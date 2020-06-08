package com.liyuan.demo.controller;

import com.liyuan.demo.domain.po.user.UserPo;
import com.liyuan.demo.domain.condition.user.UserCondition;
import com.liyuan.demo.domain.response.PageListResponse;
import com.liyuan.demo.form.user.*;
import com.liyuan.demo.vo.user.UserVo;
import com.liyuan.demo.service.UserService;
import com.liyuan.demo.domain.exception.MultiDataSourceException;
import com.liyuan.demo.util.CopyUtil;
import com.liyuan.demo.domain.response.ResponseEntity;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/user")
@Api(value = "/user", description = "平台用户表")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "查询平台用户表",notes = "根据ID查询平台用户表",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<UserVo> query(@ApiParam(value = "主键", required = true)@RequestParam Integer id) throws MultiDataSourceException {
		UserPo po = userService.queryWithValid(id);
		UserVo vo = CopyUtil.transfer(po, UserVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询平台用户表数量",notes = "查询平台用户表数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestBody@Valid UserQueryForm form) throws MultiDataSourceException {
		UserCondition condition = CopyUtil.transfer(form, UserCondition.class);
		int count = userService.queryCount(condition);
		return getSuccessResult(count);
	}

	@ApiOperation(value = "查询平台用户表列表",notes = "查询平台用户表列表",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public ResponseEntity<PageListResponse<UserVo>> queryList(@RequestBody@Valid UserQueryForm form) throws MultiDataSourceException {
		UserCondition condition = CopyUtil.transfer(form, UserCondition.class);
		condition.setPageNum(0);
		condition.setPageSize(Integer.MAX_VALUE);
		List<UserPo> poList = userService.queryList(condition);
		List<UserVo> voList = CopyUtil.transfer(poList, UserVo.class);
		return getSuccessResult(voList);
	}

	@ApiOperation(value = "查询平台用户表列表(带分页)",notes = "查询平台用户表列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public ResponseEntity<PageListResponse<UserVo>> queryPageList(@RequestBody@Valid UserQueryForm form) throws MultiDataSourceException {
		UserCondition condition = CopyUtil.transfer(form, UserCondition.class);
		List<UserVo> voList = new ArrayList<>();
		int count = userService.queryCount(condition);
		if (count > 0) {
			List<UserPo> poList = userService.queryList(condition);
			voList = CopyUtil.transfer(poList, UserVo.class);
		}
		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
	}

	@ApiOperation(value = "新增平台用户表",notes = "新增平台用户表",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<UserVo> add(@RequestBody@Valid UserCreateForm form) throws MultiDataSourceException {
		Date optTime = new Date();
		UserPo po = CopyUtil.transfer(form, UserPo.class);
		userService.insert(po);
		UserVo vo = CopyUtil.transfer(po, UserVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "修改平台用户表",notes = "修改平台用户表",httpMethod = "POST")
	@PostMapping(value = "/update")
	public ResponseEntity update(@RequestBody@Valid UserUpdateForm form) throws MultiDataSourceException {
		Date optTime = new Date();
		UserPo po = CopyUtil.transfer(form, UserPo.class);
		userService.update(po);
		return getSuccessResult();
	}

	@ApiOperation(value = "（单个）删除平台用户表",notes = "删除平台用户表",httpMethod = "POST")
	@PostMapping(value = "/delete")
	public ResponseEntity delete(@RequestBody@Valid UserDeleteForm form) throws MultiDataSourceException {
		userService.delete(form.getId());
		return getSuccessResult();
	}

}