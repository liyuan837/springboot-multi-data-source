package com.liyuan.demo.config;

import java.util.concurrent.atomic.AtomicInteger;

public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    /**
     * 从库轮询计数
     */
    private static AtomicInteger squence = new AtomicInteger(0);

    public static void set(DBTypeEnum dbType){
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get(){
        return contextHolder.get();
    }

    public static void master(){
        set(DBTypeEnum.MASTER);
        System.out.println("切换到master");
    }

    public static void slave(){
        if (squence.intValue() == Integer.MAX_VALUE) {
            synchronized (squence) {
                if (squence.intValue() == Integer.MAX_VALUE) {
                    squence = new AtomicInteger(0);
                }
            }
        }
        if(squence.getAndIncrement() % 2 == 1){
            set(DBTypeEnum.SLAVE1);
            System.out.println("切换到slave1");
        }else{
            set(DBTypeEnum.SLAVE2);
            System.out.println("切换到slave2");
        }

    }
}
