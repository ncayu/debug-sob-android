package com.android.debug.ui.loop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.debug.core.base.BaseViewModel
import com.android.debug.model.api.onFailure
import com.android.debug.model.api.onSucceed
import com.android.debug.model.bean.SobLoop
import com.android.debug.utils.AppToast
import kotlinx.coroutines.launch

/**
 * @author: dr
 * @date: 2021/1/29
 * @description 博客轮播图vm
 */
class JpBlogViewModel : BaseViewModel() {
    //轮播图数据
    val liveDataLoops = MutableLiveData<List<SobLoop>>()

    fun httpGetLoop() {
        viewModelScope.launch {
            request { getLoop() }
                    .onSucceed {
                        //成功处理.有些接口，data是没有的。
                        this?.let { liveDataLoops.value = this }
                    }.onFailure {
                        //错误处理
                        AppToast.toast(it)
                    }
        }
    }
}