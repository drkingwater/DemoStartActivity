package com.pxq.demo.startactivity.hook

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.util.Log
import java.lang.reflect.Field
import java.lang.reflect.Method


/**
 * Description:
 * Author : pxq
 * Date : 2020/5/14 7:54 PM
 */
class ActivityHookerV19 : IActivityHooker{


    @SuppressLint("PrivateApi", "DiscouragedPrivateApi")
    override fun hook() {
        //拿到ActivityThread
        val activityThreadClass = Class.forName("android.app.ActivityThread")
        val currentActivityThread: Method = activityThreadClass.getDeclaredMethod("currentActivityThread")
        val activityThreadObj: Any? = currentActivityThread.invoke(null)

        //拿到mH
        val mHField: Field = activityThreadClass.getDeclaredField("mH")
        mHField.isAccessible = true
        val mH: Handler = mHField[activityThreadObj] as Handler

        //拿到Handle里的mCallBack
        val callBackField: Field = Handler::class.java.getDeclaredField("mCallback")
        callBackField.isAccessible = true
        //赋新值
        callBackField.set(mH, object : Handler.Callback {
            override
            fun handleMessage(msg: Message): Boolean {
                //LAUNCH_ACTIVITY = 100
                if (msg.what == 100) {
                    Log.e(TAG, "handleMessage: LAUNCH_ACTIVITY")
                    val record: Any = msg.obj
                    try {
                        Log.d(TAG, "hookActivityClientRecord: " + hookActivityClientRecord(record))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                //继续执行handleMessage
                return false
            }
        })
    }

    /**
     * 替换掉ActivityClientRecord中的Intent
     *
     * @param record
     * @return
     * @throws Exception
     */
    @SuppressLint("PrivateApi")
    private fun hookActivityClientRecord(record: Any): Boolean {
        //内部类反射需要用xxx.xxx$xxx
        val recordClass = Class.forName("android.app.ActivityThread\$ActivityClientRecord")
        val intentField = recordClass.getDeclaredField("intent")
        intentField.isAccessible = true
        //拿到intent
        val intent = intentField[record] as Intent
        //目标Activity，没有就返回
        val targetIntent = intent.getParcelableExtra<Intent>(Hooker.extra) ?: return false
        //修改intent
        intentField[record] = targetIntent
        return true
    }

    companion object{
        val TAG = ActivityHookerV19::class.java.simpleName;
    }

}