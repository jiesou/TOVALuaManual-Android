package top.jiecs.tovalua.ui.post

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import top.jiecs.tovalua.data.ListContent

class PostPagerAdapter(fa: FragmentActivity, var post: ListContent.Post) : FragmentStateAdapter(fa) {

    override fun createFragment(position: Int): Fragment {
        // 调用 getItem 以实例化给定页面的 Fragment
        // 返回一个 PostDetailsFragment（定义为下面的静态内部类）
        return when (position) {
            0 -> {
                PostDetailsFragment(post)
            }
            else -> {
                PostDetailsFragment(post)
            }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}