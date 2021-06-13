package com.android.debug

import android.app.Application
import android.content.Context
import com.android.debug.utils.GlideLoaderImp
import com.debug.imageload.ImageLoader
import com.hjq.toast.ToastUtils
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import kotlin.properties.Delegates

/**
 * @author: dr
 * @date: 2020/12/31
 * @description
 */
class DebugApplication : Application() {

    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        //toast
        ToastUtils.init(this)
        //初始化glide 如果需要换其他，自行实现ILoaderStrategy
        ImageLoader.getInstance().setGlobalImageLoader(GlideLoaderImp())


       UMConfigure.setProcessEvent(true)
       UMConfigure.setLogEnabled(BuildConfig.DEBUG)
        //60c611efc59ffc0de76d5a30 600e78cdb3b4f6635de3a8e0
       UMConfigure.init(this, "60c611efc59ffc0de76d5a30", "debug-x", UMConfigure.DEVICE_TYPE_PHONE, null)
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
    }

}