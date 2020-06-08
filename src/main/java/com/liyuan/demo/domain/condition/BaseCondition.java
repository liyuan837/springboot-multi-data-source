package com.liyuan.demo.domain.condition;

import org.springframework.data.domain.Sort;
import java.util.List;

public abstract class BaseCondition{

    private int pageSize = 0;
    private int pageNum;
    private int skipResults = 0;

    private String orderBy;
    private List<Sort> sortList;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        skipResults = pageSize * pageNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNo) {
        this.pageNum = pageNo;
        skipResults = pageSize * pageNo;
    }

    public int getSkipResults() {
        return skipResults;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<Sort> getSortList() {
        return sortList;
    }

    public void setSortList(List<Sort> sortList) {
        this.sortList = sortList;
    }

    @Override
    public String toString() {
        return this.pageNum + "==" + this.pageSize;
    }

}
