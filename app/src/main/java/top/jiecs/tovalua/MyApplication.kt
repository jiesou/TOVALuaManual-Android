package top.jiecs.tovalua

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * 帮助从全局获取 Context
 */
class MyApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
