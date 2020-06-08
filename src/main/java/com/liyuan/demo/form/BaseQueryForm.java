package com.liyuan.demo.form;

import io.swagger.annotations.ApiModelProperty;

public class BaseQueryForm {
    @ApiModelProperty("分页每页条数")
    public Integer pageSize = Integer.valueOf(0);
    @ApiModelProperty("分页页码")
    public Integer pageNum = Integer.valueOf(0);


    public BaseQueryForm() {
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }


    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseQueryForm)) {
            return false;
        } else {
            BaseQueryForm other = (BaseQueryForm)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$pageSize = this.getPageSize();
                    Object other$pageSize = other.getPageSize();
                    if (this$pageSize == null) {
                        if (other$pageSize == null) {
                            break label47;
                        }
                    } else if (this$pageSize.equals(other$pageSize)) {
                        break label47;
                    }

                    return false;
                }

                Object this$pageNum = this.getPageNum();
                Object other$pageNum = other.getPageNum();
                if (this$pageNum == null) {
                    if (other$pageNum != null) {
                        return false;
                    }
                } else if (!this$pageNum.equals(other$pageNum)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseQueryForm;
    }

    public int hashCode() {
        Object $pageSize = this.getPageSize();
        int result = 1 * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        Object $pageNum = this.getPageNum();
        result = result * 59 + ($pageNum == null ? 43 : $pageNum.hashCode());
        return result;
    }
}
