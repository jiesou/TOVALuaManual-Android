package top.jiecs.tovalua.data

import java.util.*

object ListItemContent {

    /**
     * 一个包含全部列表项的 ArrayList
     */
    val ITEMS: MutableList<ListItem> = ArrayList()

    /**
     * 首页帖子流每个帖子的对象
     */
    data class ListItem(val title: String, val description: String,
                        val likes: Int, val comments: Int, val views: Int)
}