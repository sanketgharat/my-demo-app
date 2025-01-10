package com.sg.mydemoapp.utils

import android.util.Log

object Logger {

    private val TAG = "DemoAppTag"
    fun d(tag: String, message: String?) {
        message?.let {
            Log.d(tag, message)
        }
    }

    fun e(message: String?, e: Exception) {
        message?.let {
            Log.e(TAG, message, e)
        }
    }

    fun e(message: String?) {
        message?.let {
            Log.e(TAG, message)
        }
    }
}