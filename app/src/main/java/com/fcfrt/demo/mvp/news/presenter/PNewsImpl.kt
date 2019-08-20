package com.fcfrt.demo.mvp.news.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.fcfrt.demo.mvp.news.contract.CNews
import com.fcfrt.demo.mvp.news.model.MNewsImpl
import com.fcfrt.baselib.base.FcfrtBasePresenter

/**
 * 项目名称：
 * 类名称：PNewsImpl
 * 类描述：NewsActivity的Presenter
 * 作者：Class Author
 * 创建时间：
 * 邮箱：
 * 修改简介：
 */
class PNewsImpl(mContext: Context, mView: CNews.IVNews, mOwner: LifecycleOwner) :
    FcfrtBasePresenter<CNews.IVNews, MNewsImpl>(mContext, mView, MNewsImpl(), mOwner), CNews.IPNews {

}
