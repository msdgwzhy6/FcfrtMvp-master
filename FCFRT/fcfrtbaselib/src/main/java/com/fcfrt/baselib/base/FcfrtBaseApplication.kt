package com.fcfrt.baselib.base

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.fcfrt.baselib.help.manager.FcfrtActivityManager

open class FcfrtBaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FcfrtActivityManager.instance.register(this)
        instance = this
    }

    companion object {
        lateinit var instance: FcfrtBaseApplication

    }

}
