package com.pxq.demo.startactivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * Description: 代理Activity
 * Author : pxq
 * Date : 2020/5/14 9:44 PM
 */
class ActivityStub : AppCompatActivity() {

    val TAG = ActivityStub::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "ActivityStub")
    }

}