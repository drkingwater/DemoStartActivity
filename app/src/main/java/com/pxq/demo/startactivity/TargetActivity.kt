package com.pxq.demo.startactivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * Description: 未注册的Activity
 * Author : pxq
 * Date : 2020/5/14 9:45 PM
 */
class TargetActivity : AppCompatActivity() {

    val TAG = TargetActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "TargetActivity 启动...")
    }



}