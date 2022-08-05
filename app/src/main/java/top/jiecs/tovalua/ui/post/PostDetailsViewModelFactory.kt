package top.jiecs.tovalua.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import top.jiecs.tovalua.data.ListContent

class PostDetailsViewModelFactory(private val postReserved: ListContent.Post): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostDetailsViewModel(postReserved) as T
    }
}