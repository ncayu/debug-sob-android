package com.android.debug.ui.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.android.debug.R
import com.android.debug.databinding.ActivitySplashBinding
import com.android.debug.ui.login.LoginActivity
import com.github.zackratos.ultimatebar.UltimateBar
import com.hi.dhl.binding.viewbind
import com.umeng.analytics.MobclickAgent

class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by viewbind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        if (Build.VERSION.SDK_INT >= 28) {
            val lp = window.attributes
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.attributes = lp
        }
        UltimateBar.with(this)
                .applyNavigation(true)
                .create()
                .hideBar()

        binding.animLogo.addOffsetAnimListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                Log.d("AnimLogoView", "Offset anim end")
            }
        })
        binding.animLogo.addGradientAnimListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                Log.d("AnimLogoView", "Gradient anim end")
                //启动main
                // val main = Intent(binding.animLogo.context, LoginActivity::class.java)
                // startActivity(main)
                // finish()
                val i = Intent(this@SplashActivity, LoginActivity::class.java)
                val sharedView = binding.ivSplashLogo
                val transitionName = getString(R.string.blue_name);
                val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                        this@SplashActivity,
                        sharedView,
                        transitionName
                )
                startActivity(i, transitionActivityOptions.toBundle())
                binding.animLogo.postDelayed({ finish() }, 1300)
            }
        })
        binding.animLogo.startAnimation()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        UltimateBar.with(this)
                .applyNavigation(true)
                .create()
                .hideBar()
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