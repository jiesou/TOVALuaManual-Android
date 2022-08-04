package top.jiecs.tovalua.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import top.jiecs.tovalua.data.ListContent

class PostDetailsViewModelFactory(private val itemReserved: ListContent.Item): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostDetailsViewModel(itemReserved) as T
    }
}