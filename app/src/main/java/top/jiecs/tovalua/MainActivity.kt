package top.jiecs.tovalua

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import okhttp3.*
import org.json.JSONObject
import top.jiecs.tovalua.data.ListItemContent
import top.jiecs.tovalua.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var serverBaseUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        serverBaseUrl = getString(R.string.server_base_url)
        setContentView(R.layout.activity_main)

        if (ListItemContent.ITEMS.isEmpty()) {
            updateItem(0)
        } else {
            binding.progress.visibility = View.GONE
        }
    }

    private fun updateItem(page: Int) {
        Log.d("MainActivity", "updateItem")
        Thread {
            // OkHttp
            val client = OkHttpClient()
            val httpRequest = Request.Builder()
                .url(serverBaseUrl + "item/list?page=$page")
                .build()
            Log.d("MainActivity", "httpRequest")
            client.newCall(httpRequest).execute().use {
                val json = JSONObject(it.body!!.string())
                // 获取 data 子项并遍历添加到 ITEMS 中
                val itemList = json.getJSONObject("data").getJSONArray("items")
                for (i in 0 until itemList.length()) {
                    val item = itemList.getJSONObject(i)
                    // 调用 addItem 方法添加 item 到 ITEMS 中，并通知更新
                    ListItemContent.ITEMS.add(
                        ListItemContent.ListItem(
                            item.getString("title"),
                            item.getString("description"),
                            item.getJSONObject("reaction").getInt("like"),
                            item.getJSONObject("comments").getInt("length"),
                            item.getInt("views")
                        )
                    )
                }
            }
            // notifyItemInserted in Fragment
            (supportFragmentManager.findFragmentById(R.id.item_list_fragment) as ItemFragment)
                .notifyItemInserted(ListItemContent.ITEMS.size)


        }.start()


        Snackbar.make(binding.coordinator, "网络错误", Snackbar.LENGTH_INDEFINITE).setAction("重试") {
            clearItems()
            updateItem(1)
        }.show()
    }

    private fun clearItems() {
        val size = ListItemContent.ITEMS.size
        ListItemContent.ITEMS.clear()
        (supportFragmentManager.findFragmentById(R.id.item_list_fragment) as ItemFragment)
            .notifyItemRemoved(size)
    }
}
