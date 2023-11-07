package com.phatnv.widgets.utils

import android.content.Context
object AppDataStoreManagerSingleton {
    private var appDataStoreManager: AppDataStoreManager? = null

    fun initialize(context: Context) {
        if (appDataStoreManager == null) {
            appDataStoreManager = AppDataStoreManager(context)
        }
    }

    fun getInstance(): AppDataStoreManager {
        requireNotNull(appDataStoreManager) { "AppDataStoreManager is not initialized" }
        return appDataStoreManager!!
    }
}