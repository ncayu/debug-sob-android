package com.android.debug.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.debug.BuildConfig
import com.android.debug.core.base.BaseViewModel
import com.android.debug.model.api.onFailure
import com.android.debug.model.api.onSucceed
import com.android.debug.model.bean.LoginBody
import com.android.debug.ui.main.MainActivity
import com.android.debug.utils.AppToast
import com.android.lib.common.utils.AppMd5Utils
import kotlinx.coroutines.launch

/**
 * @author: dr
 * @date: 2021/2/1
 * @description
 */
class LoginViewModel : BaseViewModel() {

    val shakeAnim = MutableLiveData<Int>()

    val userName = ObservableField<String>().apply { set("") }
    val password = ObservableField<String>().apply { set("") }
    val verifyCode = ObservableField<String>().apply { set("") }

    val liveDataCaptcha = MutableLiveData<String>()

    fun login() {
        if (userName.get()?.isEmpty() == true) {
            AppToast.toast("输入账户")
            shakeAnim.postValue(1)
            return
        }
        if (password.get()?.isEmpty() == true) {
            AppToast.toast("输入密码")
            shakeAnim.postValue(2)

            return
        }
        if (verifyCode.get()?.isEmpty() == true) {
            AppToast.toast("输入验证码")
            shakeAnim.postValue(3)
            return
        }
        viewModelScope.launch {
            request {
                doLogin(
                        verifyCode.get()!!,
                        LoginBody(userName.get(), AppMd5Utils.getMD5(password.get())))
            }
                    .onSucceed {
                        navigateAndDestroy(MainActivity::class.java)
                    }
                    .onFailure {
                        AppToast.toast(it)
                        shakeAnim.postValue(3)
                        getCaptchaCode()
                    }
        }
    }

    fun getCaptchaCode() {
        liveDataCaptcha.value = BuildConfig.UNION_BASE_URL + "/uc/ut/captcha?code=" + System.currentTimeMillis()
    }

}