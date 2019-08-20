package com.fcfrt.baselib.help.http

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.GenericLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.rxjava.rxlife.Scope

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
/**
 * 项目名称：
 * 类名称：BaseScope.kt
 * 类描述：
 * 作者：AlanPaine
 * 创建时间： 2019/8/20-14:14
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
 @SuppressLint("RestrictedApi")
 open class BaseScope(owner: LifecycleOwner) : Scope, GenericLifecycleObserver {

    private var mDisposables: CompositeDisposable? = null

    init {
        owner.lifecycle.addObserver(this)
    }


    override fun onScopeStart(d: Disposable) {
        addDisposable(d)
    }

    override fun onScopeEnd() {

    }

    private fun addDisposable(disposable: Disposable) {
        var disposables = mDisposables
        if (disposables == null) {
            mDisposables = CompositeDisposable()
            disposables = mDisposables
        }
        disposables!!.add(disposable)
    }

    private fun dispose() {
        val disposables = mDisposables ?: return
        disposables.dispose()
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        //Activity/Fragment 生命周期回调
        if (event == Lifecycle.Event.ON_DESTROY) {  //Activity/Fragment 销毁
            source.lifecycle.removeObserver(this)
            dispose() //中断RxJava管道
        }
    }
}
