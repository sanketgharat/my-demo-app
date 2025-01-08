package com.sg.mydemoapp

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    public fun showToast(message: String) {
        if (message.isNotBlank())
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}