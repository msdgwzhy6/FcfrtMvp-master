package com.fcfrt.baselib.help.http;

import io.reactivex.functions.Consumer;

/**
 * 项目名称：
 * 类名称：OnError.java
 * 类描述：RxJava 错误回调 ,加入网络异常处理
 * 作者：AlanPaine
 * 创建时间： 2019/8/20-14:13
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
public interface OnError extends Consumer<Throwable> {

    @Override
    default void accept(Throwable throwable) throws Exception {
        onFailure(new ErrorInfo(throwable));
    }

    void onFailure(ErrorInfo error) throws Exception;
}