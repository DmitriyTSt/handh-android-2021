package ru.handh.lesson5

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract

class SecondActivityContract : ActivityResultContract<User, String>() {
    override fun createIntent(context: Context, input: User): Intent {
        return SecondActivity.createStartIntent(context, input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        return intent?.getStringExtra(SecondActivity.EXTRA_RESULT_USERNAME).orEmpty()
    }
}