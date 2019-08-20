package com.fcfrt.baselib.help

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.fcfrt.baselib.R
import com.fcfrt.baselib.base.FcfrtBaseApplication

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
/**
 * 项目名称：
 * 类名称：FcfrtNetHelper.kt
 * 类描述：网络判断
 * 作者：AlanPaine
 * 创建时间： 2019/8/20-14:15
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
object FcfrtNetHelper {


    fun isNetworkConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable
            }
        }

        return false
    }



}
