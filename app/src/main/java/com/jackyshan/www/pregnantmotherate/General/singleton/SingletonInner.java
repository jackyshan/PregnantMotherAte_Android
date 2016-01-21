package com.jackyshan.www.pregnantmotherate.General.singleton;

/**
 * Created by jackyshan on 15/5/8.
 */
public class SingletonInner {
    /**
     * field
     */
//    public PaymentCore paymentCore;

    /**
     * 私有的构造函数
     */
    private SingletonInner() {
//        paymentCore = new PaymentCore();
    }

    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static final SingletonInner instance = new SingletonInner();
    }

    public static SingletonInner getInstance() {
        return SingletonHolder.instance;
    }

    protected void method() {
        System.out.println("SingletonInner");
    }
}