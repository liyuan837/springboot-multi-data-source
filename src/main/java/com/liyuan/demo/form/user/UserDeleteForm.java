package com.liyuan.demo.form.user;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "平台用户表")
public class UserDeleteForm implements Serializable {

	@ApiModelProperty(value = "主键", required = true)
	@NotNull(message = "主键不能为空")
	private Integer id;

}