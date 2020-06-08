package com.liyuan.demo.domain.po.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class UserPo implements Serializable {

	/**
	 * 主键
	*/
	private Integer id;
	/**
	 * 姓名
	*/
	private String name;
	/**
	 * 年龄
	*/
	private Integer age;
	/**
	 * 性别
	*/
	private Integer gender;
	/**
	 * 创建时间
	*/
	private Date createTime;
}