package top.jiecs.tovalua.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import top.jiecs.tovalua.data.ListContent
import top.jiecs.tovalua.databinding.FragmentPostDetailsBinding
import top.jiecs.tovalua.databinding.UnitUserInfoBinding
import java.text.SimpleDateFormat
import java.util.*

class PostDetailsFragment(private var post: ListContent.Post) : Fragment() {

    private lateinit var viewModel: PostDetailsViewModel
    private lateinit var binding: FragmentPostDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, PostDetailsViewModelFactory(post))[PostDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        val root = binding.root
        val unitUserInfoBinding = UnitUserInfoBinding.bind(binding.root)

        val post = viewModel.post.value!!
        unitUserInfoBinding.userNick.text = post.userNick
        Glide.with(this).load(post.userAvatar).into(unitUserInfoBinding.userAvatar)
        unitUserInfoBinding.time.text = SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA).format(post.time)
        return root
    }

}