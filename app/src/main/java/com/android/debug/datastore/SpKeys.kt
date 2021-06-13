package com.android.debug.datastore

import androidx.datastore.preferences.core.preferencesKey


/**
 * 要使用data source需要统一在这里配置
 */
object SpKeys {
    /**
     * token保存
     */
    val token = preferencesKey<String>("app_token_key")

    /**
     * 用户信息
     */
    val userInfo = preferencesKey<String>("user_info")

    val userName = preferencesKey<String>("user_name")
    val userPassword = preferencesKey<String>("user_pwd")

    val loginKey = preferencesKey<String>("cli")

}