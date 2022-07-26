package top.jiecs.tovalua.data

import top.jiecs.tovalua.units.User
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
                    val userNick: String, val userAvatar: String,
                    val likes: Int, val comments: Int, val views: Int,
                    val time: Long ,val id: String)
}