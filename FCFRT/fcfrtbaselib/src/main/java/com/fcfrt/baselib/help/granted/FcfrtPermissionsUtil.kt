package com.fcfrt.baselib.utils.granted

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.content.PermissionChecker

import java.io.Serializable
import java.util.HashMap


/**
 * 项目名称：
 * 类名称：FcfrtPermissionsUtil
 * 类描述：
 * 作者：FCFRT
 * 创建时间： 2019/4/8-15:43
 * 邮箱：FCFRT_ADMIN@163.COM
 * 修改简介：
 */
object FcfrtPermissionsUtil {

    val TAG = "PermissionGrantor"
    private val listenerMap = HashMap<String,FcfrtPermissionListener>()





    /**
     * 申请授权，当用户拒绝时，可以设置是否显示Dialog提示用户，也可以设置提示用户的文本内容
     * @param context
     * @param listener
     * @param permission 需要申请授权的权限
     * @param showTip 当用户拒绝授权时，是否显示提示
     * @param tip 当用户拒绝时要显示Dialog设置
     */
    fun requestPermission( context: Context, listener: FcfrtPermissionListener, vararg permission: String, showTip: Boolean=true, tip: TipInfo?=null ) {

        if (hasPermission(context, *permission)) {
            listener.permissionGranted(permission)
        } else {
            if (Build.VERSION.SDK_INT < 23) {
                listener.permissionDenied(permission)
            } else {
                val key = System.currentTimeMillis().toString()
                listenerMap[key] = listener
                val intent = Intent(context, FcfrtPermissionActivity::class.java)
                intent.putExtra("permission", permission)
                intent.putExtra("key", key)
                intent.putExtra("showTip", showTip)
                intent.putExtra("tip", tip)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                context.startActivity(intent)
            }
        }
        return
    }


    /**
     * 判断权限是否授权
     * @param context
     * @param permissions
     * @return
     */
    fun hasPermission(context: Context, vararg permissions: String): Boolean {

        if (permissions.isEmpty()) {
            return false
        }

        for (per in permissions) {
            val result = PermissionChecker.checkSelfPermission(context, per)
            if (result != PermissionChecker.PERMISSION_GRANTED) {
                return false
            }
        }

        return true
    }

    /**
     * 判断一组授权结果是否为授权通过
     * @param grantResult
     * @return
     */
    fun isGranted(vararg grantResult: Int): Boolean {

        if (grantResult.isEmpty()) {
            return false
        }

        for (result in grantResult) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    /**
     * 跳转到当前应用对应的设置页面
     * @param context
     */
    fun gotoSetting(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:" + context.packageName)
        context.startActivity(intent)
    }

    /**
     *
     * @param key
     * @return
     */
    internal fun fetchListener(key: String): FcfrtPermissionListener? {
        return listenerMap.remove(key)
    }


    class TipInfo(
        internal var title: String?, internal var content: String?, internal var cancel: String?  //取消按钮文本
        , internal var ensure: String?  //确定按钮文本
    ) : Serializable {
        companion object {

            private const val serialVersionUID = 1L
        }
    }
}
