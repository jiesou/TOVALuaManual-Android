package top.jiecs.tovalua

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.snackbar.Snackbar
import okhttp3.*
import org.json.JSONObject
import top.jiecs.tovalua.data.ListContent
import top.jiecs.tovalua.databinding.ActivityMainBinding
import top.jiecs.tovalua.ui.post.PostActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listItemFragment: ListItemFragment
    private lateinit var serverBaseUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        listItemFragment = supportFragmentManager.findFragmentById(R.id.item_list_fragment) as ListItemFragment
        serverBaseUrl = getString(R.string.server_base_url)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        }
    }

    override fun onResume() {
        super.onResume()
        // 初始化页数为 0
        var page = 0

        if (ListContent.Posts.isEmpty()) {
            updatePost(0)
        } else {
            binding.progress.visibility = View.GONE
        }

        // 滚到底加载下一页
        listItemFragment.setOnScrollToBottomListener {
            page++
            updatePost(page)
        }
        // 点击事件
        listItemFragment.setOnItemClickListener { post, _ ->
            startActivity(Intent(this, PostActivity::class.java).putExtra("post", post))
        }
    }

    private fun updatePost(page: Int) {
        binding.progress.visibility = View.VISIBLE
        Thread {
            // OkHttp
            val client = OkHttpClient()
            val httpRequest = Request.Builder()
                .url(serverBaseUrl + "post/list?page=$page")
                .build()
            val addPostList: MutableList<ListContent.Post> = mutableListOf()
            try {
                client.newCall(httpRequest).execute().use {
                    val json = JSONObject(it.body!!.string())
                    val code = json.getInt("code")
                    if (code >= 0) {
                        // 获取 data 子项并遍历添加到 ITEMS 中
                        val postList = json.getJSONObject("data").getJSONArray("posts")
                        for (i in 0 until postList.length()) {
                            val post = postList.getJSONObject(i)
                            // 调用 addItem 方法添加 item 到 ITEMS 中，并通知更新
                            addPostList.add(
                                ListContent.Post(
                                    post.getString("title"),
                                    post.getString("description"),
                                    post.getJSONObject("user").getString("name"),
                                    post.getJSONObject("user").getString("avatar"),
                                    post.getJSONObject("reaction").getInt("like"),
                                    post.getJSONObject("comments").getInt("length"),
                                    post.getInt("views"),
                                    post.getLong("timeCreate"),
                                    post.getString("id")
                                )
                            )
                        }
                    } else {
                        Snackbar.make(
                            binding.coordinator, "意料之外的错误 $code ${json.getString("message")}",
                            Snackbar.LENGTH_INDEFINITE
                        ).setAction(getString(R.string.retry)) {
                            listItemFragment.clearItems()
                            updatePost(1)
                        }.show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Snackbar.make(binding.coordinator, "网络错误 ${e.message}", Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.retry)) {
                        listItemFragment.clearItems()
                        updatePost(1)
                    }.show()
            }
            runOnUiThread {
                listItemFragment.addItems(addPostList)
                binding.progress.visibility = View.GONE
            }
        }.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> binding.drawerLayout.openDrawer(GravityCompat.START)
            R.id.search -> {
                Snackbar.make(binding.root, "点击了搜索", Snackbar.LENGTH_LONG).show()
            }
        }
        return true
    }
}
