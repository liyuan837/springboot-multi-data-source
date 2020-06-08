package com.liyuan.demo.domain.response;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class PageListResponse<T> {

    @ApiModelProperty("数据列表")
    private List<T> dataList;

    public List<T> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    @ApiModelProperty("当前页码，第一页为0")
    private int pageNum;
    @ApiModelProperty("每页条数")
    private int pageSize;
    @ApiModelProperty("总条数")
    private long totalCount;

    public PageListResponse(int pageNum, int pageSize, long totalCount, List<T> dataList) {
        this.dataList = dataList;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

}