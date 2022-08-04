package top.jiecs.tovalua

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import top.jiecs.tovalua.data.ListContent
import top.jiecs.tovalua.ui.main.PostPagerAdapter
import top.jiecs.tovalua.databinding.ActivityPostBinding
import top.jiecs.tovalua.ui.main.PostDetailsViewModel
import top.jiecs.tovalua.ui.main.PostDetailsViewModelFactory

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getParcelableExtra<ListContent.Item>("item")
        item?.let {
            val detailsViewModel = ViewModelProvider(this, PostDetailsViewModelFactory(item))[PostDetailsViewModel::class.java]
        }

        val sectionsPagerAdapter = PostPagerAdapter(this)
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
