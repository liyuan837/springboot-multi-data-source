package com.liyuan.demo.form.user;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import com.liyuan.demo.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "平台用户表")
public class UserUpdateForm implements Serializable {

	@ApiModelProperty(value = "主键", required = true)
	@NotNull(message = "主键不能为空")
	private Integer id;

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "性别")
	private Integer gender;

	@ApiModelProperty(value = "创建时间,格式为:" + DateUtil.FORMAT)
	@JsonFormat(pattern= DateUtil.FORMAT,timezone="GMT+8")
	private Date createTime;

}