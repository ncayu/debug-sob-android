package com.android.debug.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.debug.R
import com.umeng.analytics.MobclickAgent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }
}