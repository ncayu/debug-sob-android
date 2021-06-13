package com.android.debug.model.api

class BaseResponse<T> {
    val data: T? = null
    val code: Int = 0
    val message: String? = null
    val success: Boolean = false
    var exception: Exception? = null
}

inline fun <T> BaseResponse<T>.onSucceed(bloc: T?.() -> Unit): BaseResponse<T> {
    return if (exception == null) {//没有异常，则把正确结果bloc出去
        bloc(this.data)
        this
    } else {//出现异常（网络/服务器/自定义异常）执行这里 不用bloc
        this
    }
}


inline fun <T> BaseResponse<T>.onFailure(bloc: (String) -> Unit) {

    exception?.message?.let(bloc)

    // exception?.apply {
    //     this.message?.let { bloc(it) }
    //     //拦截其他异常，比如token过期，或者公共需要处理的
    //    // if (this is ApiException) {
    //    //     //内部的异常
    //    //     this.message?.let { bloc(it) }
    //    // } else {
    //    //
    //    // }
    // }

}