package com.fcfrt.demo.mvp.main.contract

import com.fcfrt.baselib.base.IFcfrtBaseView
import com.fcfrt.baselib.base.IFcfrtBasePresenter
import com.fcfrt.demo.mvp.bean.Address

/**
 * 项目名称：
 * 类名称：CMain
 * 类描述：MainActivity的Contract
 * 作者：AlanPaine
 * 创建时间：
 * 邮箱：
 * 修改简介：
 */
interface CMain {

    interface IPMain : IFcfrtBasePresenter {
        fun getIpInfo(ip: String)
        fun postIpInfo(ip: String)
    }

    interface IVMain : IFcfrtBaseView {

        fun showLoading()

        fun hideLoading()

        fun onSuccess(data: Address)

        fun onFailure(msg:String)
    }
}
