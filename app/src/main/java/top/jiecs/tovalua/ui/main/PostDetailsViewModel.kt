package top.jiecs.tovalua.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import top.jiecs.tovalua.data.ListContent

class PostDetailsViewModel(itemReserved: ListContent.Item) : ViewModel() {

    var item = MutableLiveData<ListContent.Item>()

    init {
        item.value = itemReserved
    }
}