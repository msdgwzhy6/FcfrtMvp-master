package com.fcfrt.baselib.help.http


import android.util.Log
import com.fcfrt.guard.mvp.base.bean.BaseMode
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.AbstractParser
import rxhttp.wrapper.utils.GsonUtil

import java.io.IOException
import java.lang.reflect.Type

/**
 * 项目名称：
 * 类名称：DataPareser.kt
 * 类描述：数据解析器 ,解析完成对Data对象做判断,如果ok,返回数据 T
 * 作者：AlanPaine
 * 创建时间： 2019/8/20-14:14
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
@Parser(name = "DataParser")
class DataPareser<T> : AbstractParser<T> {

     constructor() : super() {}

     constructor(type: Type) : super(type) {}

    /**
     * @param response Http执行结果
     * @return 开发者传入的泛型类型
     * @throws IOException 网络异常、数据异常等，RxJava的观察者会捕获此异常
     */
    @Throws(IOException::class)
    override fun onParse(response: okhttp3.Response): T {
        val content = getResult(response) //从Response中取出Http执行结果
        Log.e("DataPareser",content)
        val type = ParameterizedTypeImpl.get(BaseMode::class.java, mType) //获取泛型类型
        //通过Gson自动解析成Data<T>对象
        val data = GsonUtil.getObject<BaseMode<T>>(content, type) ?: //为空 ，表明数据不正确
        throw ParseException("data parse error", response)

        //跟服务端协议好，code等于0，才代表数据正确,否则，抛出异常
        if (data.code != "0") {
            throw ParseException(data.code.toString(), data.msg, response)
        }
        return data.data
            ?: //为空，有可能是参数错误或者其他原因，导致服务器不能正确给我们data字段数据
            throw ParseException(data.code.toString(), "服务器返回数据异常：${data.msg}", response)
    }

    companion object {
        @JvmStatic
        operator fun <T> get(type: Class<T>): DataPareser<T> {
            return DataPareser(type)
        }
    }
}