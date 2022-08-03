package top.jiecs.tovalua.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import top.jiecs.tovalua.R

private val TAB_TITLES = arrayOf(
    R.string.details,
    R.string.comment
)

class PostPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        // 调用 getItem 以实例化给定页面的 Fragment
        // 返回一个 PostDetailsFragment（定义为下面的静态内部类）
        return PostDetailsFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}