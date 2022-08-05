package top.jiecs.tovalua

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import top.jiecs.tovalua.data.ListContent
import top.jiecs.tovalua.ui.main.PostPagerAdapter
import top.jiecs.tovalua.databinding.ActivityPostBinding
import top.jiecs.tovalua.ui.main.PostDetailsFragment

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getParcelableExtra<ListContent.Item>("item")
        val sectionsPagerAdapter = item?.let { PostPagerAdapter(this, it) }
        val viewPager: ViewPager2 = binding.postPager
        viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.details)
                1 -> tab.text = getString(R.string.comment)
            }
        }.attach()

    }
}
