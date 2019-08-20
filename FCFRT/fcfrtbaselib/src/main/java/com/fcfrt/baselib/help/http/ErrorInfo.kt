package com.fcfrt.baselib.help.http

import android.text.TextUtils
import android.util.Log
import com.fcfrt.baselib.R
import com.fcfrt.baselib.base.FcfrtBaseApplication
import com.fcfrt.baselib.help.FcfrtNetHelper

import com.fcfrt.baselib.utils.FcfrtToast
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.exception.ParseException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * 项目名称：
 * 类名称：ErrorInfo.kt
 * 类描述：错误回调信息
 * 作者：AlanPaine
 * 创建时间： 2019/8/20-14:13
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
class ErrorInfo {
     var errorCode: Int = 0  //仅指服务器返回的错误码
     var errorMsg: String? = null //错误文案，网络错误、请求失败错误、服务器返回的错误等文案
     var network: Boolean = false//当前网络状态
     var throwable: Throwable //异常信息

    constructor(throwable:Throwable){
        this.throwable = throwable
        if (throwable is UnknownHostException) {//没有网络
            this.errorCode = 505
            if (!FcfrtNetHelper.isNetworkConnected(FcfrtBaseApplication.instance)) {
                this.errorMsg = FcfrtBaseApplication.instance.getString(R.string.network_error)
            } else {
                this.errorMsg = FcfrtBaseApplication.instance.getString(R.string.notify_no_network)
            }
            this.network = false
        } else if (throwable is SocketTimeoutException) {//连接超时
            this.errorCode = 506
            this.errorMsg = FcfrtBaseApplication.instance.getString(R.string.time_out_please_try_again_later)
            this.network = false
        } else if (throwable is TimeoutException) {//请求超时
            this.errorCode = 507
            this.errorMsg = FcfrtBaseApplication.instance.getString(R.string.time_out_please_try_again_later)
            this.network = false
        } else if (throwable is ConnectException) {//网络不给力
            this.errorCode = 508
            this.errorMsg = FcfrtBaseApplication.instance.getString(R.string.esky_service_exception)
            this.network = false
        } else if (throwable is HttpStatusCodeException) {//请求失败异常
            val code = throwable.localizedMessage
            this.errorCode = code?.toInt() ?: 509
            if ("416" == code) {
                errorMsg = "请求范围不符合要求"
            }else if("404"==code){
                errorMsg = "请求接口不存在"
            }else{
                errorMsg = throwable.message
            }
            this.network = true
        } else if (throwable is ParseException) { // ParseException异常表明请求成功，但是数据不正确
            val errorCode = throwable.localizedMessage
            if ("-1" == errorCode) {
                errorMsg = "数据解析失败,请稍后再试"
            } else {
                this.errorCode = errorCode?.toInt() ?: 510
                errorMsg = throwable.message
                if (TextUtils.isEmpty(errorMsg)) errorMsg = errorCode//errorMsg为空，显示errorCode
            }
            this.network = true
        } else {
            this.errorCode = 511
            this.errorMsg = throwable.message
            this.network = true
        }
    }

    fun show(): Boolean {
        FcfrtToast.show(errorMsg.toString())
        return true
    }

    /**
     * @param standbyMsg 备用的提示文案
     */
    fun show(standbyMsg: String): Boolean {
        FcfrtToast.show(if (TextUtils.isEmpty(errorMsg)) standbyMsg else errorMsg.toString())
        return true
    }

    /**
     * @param standbyMsg 备用的提示文案
     */
    fun show(standbyMsg: Int): Boolean {
        FcfrtToast.show(if (TextUtils.isEmpty(errorMsg)) FcfrtBaseApplication.instance.getString(standbyMsg) else errorMsg.toString())
        return true
    }

    fun log(){
        Log.e("ErrorInfo","code:$errorCode msg:$errorMsg")
    }


}