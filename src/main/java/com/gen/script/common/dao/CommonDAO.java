package com.gen.script.common.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("commonDAO")
public class CommonDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	protected <T> List<T> selectList(String id){
		return getSqlSession().selectList(id);
	}
	
	protected <T> List<T> selectList(String id, Object obj){
		return getSqlSession().selectList(id, obj);
	}

	protected <T> T selectOne(String id){
		return getSqlSession().selectOne(id);
	}
	
	protected <T> T selectOne(String id, Object obj){
		return getSqlSession().selectOne(id, obj);
	}
	
	protected int insert(String id, Object obj){
		return getSqlSession().insert(id, obj);
	}
	
	protected int delete(String id, Object obj){
		return getSqlSession().delete(id, obj);
	}
	
	protected int update(String id, Object obj){
		return getSqlSession().update(id, obj);
	}
	
	public SqlSessionTemplate getSqlSession() {
		return sqlSessionTemplate;
	}
}
