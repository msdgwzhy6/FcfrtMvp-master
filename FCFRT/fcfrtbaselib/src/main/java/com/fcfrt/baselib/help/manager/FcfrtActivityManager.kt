package com.fcfrt.baselib.help.manager

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import java.util.*
import kotlin.system.exitProcess

/**
 * 项目名称：
 * 类名称：FcfrtActivityManager.kt
 * 类描述：Activity栈任务管理器  通常放在我们自定义Activity的基类中操作
 * 作者：AlanPaine
 * 创建时间： 2019/8/20-13:46
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
class FcfrtActivityManager {
    private val mActivityStack = Stack<Activity>()


    companion object {
        val instance: FcfrtActivityManager by lazy { FcfrtActivityManager() }

    }


    fun register(application: Application, callback: FcfrtActivityManagerLifecycleCallbackImpl) {
        application.registerActivityLifecycleCallbacks(callback)
    }

    fun register(application: Application) {
        register(application, FcfrtActivityManagerLifecycleCallbackImpl())
    }

    fun addActivity(activity: Activity) {
        mActivityStack.push(activity)
    }

    fun removeActivity(activity: Activity) {
        mActivityStack.remove(activity)
    }

    /**
     * finish对应class的所有activity
     *
     * @param cls 要关闭的Activity Class
     */
    fun finishActivity(vararg cls: Class<out Activity>) {
        for (i in mActivityStack.indices.reversed()) {
            val activity = mActivityStack[i]
            for (c in cls) {
                if (c.name == activity.javaClass.name) {
                    finishActivity(activity)
                }
            }
        }
    }


    /**
     * 关闭栈顶的Activity
     */
    fun finishTopActivity() {
        val pop = mActivityStack.pop()
        if (!pop.isFinishing) {
            pop.finish()
        }
    }


    /**
     * finish除白名单以外的所有activity
     *
     * @param activityWhitelist 要保留的activity
     */
    fun finishAllActivityByWhitelist(vararg activityWhitelist: Class<out Activity>) {
        for (i in mActivityStack.indices.reversed()) {
            val activity = mActivityStack[i]
            for (c in activityWhitelist) {
                if (c.name == activity.javaClass.name) {
                    break
                } else {
                    finishActivity(activity)
                }
            }
        }
    }


    /**
     * finish所有activity
     */
    fun finishAllActivity() {
        for (i in mActivityStack.indices.reversed()) {
            val activity = mActivityStack[i]
            finishActivity(activity)
        }
    }


    private fun finishActivity(activity: Activity?) {
        if (activity != null) {
            if (!activity.isFinishing) {
                activity.finish()
                mActivityStack.remove(activity)
            }
        }
    }

    fun getActivityStack(): Stack<Activity> {
        return mActivityStack
    }

    /**
     * @param activity
     * @return 是否存在此activity
     */
    fun isContainsActivity(activity: Activity): Boolean {
        return mActivityStack.contains(activity)
    }


    fun isContainsActivity(activityClass: Class<out Activity>): Boolean {
        for (i in mActivityStack.indices.reversed()) {
            val activity = mActivityStack[i]
            if (activityClass.name == activity.javaClass.name) {
                return true
            }
        }
        return false
    }

    /**
     * @return 已经打开activity的数量
     */
    fun getActivityCount(): Int {
        return mActivityStack.size
    }

    /**
     * 退出应用程序
     */
    fun AppExit(context: Context) {
        try {
            finishAllActivity()
            val activityMgr = context .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.killBackgroundProcesses(context.packageName)
            exitProcess(0)
        } catch (e: Exception) {
        }

    }
}