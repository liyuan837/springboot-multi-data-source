package com.liyuan.demo.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

public class PageHelperUtil {

    public static <E> Page<E> startPage(int pageNum, int pageSize,String orderBy) {
        if(orderBy != null){
            return PageHelper.startPage(pageNum,pageSize,orderBy);
        }
        return PageHelper.startPage(pageNum,pageSize,false);
    }
}