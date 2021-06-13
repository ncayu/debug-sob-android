package com.android.debug.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 *     DataStore 文件存放目录: /data/data/<包名>/files/datastore
 */
class DataStoreRepository(val context: Context) : IDataStoreRepository {

    private val dataStoreDir = "DataStore"
    var dataStore: DataStore<Preferences> = context.createDataStore(
            name = dataStoreDir
    )

    override suspend fun putBoolean(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStore.edit { it[key] = value }
    }

    override suspend fun getBoolean(key: Preferences.Key<Boolean>): Flow<Boolean> =
            dataStore.data.catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }.map { preferences ->
                preferences[key] ?: false
            }

    override suspend fun putInt(key: Preferences.Key<Int>, value: Int) {
        dataStore.edit { it[key] = value }
    }

    override suspend fun getInt(key: Preferences.Key<Int>): Flow<Int> =
            dataStore.data.catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }.map { preferences ->
                preferences[key] ?: 0
            }

    override suspend fun putLong(key: Preferences.Key<Long>, value: Long) {
        dataStore.edit { it[key] = value }
    }

    override suspend fun getLong(key: Preferences.Key<Long>): Flow<Long> =
            dataStore.data.catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }.map { preferences ->
                preferences[key] ?: 0
            }

    override suspend fun putFloat(key: Preferences.Key<Float>, value: Float) {
        dataStore.edit { it[key] = value }
    }

    override suspend fun getFloat(key: Preferences.Key<Float>): Flow<Float> =
            dataStore.data.catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }.map { preferences ->
                preferences[key] ?: 0f
            }

    override suspend fun putDouble(key: Preferences.Key<Double>, value: Double) {
        dataStore.edit { it[key] = value }
    }

    override suspend fun getDouble(key: Preferences.Key<Double>): Flow<Double> =
            dataStore.data.catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }.map { preferences ->
                preferences[key] ?: 0.0
            }

    override suspend fun putString(key: Preferences.Key<String>, value: String) {
        dataStore.edit { it[key] = value }
    }

    override fun getString(key: Preferences.Key<String>): Flow<String> =
            dataStore.data.catch {
                // 当读取数据遇到错误时，如果是 `IOException` 异常，发送一个 emptyPreferences，来重新使用
                // 但是如果是其他的异常，最好将它抛出去，不要隐藏问题
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }.map { preferences ->
                preferences[key] ?: ""
            }
}

