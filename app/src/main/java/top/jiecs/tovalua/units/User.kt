package top.jiecs.tovalua.units

import top.jiecs.tovalua.MyApplication
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/**
 * 用户辅助类
 */
class User(email: String, password: String) {
    private val context = MyApplication.context

    private val masterKey: MasterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val pref = EncryptedSharedPreferences.create(
        context, "account", masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    var email: String? = null
    var password: String? = null

    fun login(callback: (CheckLoginResult) -> Unit) {
        val result = checkLogin()
        if (result.code >= 0) {
            pref.edit().apply {
                putString("email", email)
                putString("password", password)
            }.apply()
        }
        callback(result)
    }

    fun logout() {
        this.email = null
        this.password = null
        pref.edit().clear().apply()
    }

    private fun checkLogin(): CheckLoginResult {
        // TODO: 检查用户后端
        Thread.sleep(3000)
        return CheckLoginResult(0, "Success")
    }
    data class CheckLoginResult(val code: Int, val message: String)
}