package com.android.debug.ui.fragment.vm

import androidx.lifecycle.viewModelScope
import com.android.debug.core.base.BaseViewModel
import com.android.debug.model.api.onFailure
import com.android.debug.model.api.onSucceed
import com.android.debug.model.bean.SobMoment
import com.android.debug.utils.AppToast
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import kotlinx.coroutines.launch

class MomentViewModel : BaseViewModel() {

    val momentLiveData = UnPeekLiveData<SobMoment>()

    fun getMoment(page: Int) {
        viewModelScope.launch {
            request { getMomentList(page) }
                    .onSucceed {
                        this?.apply { momentLiveData.postValue(this) }
                    }
                    .onFailure {
                        AppToast.toast(it)
                    }
        }
    }
}