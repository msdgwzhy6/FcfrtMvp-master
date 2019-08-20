package com.fcfrt.baselib.help.http

import io.reactivex.functions.Consumer

/**
 * 项目名称：
 * 类名称：OnError1.kt
 * 类描述：RxJava 错误回调 ,加入网络异常处理
 * 作者：AlanPaine
 * 创建时间： 2019/8/20-14:13
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
interface OnError1 : Consumer<Throwable> {



    @Throws(Exception::class)
    override fun accept(throwable: Throwable) {
        onFailure(ErrorInfo(throwable))
    }

    @Throws(Exception::class)
    fun onFailure(error: ErrorInfo )
}