package com.liyuan.demo.domain.condition.user;

import java.io.Serializable;
import java.util.List;
import com.liyuan.demo.domain.condition.BaseCondition;
import lombok.Data;

@Data
public class UserCondition extends BaseCondition implements Serializable {

	/**
	 * 主键
	*/
	private Integer id;
	/**
	 * 主键列表
	*/
	private List<Integer> idList;
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
}