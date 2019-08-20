package com.fcfrt.baselib.utils

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.fcfrt.baselib.base.FcfrtBaseApplication
/**
 * 项目名称：
 * 类名称：FcfrtToast.kt
 * 类描述：可在任意线程执行本类方法
 * 作者：AlanPaine
 * 创建时间： 2019/8/20-14:15
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
object FcfrtToast {

    private val mHandler = Handler(Looper.getMainLooper())
    private var mToast: Toast? = null


    fun show(msgResId: Int, timeLong: Boolean = false): Boolean {
        return show(FcfrtBaseApplication.instance.getString(msgResId), timeLong)
    }


    fun show(msg: CharSequence, timeLong: Boolean = false): Boolean {
        return runOnUiThread (Runnable{
            val duration = if (timeLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
            if (mToast == null) {
                mToast = Toast.makeText(FcfrtBaseApplication.instance, msg, duration)
            } else {
                mToast?.duration = duration
                mToast?.setText(msg)
            }
            mToast?.show()
        })
    }

    private fun runOnUiThread(runnable: Runnable): Boolean {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            runnable.run()
        } else {
            mHandler.post(runnable)
        }
        return true
    }
}