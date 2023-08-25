package com.trako.repository;

import org.apache.ibatis.annotations.Mapper;

import com.trako.model.User;

@Mapper //mybatis_annotation
public interface UserMapper {
	void saveUser(User user);
}
