package top.jiecs.tovalua

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import top.jiecs.tovalua.data.ListContent

/**
 * 展示帖子列表的 fragment
 */
class ItemFragment : Fragment() {

    private var columnCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // 设置 RecyclerView
        with(view as RecyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            // 设置适配器
            adapter = MyItemRecyclerViewAdapter(ListContent.ITEMS)
        }
        return view
    }


    fun addItems(items: List<ListContent.Item>) {
        val adapter = (view as RecyclerView).adapter as MyItemRecyclerViewAdapter
        val oldCount = adapter.itemCount
        ListContent.ITEMS.addAll(items)
        adapter.notifyItemRangeInserted(oldCount, ListContent.ITEMS.size - 1)
    }

    fun clearItems() {
        val adapter = (view as RecyclerView).adapter as MyItemRecyclerViewAdapter
        val oldCount = adapter.itemCount
        ListContent.ITEMS.clear()
        adapter.notifyItemRangeRemoved(0, oldCount)
    }
}