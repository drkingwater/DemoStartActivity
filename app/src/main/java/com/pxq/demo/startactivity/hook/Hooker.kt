package com.pxq.demo.startactivity.hook

import android.util.Log
import java.lang.Exception

/**
 * Description:
 * Author : pxq
 * Date : 2020/5/14 8:36 PM
 */
object Hooker {

    private val TAG = Hooker.javaClass.simpleName

    val extra = "target_activity"

    fun hook(version:Int):Boolean = try {
        //todo 兼容版本
        when(version){
            19,23 -> {
                ActivityHookerV19().hook()
                true
            }
            else -> {
                Log.w(TAG, "Android-$version 未兼容！！！")
                false
            }
        }
    } catch (e : Exception){
        e.printStackTrace()
        false
    }

}