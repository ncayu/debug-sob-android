package com.android.debug.ui.fragment.vm

import androidx.lifecycle.viewModelScope
import com.android.debug.core.base.BaseViewModel
import com.android.debug.model.api.onFailure
import com.android.debug.model.api.onSucceed
import kotlinx.coroutines.launch

/**
 * @author: dr
 * @date: 2021/6/14
 * @description 首页推荐
 */
class HomeViewModel : BaseViewModel() {

    /**
     * 首页推荐接口
     * @param page Int 页码最低1
     */
    fun getRecommend(page: Int) {
        viewModelScope.launch {
            request { getHomeRecommend(page) }
                    .onSucceed { }
                    .onFailure { }
        }
    }
}