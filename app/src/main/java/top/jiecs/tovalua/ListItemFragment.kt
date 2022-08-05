package top.jiecs.tovalua

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.jiecs.tovalua.data.ListContent

/**
 * 展示帖子列表的 fragment
 */
class ListItemFragment : Fragment() {

    private var columnCount = 1
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        recyclerView = inflater.inflate(R.layout.fragment_list_item, container, false) as RecyclerView
        // 设置 RecyclerView
        with(recyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            // 设置适配器
            adapter = ListItemRecyclerViewAdapter(ListContent.Posts)
        }
        return recyclerView
    }

    fun setOnScrollToBottomListener(listener: () -> Unit) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // 判断是否滑动到底部
                if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                    >= recyclerView.computeVerticalScrollRange()
                ) {
                    listener()
                }
            }
        })
    }

    fun setOnItemClickListener(listener: (ListContent.Post, Int) -> Unit) {
        (recyclerView.adapter as ListItemRecyclerViewAdapter).itemClickListener = listener
    }

    fun addItems(posts: List<ListContent.Post>) {
        val adapter = recyclerView.adapter as ListItemRecyclerViewAdapter
        val oldCount = adapter.itemCount
        ListContent.Posts.addAll(posts)
        adapter.notifyItemRangeInserted(oldCount, ListContent.Posts.size - 1)
    }

    fun clearItems() {
        val adapter = recyclerView.adapter as ListItemRecyclerViewAdapter
        val oldCount = adapter.itemCount
        ListContent.Posts.clear()
        adapter.notifyItemRangeRemoved(0, oldCount)
    }
}