package com.liyuan.demo.aop;

import com.liyuan.demo.config.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataSourceAop {

    @Pointcut("@annotation(com.liyuan.demo.annotation.ReadOnly)" +
            "|| execution(* com.liyuan.demo.mapper..*.select*(..))" +
            "|| execution(* com.liyuan.demo.mapper..*.count*(..))")
    public void readPointcut(){

    }

    @Pointcut("!@annotation(com.liyuan.demo.annotation.ReadOnly)" +
            "&& execution(* com.liyuan.demo.mapper..*.insert*(..))" +
            "|| execution(* com.liyuan.demo.mapper..*.update*(..))" +
            "|| execution(* com.liyuan.demo.mapper..*.delete*(..))")
    public void writePointcut(){

    }

    @Before("readPointcut()")
    public void read(){
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write(){
        DBContextHolder.master();
    }
}
