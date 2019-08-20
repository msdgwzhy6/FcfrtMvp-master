package com.fcfrt.guard.mvp.base.bean

import java.io.Serializable

/**
 * 项目名称：
 * 类名称：BaseMode.kt
 * 类描述：mode基础对象构造方法
 * 作者：AlanPaine
 * 创建时间： 2019/8/20-14:15
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
class BaseMode<T> : Serializable {
    var code: String? = null//-1(数据异常)/0(没有数据)/1(获取成功)
    var msg: String? = null//数据异常/没有数据/获取成功
    var data: T? = null//返回结果
}
