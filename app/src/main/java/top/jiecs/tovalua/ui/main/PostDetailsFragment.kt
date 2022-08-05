package top.jiecs.tovalua.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import top.jiecs.tovalua.data.ListContent
import top.jiecs.tovalua.databinding.FragmentPostDetailsBinding
import top.jiecs.tovalua.databinding.UnitUserInfoBinding

class PostDetailsFragment(var item: ListContent.Item) : Fragment() {

    private lateinit var viewModel: PostDetailsViewModel
    private lateinit var binding: FragmentPostDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, PostDetailsViewModelFactory(item))[PostDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        val root = binding.root
        val unitUserInfoBinding = UnitUserInfoBinding.bind(binding.root)

        val item = viewModel.item.value!!
        unitUserInfoBinding.userNick.text = item.userNick
        Glide.with(this).load(item.userAvatar).into(unitUserInfoBinding.userAvatar)
        return root
    }

}