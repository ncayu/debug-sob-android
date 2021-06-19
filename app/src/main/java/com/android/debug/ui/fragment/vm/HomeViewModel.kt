package com.android.debug.ui.fragment.vm

import androidx.lifecycle.viewModelScope
import com.android.debug.core.base.BaseViewModel
import com.android.debug.model.api.onFailure
import com.android.debug.model.api.onSucceed
import com.android.debug.model.bean.HomeRecommend
import com.android.debug.utils.AppToast
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * @author: dr
 * @date: 2021/6/14
 * @description 首页推荐
 */
class HomeViewModel : BaseViewModel() {

    val articleList = UnPeekLiveData<HomeRecommend>()

    val requestState = UnPeekLiveData<Boolean>()

    /**
     * 因为没有字段区分item的类型，这里需要根据covers的长度来设置类型，需要flow来拆分
     */
    private fun getRecommendFlow(page: Int): Flow<HomeRecommend> {
        return flow {
            request { getHomeRecommend(page) }
                    .onSucceed {
                        this?.let { recommend ->
                            //修改样式后，这里不需要区分单图还是多图，直接使用单图即可
                            // recommend.list.forEach {
                            //     if (it.avatar != null && it.covers.size > 1) {
                            //         it.rvItemType = HomeRecommend.RecommendArticle.ITEM_STYLE_MULTI
                            //     } else {
                            //         it.rvItemType = HomeRecommend.RecommendArticle.ITEM_STYLE_SINGLE
                            //     }
                            // }
                            emit(this)
                        }
                    }
                    .onFailure {
                        //如果true，停止刷新，false，就显示加载更多错误
                        requestState.postValue(page == 1)
                        AppToast.toast(it)
                    }
        }
    }

    /**
     * 首页推荐接口
     * @param page Int 页码最低1
     */
    fun getRecommend(page: Int) {
        viewModelScope.launch {
            getRecommendFlow(page)
                    .catch {
                        //如果true，停止刷新，false，就显示加载更多错误
                        requestState.postValue(page == 1)
                        AppToast.toast(this.toString())
                    }
                    .collect {
                        articleList.postValue(it)
                    }
        }
    }

}