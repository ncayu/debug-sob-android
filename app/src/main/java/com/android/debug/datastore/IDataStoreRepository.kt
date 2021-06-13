package com.android.debug.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow


interface IDataStoreRepository {

    suspend fun putBoolean(key: Preferences.Key<Boolean>, value: Boolean)
    suspend fun getBoolean(key: Preferences.Key<Boolean>): Flow<Boolean>

    suspend fun putInt(key: Preferences.Key<Int>, value: Int)
    suspend fun getInt(key: Preferences.Key<Int>): Flow<Int>

    suspend fun putLong(key: Preferences.Key<Long>, value: Long)
    suspend fun getLong(key: Preferences.Key<Long>): Flow<Long>

    suspend fun putFloat(key: Preferences.Key<Float>, value: Float)
    suspend fun getFloat(key: Preferences.Key<Float>): Flow<Float>

    suspend fun putDouble(key: Preferences.Key<Double>, value: Double)
    suspend fun getDouble(key: Preferences.Key<Double>): Flow<Double>

    suspend fun putString(key: Preferences.Key<String>, value: String)
    fun getString(key: Preferences.Key<String>): Flow<String>

}