package com.liyuan.demo.service.impl;

import com.liyuan.demo.domain.po.user.UserPo;
import com.liyuan.demo.domain.condition.user.UserCondition;
import com.liyuan.demo.mapper.UserMapper;
import com.liyuan.demo.domain.exception.MultiDataSourceException;
import com.liyuan.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserPo, UserCondition, UserMapper> implements UserService {

    @Override
    public UserPo queryWithValid(Object id) throws MultiDataSourceException {
        return super.queryWithValid(id);
    }
}