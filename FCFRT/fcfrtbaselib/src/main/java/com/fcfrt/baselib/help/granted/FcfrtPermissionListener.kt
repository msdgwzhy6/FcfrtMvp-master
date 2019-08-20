package com.fcfrt.baselib.utils.granted

import androidx.annotation.NonNull


/**
 * 项目名称：
 * 类名称：FcfrtPermissionListener
 * 类描述：
 * 作者：FCFRT
 * 创建时间： 2019/4/8-15:42
 * 邮箱：FCFRT_ADMIN@163.COM
 * 修改简介：
 */
interface FcfrtPermissionListener {

    /**
     * 通过授权
     * @param permission
     */
    fun permissionGranted(@NonNull permission: Array<out String>)

    /**
     * 拒绝授权
     * @param permission
     */
    fun permissionDenied(@NonNull permission: Array<out String>)
}
