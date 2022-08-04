package top.jiecs.tovalua.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import top.jiecs.tovalua.databinding.FragmentPostBinding

class PostDetailsFragment : Fragment() {

    private lateinit var viewModel: PostDetailsViewModel
    private lateinit var binding: FragmentPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it)
            }?.get(PostDetailsViewModel::class.java)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false)
        val root = binding.root

        binding.userNick.text = viewModel.item.value?.userNick
        Log.d("PostDetailsFragment", "item: ${viewModel.item}")
        return root
    }

    companion object {
        /**
         * 返回一个这个 Fragment 页的新实例
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PostDetailsFragment {
            return when (sectionNumber) {
                1 -> PostDetailsFragment()
                else -> PostDetailsFragment()
            }
        }
    }
}