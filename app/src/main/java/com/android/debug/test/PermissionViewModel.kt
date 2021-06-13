package com.android.debug.test

import android.view.View
import com.android.debug.core.base.BaseViewModel
import com.android.debug.utils.AppToast

/**
 * @author: 123
 * @date: 2021/1/29
 * @description $
 */
class PermissionViewModel : BaseViewModel() {


    fun requestPermission(view: View) {
        //请求权限
        AppToast.toast("test")
    }
}