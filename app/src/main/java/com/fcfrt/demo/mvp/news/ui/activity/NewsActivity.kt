package com.fcfrt.demo.mvp.news.ui.activity


import com.fcfrt.demo.R
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.fcfrt.baselib.base.FcfrtBaseActivity
import com.fcfrt.demo.mvp.news.contract.CNews
import com.fcfrt.demo.mvp.news.presenter.PNewsImpl

/**
 * 项目名称：
 * 类名称：NewsActivity
 * 类描述：Class Annotation
 * 作者：Class Author
 * 创建时间：
 * 邮箱：
 * 修改简介：
 */
class NewsActivity : FcfrtBaseActivity<PNewsImpl>(), CNews.IVNews {


    override fun getLayoutId(): Int {
        return R.layout.activity_news

    }


    /**
     * 创建presenter实例
     */
    override fun createPresenter(): PNewsImpl {
        return PNewsImpl(this, this, this)
    }

    override fun onViewCreated() {

    }



    companion object {
        //private const val FCFRT_ID = "data_id"
        fun launch(context: Context/*, id: Int*/) {
            val intent = Intent(context, NewsActivity::class.java)
            // intent.putExtra(FCFRT_ID, id)
            context.startActivity(intent)
        }

        fun launchFinish(context: Context) {
            val intent = Intent(context, NewsActivity::class.java)
            context.startActivity(intent)
            (context as AppCompatActivity).finish()

        }

    }
}
