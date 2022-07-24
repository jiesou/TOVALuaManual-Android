package top.jiecs.tovalua

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import top.jiecs.tovalua.data.ListItemContent

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
            adapter = MyItemRecyclerViewAdapter(ListItemContent.ITEMS)
        }
        return view
    }

    fun notifyItemInserted(position: Int) {
        (view as RecyclerView).adapter!!.notifyItemInserted(position)
    }

    fun notifyItemRemoved(position: Int) {
        (view as RecyclerView).adapter!!.notifyItemRemoved(position)
    }
}