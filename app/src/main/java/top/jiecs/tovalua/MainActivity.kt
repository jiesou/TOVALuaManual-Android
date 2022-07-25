package top.jiecs.tovalua

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import okhttp3.*
import org.json.JSONObject
import top.jiecs.tovalua.data.ListContent
import top.jiecs.tovalua.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemFragment: ItemFragment
    private lateinit var serverBaseUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemFragment = supportFragmentManager.findFragmentById(R.id.item_list_fragment) as ItemFragment
        serverBaseUrl = getString(R.string.server_base_url)
    }

    override fun onResume() {
        super.onResume()
        var page = 0

        if (ListContent.ITEMS.isEmpty()) {
            updateItem(0)
        } else {
            binding.progress.visibility = View.GONE
        }
        itemFragment.setOnScrollToBottomListener {
            page++
            updateItem(page)
        }
        itemFragment.setOnItemClickListener { item, i ->
            Snackbar.make(binding.root, "点击了第${i}个item，id${item.id}", Snackbar.LENGTH_LONG).show()
        }
    }


    private fun updateItem(page: Int) {
        Log.d("MainActivity", "updateItem")
        binding.progress.visibility = View.VISIBLE
        Thread {
            // OkHttp
            val client = OkHttpClient()
            val httpRequest = Request.Builder()
                .url(serverBaseUrl + "item/list?page=$page")
                .build()
            val addItemList: MutableList<ListContent.Item> = mutableListOf()
            try {
                client.newCall(httpRequest).execute().use {
                    val json = JSONObject(it.body!!.string())
                    // 获取 data 子项并遍历添加到 ITEMS 中
                    val itemList = json.getJSONObject("data").getJSONArray("items")
                    for (i in 0 until itemList.length()) {
                        val item = itemList.getJSONObject(i)
                        // 调用 addItem 方法添加 item 到 ITEMS 中，并通知更新
                        addItemList.add(
                            ListContent.Item(
                                item.getString("title"),
                                item.getString("description"),
                                item.getJSONObject("reaction").getInt("like"),
                                item.getJSONObject("comments").getInt("length"),
                                item.getInt("views"),
                                item.getString("id")
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Snackbar.make(binding.coordinator, "网络错误", Snackbar.LENGTH_INDEFINITE).setAction("重试") {
                    itemFragment.clearItems()
                    updateItem(1)
                }.show()
            }
            runOnUiThread {
                itemFragment.addItems(addItemList)
                binding.progress.visibility = View.GONE
            }
        }.start()
    }
}
