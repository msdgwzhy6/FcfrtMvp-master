package com.fcfrt.baselib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fcfrt.baselib.help.manager.FcfrtActivityManager
import com.fcfrt.baselib.utils.FcfrtToast


abstract class FcfrtBaseActivity<T:FcfrtBasePresenter<*,*>>: AppCompatActivity() {

    var mPresenter :T?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //onCreate中入栈
        FcfrtActivityManager.instance.addActivity(this)
        beforeSetContentView(savedInstanceState)
        setContentView(getLayoutId())
        onIntentData()//获取数据
        mPresenter=createPresenter()
        onViewCreated()//界面创建完成
    }
    /**
     * 在SetContentView之前进行操作，父类空实现，子类根据需要进行实现
     */
    open fun beforeSetContentView(savedInstanceState: Bundle?) {}
    /**
     * 获取布局id
     */
    abstract fun getLayoutId(): Int

    /**
     * 获取getIntent();数据
     */
    open fun onIntentData() {

    }
    /**
     * 创建presenter实例
     */
    abstract fun createPresenter():T

    /**
     * 界面创建完成
     */
    abstract fun onViewCreated()

    open fun showToast(msg:String){
        FcfrtToast.show(msg)
    }
    open fun showToast(msg:Int){
        FcfrtToast.show(msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        //onDestroy中入移除
        FcfrtActivityManager.instance.removeActivity(this)
    }
}