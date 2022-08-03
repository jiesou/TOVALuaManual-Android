package top.jiecs.tovalua.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import top.jiecs.tovalua.databinding.FragmentPostBinding

class PostDetailsFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentPostBinding? = null

    // 只在 onCreateView 和 onDestroyView 中可用
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        val root = binding.root

        pageViewModel.userNick.observe(viewLifecycleOwner) {
            binding.userNick.text = it
        }
        return root
    }

    companion object {
        /**
         * 表示此 Fragment 页数的 Fragment 参数
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * 返回一个这个 Fragment 页的新实例
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PostDetailsFragment {
            return PostDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}