package top.jiecs.tovalua

import org.junit.Test
import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun passwordCrypt(password: String) {
        val base = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-=_+[]/{}|;':\\,.<>?"
        val random = Random()
        val sb = StringBuffer()
        for (i in 0 until 32) {
            val number: Int = random.nextInt(base.length)
            sb.append(base[number])
        }
        println("random$sb")
    }
}