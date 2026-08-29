package com.danggai.endfield.assistant

import android.content.Context
import android.webkit.CookieManager
import android.webkit.WebStorage
import android.webkit.WebView
import java.io.File

object WebLoginManager {

    fun clearWebSession(context: Context) {
        try {
            CookieManager.getInstance().removeAllCookies(null)
            CookieManager.getInstance().flush()
        } catch (_: Exception) {
        }

        try {
            WebStorage.getInstance().deleteAllData()
        } catch (_: Exception) {
        }

        try {
            WebView(context).apply {
                clearCache(true)
                clearHistory()
                clearFormData()
                clearSslPreferences()
                destroy()
            }
        } catch (_: Exception) {
        }

        try {
            val dataDir = context.applicationInfo.dataDir
            val webViewDir = File(dataDir, "app_webview")
            if (webViewDir.exists()) {
                webViewDir.deleteRecursively()
            }
        } catch (_: Exception) {
        }

        try {
            context.cacheDir?.deleteRecursively()
        } catch (_: Exception) {
        }
    }
}