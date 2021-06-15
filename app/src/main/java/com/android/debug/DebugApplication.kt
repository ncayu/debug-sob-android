package com.android.debug

import android.app.Application
import android.content.Context
import com.android.debug.model.api.OKHttpUpdateHttpService
import com.android.debug.utils.GlideLoaderImp
import com.debug.imageload.ImageLoader
import com.hjq.toast.ToastUtils
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import com.xuexiang.xupdate.XUpdate
import com.xuexiang.xupdate.entity.UpdateError.ERROR.CHECK_NO_NEW_VERSION
import com.xuexiang.xupdate.utils.UpdateUtils
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
        XUpdate.get()
            .debug(BuildConfig.DEBUG)
            .isWifiOnly(true) // By default, only version updates are checked under WiFi
            .isGet(true) // The default setting uses Get request to check versions
            .isAutoMode(false) // The default setting is non automatic mode
            .param(
                "versionCode",
                UpdateUtils.getVersionCode(this)
            ) // Set default public request parameters
            .param("appKey", packageName)
            .setOnUpdateFailureListener { error ->
                // Set listening for version update errors
                if (error.code != CHECK_NO_NEW_VERSION) {          // Handling different errors
                }
            }
            .supportSilentInstall(true) // Set whether silent installation is supported. The default is true
            .setIUpdateHttpService(OKHttpUpdateHttpService()) // This must be set! Realize the network request function.
            .init(this) // This must be initialized


    }

}