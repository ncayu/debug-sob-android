package com.android.debug.ui.login

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.android.debug.BR
import com.android.debug.R
import com.android.debug.core.base.BaseActivity
import com.android.debug.databinding.ActivityLoginBinding
import com.android.lib.common.utils.singleClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.umeng.analytics.MobclickAgent


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    private lateinit var shake: Animation

    override fun getLayoutId() = R.layout.activity_login

    override fun getVariableId() = BR.loginVm

    override fun initView() {
        MobclickAgent.onEvent(this, "login-page")
        shake = AnimationUtils.loadAnimation(this, R.anim.ani_shake)
        vb.btnLogin.singleClick {
            viewModel.login()
        }
        vb.ivVerifyCode.singleClick {
            viewModel.getCaptchaCode()
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        viewModel.getCaptchaCode()
    }


    override fun startObserve() {
        viewModel.liveDataCaptcha.observe(this, {
            Glide.with(this@LoginActivity)
                    .load(it)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(vb.ivVerifyCode)
        })
        viewModel.shakeAnim.observe(this, {
            showAnimShake(it)
        })
    }

    private fun showAnimShake(view: Int) {
        when (view) {
            1 -> {
                vb.etUser.startAnimation(shake)
            }
            2 -> {
                vb.etPwd.startAnimation(shake)
            }
            3 -> {
                vb.etCode.startAnimation(shake)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        vb.etCode.animation?.cancel()
        vb.etPwd.animation?.cancel()
        vb.etUser.animation?.cancel()
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