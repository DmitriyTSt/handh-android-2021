package ru.handh.lesson5

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    companion object {
        const val KEY_USER = "key_user"

        const val EXTRA_RESULT_USERNAME = "extra_result_username"

        fun createStartIntent(context: Context, user: User): Intent {
            return Intent(context, SecondActivity::class.java).apply {
                putExtra(KEY_USER, user)
            }
        }
    }

    private val user by lazy { intent.getParcelableExtra<User>(KEY_USER) as User }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        setResult(Activity.RESULT_OK, Intent().apply { putExtra(EXTRA_RESULT_USERNAME, "${user.name} ${user.surname}") })
        finish()
    }
}