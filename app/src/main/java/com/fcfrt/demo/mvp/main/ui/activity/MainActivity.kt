package com.fcfrt.demo.mvp.main.ui.activity


import com.fcfrt.demo.R
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fcfrt.baselib.base.FcfrtBaseActivity
import com.fcfrt.demo.mvp.bean.Address
import com.fcfrt.demo.mvp.main.contract.CMain
import com.fcfrt.demo.mvp.main.presenter.PMainImpl
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 项目名称：
 * 类名称：MainActivity
 * 类描述：Class Annotation
 * 作者：AlanPaine
 * 创建时间：
 * 邮箱：
 * 修改简介：
 */
class MainActivity : FcfrtBaseActivity<PMainImpl>(), CMain.IVMain {
    private val TAG = MainActivity::class.java.simpleName


    override fun getLayoutId(): Int {
        return R.layout.activity_main

    }


    /**
     * 创建presenter实例
     */
    override fun createPresenter(): PMainImpl {
        return PMainImpl(this, this, this)
    }

    override fun onViewCreated() {
        bt_get.setOnClickListener {
            mPresenter?.getIpInfo(et_text.text.toString())
        }
        bt_post.setOnClickListener {
            mPresenter?.postIpInfo(et_text.text.toString())
        }
    }


    override fun showLoading() {
       Log.e(TAG,"showLoading")
    }

    override fun hideLoading() {
        Log.e(TAG,"hideLoading")

    }

    override fun onSuccess(data: Address) {
        Log.e(TAG,data.city.toString())
        tv_txt.text = "IP:${data.ip}\n国家：${data.country}\n城市：${data.city}\n地区:${data.region}"
    }

    override fun onFailure(msg: String) {
        Log.e(TAG,msg)
    }


    companion object {
        //private const val FCFRT_ID = "data_id"
        fun launch(context: Context/*, id: Int*/) {
            val intent = Intent(context, MainActivity::class.java)
            // intent.putExtra(FCFRT_ID, id)
            context.startActivity(intent)
        }

        fun launchFinish(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            (context as AppCompatActivity).finish()

        }

    }
}
