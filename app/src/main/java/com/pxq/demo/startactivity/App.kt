package com.pxq.demo.startactivity

import android.app.Application
import android.os.Build
import com.pxq.demo.startactivity.hook.Hooker

/**
 * Description: hook Activity
 * Author : pxq
 * Date : 2020/5/14 9:17 PM
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Hooker.hook(Build.VERSION.SDK_INT)
    }

}