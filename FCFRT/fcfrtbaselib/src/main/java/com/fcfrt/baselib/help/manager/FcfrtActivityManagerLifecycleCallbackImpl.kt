package com.fcfrt.baselib.help.manager

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * 项目名称：
 * 类名称：ActivityManagerLifecycleCallbackImpl
 * 类描述：
 * 作者：FCFRT
 * 创建时间： 2019/4/24-11:26
 * 邮箱：FCFRT_ADMIN@163.COM
 * 修改简介：
 */
class FcfrtActivityManagerLifecycleCallbackImpl : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        FcfrtActivityManager.instance.addActivity(activity)
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        FcfrtActivityManager.instance.removeActivity(activity)
    }
}