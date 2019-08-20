package com.fcfrt.demo.mvp.main.model

import com.fcfrt.baselib.base.FcfrtBaseModel
import com.fcfrt.demo.mvp.bean.Address
import io.reactivex.Observable
import rxhttp.wrapper.param.RxHttp

/**
 * 项目名称：
 * 类名称：MMainImpl
 * 类描述：MainActivity的Model
 * 作者：AlanPaine
 * 创建时间：
 * 邮箱：
 * 修改简介：
 */
class MMainImpl : FcfrtBaseModel() {
    //发送Get请求
    fun getIpInfo(ip: String): Observable<Address> {
        return RxHttp.get("/service/getIpInfo.php") //Get请求
            .add("ip", ip) //添加参数
            .addHeader("accept", "*/*") //添加请求头
            .addHeader("connection", "Keep-Alive")
            .addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
            .asDataParser(Address::class.java)
    }
    //发送Post请求
    fun postIpInfo(ip: String): Observable<Address> {
        return RxHttp.postForm("/service/getIpInfo.php") //Get请求
            .add("ip", ip) //添加参数
            .addHeader("accept", "*/*") //添加请求头
            .addHeader("connection", "Keep-Alive")
            .addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
            .asDataParser(Address::class.java)
    }
}
