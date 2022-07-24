package top.jiecs.tovalua.data

import java.util.*

object ListContent {

    /**
     * 一个包含全部列表项的 ArrayList
     */
    val ITEMS: MutableList<Item> = ArrayList()

    /**
     * 首页帖子流每个帖子的对象
     */
    data class Item(val title: String, val description: String,
                    val likes: Int, val comments: Int, val views: Int)
}