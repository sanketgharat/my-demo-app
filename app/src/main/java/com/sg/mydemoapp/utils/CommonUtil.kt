package com.sg.mydemoapp.utils

import android.content.Context
import com.sg.mydemoapp.MyDemoApplication
import java.util.Arrays
import java.util.Collections
import java.util.Stack
import kotlin.random.Random

object CommonUtil {

    private val recycle: Stack<Int> = Stack()
    private val colors: Stack<Int> = Stack()

    init {
        recycle.addAll(
            Arrays.asList(
                // ARGB hex to int >> (0xFFEE5670.toInt(),...)
                -0xbbcca, -0x16e19d, -0x63d850, -0x98c549,
                -0xc0ae4b, -0xde690d, -0xfc560c, -0xff432c,
                -0xff6978, -0xb350b0, -0x743cb6, -0x3223c7,
                -0x14c5, -0x3ef9, -0x6800, -0xa8de,
                -0x86aab8, -0x616162, -0x9f8275, -0xcccccd
            )
        )
    }

    fun isOnline(application: Context): Boolean {
        return (application as MyDemoApplication).isNetworkConnected
    }

    fun createRandomColor(): String {
        //return Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
        val randomInt = Random.nextInt(999999)
        return "#$randomInt"
    }

    fun getColor(): Int {
        if (colors.size == 0)
            while (!recycle.isEmpty()) colors.push(recycle.pop())
        Collections.shuffle(colors)
        val c = colors.pop()
        recycle.push(c)
        return c
    }
}