package com.android.debug.ui.moment

import androidx.lifecycle.viewModelScope
import com.android.debug.core.base.BaseViewModel
import com.android.debug.model.bean.SobMomentComment
import com.android.debug.model.bean.SobMomentDetail
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import sj.mblog.LL

/**
 * @author: dr
 * @date: 2021/6/20
 * @description 摸鱼详情
 */
class MomentDetailViewModel : BaseViewModel() {

    val detailLiveData = UnPeekLiveData<SobMomentDetail>()
    val commentListData = UnPeekLiveData<SobMomentComment>()

    fun getMomentDetailById(momentId: String) {
        LL.e("摸鱼id", momentId)
        viewModelScope.launch {
            val momentInfo = async { request { getMomentDetail(momentId) } }.await()
            val commentList = async { request { getMomentComment(momentId, 1) } }.await()
            if (momentInfo.success && commentList.success) {
                LL.e("详情和评论第一页都请求成功了")
                detailLiveData.postValue(momentInfo.data)
                commentListData.postValue(commentList.data)
            }
        }
    }
}