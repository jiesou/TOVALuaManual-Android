package top.jiecs.tovalua

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import top.jiecs.tovalua.data.ListItemContent.ListItem
import top.jiecs.tovalua.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] 能显示 [ListItem].
 */
class MyItemRecyclerViewAdapter(
    private var values: List<ListItem>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 将全局的监听传递给 ViewHolder
        return ViewHolder(
            // 把 xml 布局转换成 View 对象传入 FragmentItemBinding
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.title
        holder.descriptionView.text = item.description
        holder.likesView.text = item.likes.toString()
        holder.commentsView.text = item.comments.toString()
        holder.viewsView.text = item.views.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val titleView: TextView = binding.title
        val descriptionView: TextView = binding.description
        val likesView: TextView = binding.likesText
        val commentsView: TextView = binding.commentsText
        val viewsView: TextView = binding.viewsText
    }
}
