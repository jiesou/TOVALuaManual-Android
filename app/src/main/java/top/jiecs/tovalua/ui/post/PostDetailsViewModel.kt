package top.jiecs.tovalua.ui.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import top.jiecs.tovalua.data.ListContent

class PostDetailsViewModel(postReserved: ListContent.Post) : ViewModel() {

    var post = MutableLiveData<ListContent.Post>()

    init {
        post.value = postReserved
    }
}