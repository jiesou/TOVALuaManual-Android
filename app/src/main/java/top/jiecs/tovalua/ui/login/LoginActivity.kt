package top.jiecs.tovalua.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.viewpager.widget.ViewPager
import top.jiecs.tovalua.databinding.ActivityLoginBinding
import top.jiecs.tovalua.R
import top.jiecs.tovalua.units.User

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)



        binding.email.afterTextChanged {
            if (it.matches(Regex("^.{2,12}\$"))) {
                binding.email.error = "用户名长度为6-20位字母或数字"
            }
        }

        binding.password.apply {
            afterTextChanged {
                Log.d("LoginActivity", "password.afterTextChanged$it")
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        login(
                            binding.email.text.toString(),
                            binding.password.text.toString()
                        )
                }
                false
            }
        }
        binding.login.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            login(binding.email.text.toString(), binding.password.text.toString())
        }
    }

    private fun login(email: String, password: String) {
        User(email, password).login{
            binding.loading.visibility = View.GONE
            if (it.code >= 0) {
                finish()
            } else {
                when (it.code) {
                    -21 -> binding.password.error = getString(R.string.account_not_exist)
                    -22 -> binding.email.error = getString(R.string.account_not_exist)
                    else -> binding.email.error = "${it.code}: ${it.message}"
                }
            }
        }
    }
}


// 简化 afterTextChangedEditText 应用于 EditText 的代码
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}