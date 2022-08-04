package top.jiecs.tovalua.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.viewpager2.adapter.FragmentStateAdapter
import top.jiecs.tovalua.R

class PostPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun createFragment(position: Int): Fragment {
        // 调用 getItem 以实例化给定页面的 Fragment
        // 返回一个 PostDetailsFragment（定义为下面的静态内部类）
        return PostDetailsFragment.newInstance(position + 1)
    }

    override fun getItemCount(): Int {
        return 2
    }
}