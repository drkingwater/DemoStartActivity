package com.pxq.demo.startactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.pxq.demo.startactivity.hook.Hooker

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun open(view: View) {
        val intent = Intent(this, ActivityStub::class.java).apply {
            //要启动的Activity
            val bundle = Bundle()
            bundle.putParcelable(Hooker.extra, Intent(this@MainActivity, TargetActivity::class.java))
            putExtras(bundle)
        }

        startActivity(intent)
    }
}
