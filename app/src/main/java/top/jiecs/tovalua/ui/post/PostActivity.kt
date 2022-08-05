package top.jiecs.tovalua.ui.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import top.jiecs.tovalua.R
import top.jiecs.tovalua.data.ListContent
import top.jiecs.tovalua.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val post = intent.getParcelableExtra<ListContent.Post>("post")!!
        val postPagerAdapter = PostPagerAdapter(this, post)
        val viewPager: ViewPager2 = binding.postPager
        viewPager.adapter = postPagerAdapter

        TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.details)
                1 -> tab.text = getString(R.string.comment)
            }
        }.attach()
    }
}
