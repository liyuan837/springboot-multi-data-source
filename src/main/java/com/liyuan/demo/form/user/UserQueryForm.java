package com.liyuan.demo.form.user;

import java.io.Serializable;
import java.util.List;
import com.liyuan.demo.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "平台用户表")
public class UserQueryForm extends BaseQueryForm implements Serializable {

	@ApiModelProperty(value = "主键")
	private Integer id;

	@ApiModelProperty(value = "主键列表")
	private List<Integer> idList;

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "性别")
	private Integer gender;

}