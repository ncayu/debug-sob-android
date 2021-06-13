package com.android.debug.datastore

import com.android.debug.DebugApplication

/**
 * @author: dr
 * @date: 2021/1/28
 * @description sp data source
 */
object SpManager {
    @JvmStatic
    fun getDataStore(): IDataStoreRepository {
        return DataStoreRepository(DebugApplication.CONTEXT)
    }
}