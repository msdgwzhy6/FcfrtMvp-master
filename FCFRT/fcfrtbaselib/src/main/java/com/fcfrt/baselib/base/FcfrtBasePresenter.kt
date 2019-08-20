package com.fcfrt.baselib.base


import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner

import com.fcfrt.baselib.help.http.BaseScope
import com.rxjava.rxlife.life
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


open class FcfrtBasePresenter<V : IFcfrtBaseView, M : FcfrtBaseModel> : BaseScope {

    var mView: V? = null
    var mModel: M? = null
    var mContext: Context? = null


    constructor (mContext: Context, mView: V, mModel: M,mOwner: LifecycleOwner): super(mOwner) {
        this.mView = mView
        this.mModel = mModel
        this.mContext = mContext
        Observable.interval(1, 1, TimeUnit.SECONDS)
            .life(mOwner) //这里的this 为Scope接口对象
            .subscribe { _/*aLong*/ ->
                //Log.e("FcfrtBasePresenter", "accept aLong=$aLong")
            }
    }

    /*constructor (mContext: AppCompatActivity, mView: V, mModel: M): super(mContext) {
        this.mView = mView
        this.mModel = mModel
        this.mContext = mContext
    }*/
    /*constructor (mContext: Fragment, mView: V, mModel: M): super(mContext) {
        this.mView = mView
        this.mModel = mModel
        this.mContext = mContext
    }*/


}