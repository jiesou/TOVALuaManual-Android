package top.jiecs.tovalua

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import top.jiecs.tovalua.data.ListContent
import top.jiecs.tovalua.ui.main.PostPagerAdapter
import top.jiecs.tovalua.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = PostPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(viewPager)

        val item = intent.getParcelableExtra<ListContent.Item>("item")
    }
}