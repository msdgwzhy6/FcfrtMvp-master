package com.fcfrt.demo.mvp.main.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.fcfrt.demo.mvp.main.contract.CMain
import com.fcfrt.demo.mvp.main.model.MMainImpl
import com.fcfrt.baselib.base.FcfrtBasePresenter
import com.fcfrt.baselib.help.http.OnError
import com.rxjava.rxlife.lifeOnMain
import io.reactivex.functions.Consumer

/**
 * 项目名称：
 * 类名称：PMainImpl
 * 类描述：MainActivity的Presenter
 * 作者：AlanPaine
 * 创建时间：
 * 邮箱：
 * 修改简介：
 */
class PMainImpl(mContext: Context, mView: CMain.IVMain, mOwner: LifecycleOwner) :
    FcfrtBasePresenter<CMain.IVMain, MMainImpl>(mContext, mView, MMainImpl(), mOwner), CMain.IPMain {

    override fun getIpInfo(ip: String) {
        mModel!!.getIpInfo(ip)
            .doOnSubscribe { mView?.showLoading()}
            .doFinally {mView?.hideLoading() }
            .lifeOnMain(this)
            .subscribe(Consumer { data ->
                mView?.onSuccess(data)
            }, OnError { error ->
                error.log()//打印日志
                if (error.network){
                    error.show()
                }else{
                    mView?.onFailure(error.errorMsg.toString())
                }

            })
    }

    override fun postIpInfo(ip: String) {
        mModel!!.getIpInfo(ip)
            .doOnSubscribe { mView?.showLoading()}
            .doFinally {mView?.hideLoading() }
            .lifeOnMain(this)
            .subscribe(Consumer { data ->
                mView?.onSuccess(data)
            }, OnError { error ->
                error.log()//打印日志
                if (error.network){
                    error.show()
                }else{
                    mView?.onFailure(error.errorMsg.toString())
                }

            })
    }

}
