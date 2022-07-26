package top.jiecs.tovalua

import android.icu.text.SimpleDateFormat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import top.jiecs.tovalua.data.ListContent

import top.jiecs.tovalua.data.ListContent.Item
import top.jiecs.tovalua.databinding.FragmentItemBinding
import java.util.*

class MyItemRecyclerViewAdapter(
    private var values: List<Item>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    var itemClickListener: ((Item, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 将全局的监听传递给 ViewHolder
        val viewHolder = ViewHolder(
            // 把 xml 布局转换成 View 对象传入 FragmentItemBinding
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.cardView.setOnClickListener {
            itemClickListener?.let {
                it(values[viewHolder.bindingAdapterPosition], viewHolder.bindingAdapterPosition)
            }
        }
        return viewHolder
    }


    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val cardView: MaterialCardView = binding.card
        val titleView: TextView = binding.title
        val descriptionView: TextView = binding.description
        val userNickView: TextView = binding.userNick
        val userAvatarView: ShapeableImageView = binding.userAvatar
        val likesView: TextView = binding.likes
        val commentsView: TextView = binding.comments
        val viewsView: TextView = binding.views
        val timeView: TextView = binding.time
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.title
        holder.descriptionView.text = item.description
        holder.userNickView.text = item.userNick
        Glide.with(MyApplication.context).load(item.userAvatar).into(holder.userAvatarView)
        holder.likesView.text = item.likes.toString()
        holder.commentsView.text = item.comments.toString()
        holder.viewsView.text = item.views.toString()
        holder.timeView.text = java.text.SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA).format(item.time)
    }

    override fun getItemCount(): Int = values.size

}
