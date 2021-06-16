package com.android.debug.ui.fragment.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.debug.core.base.BaseViewModel
import com.android.debug.model.api.onFailure
import com.android.debug.model.api.onSucceed
import com.android.debug.model.bean.HomeRecommend
import com.android.debug.utils.AppToast
import kotlinx.coroutines.launch

/**
 * @author: dr
 * @date: 2021/6/14
 * @description 首页推荐
 */
class HomeViewModel : BaseViewModel() {

    val articleList = MutableLiveData<HomeRecommend>()

    val requestState = MutableLiveData<Boolean>()

    /**
     * 首页推荐接口
     * @param page Int 页码最低1
     */
    fun getRecommend(page: Int) {
        viewModelScope.launch {
            request { getHomeRecommend(page) }
                .onSucceed {
                    this?.apply { articleList.postValue(this) }
                }
                .onFailure {
                    //如果true，停止刷新，false，就显示加载更多错误
                    requestState.postValue(page == 1)
                    AppToast.toast(it)
                }
        }
    }
}