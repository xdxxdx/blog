package com.xdx.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
 * 所有dao基类
 * 
 * @author xdx
 *
 * @param <T>
 * @param <PK>
 */
@Repository("baseDao")
public  class BaseDao<T, PK extends Serializable> {
	private T t;
	
	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	static{
		System.out.println("加载BaseDao");
	}
	private Class<T> enetityClass;//T类型的Class对象
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	// 构造方法，根据实例类自动获取实体类型,这边利用java的反射
	public BaseDao() {
		this.enetityClass = null;
		Class c = getClass();
		System.out.println(c);
		Type t = c.getGenericSuperclass();
		System.out.println(t);
		if (t instanceof ParameterizedType) {
			ParameterizedType p = (ParameterizedType) t;
			Type[] type = p.getActualTypeArguments();
			this.enetityClass = (Class<T>) type[0];
		}
		System.out.println("baseDao实例化"+t);
	}
	/**
	 * 获取实体
	 * 
	 * @param id
	 * @return
	 */
	public T getT(String sql, Object param) {
		return sqlSessionTemplate.selectOne(sql, param);
	}
	/**
	 * 获取map
	 * @param sql
	 * @return
	 */
	public List<Map<String,Object>>findMapList(String sql){
		return sqlSessionTemplate.selectList(sql);
	}
	/**
	 * 不带查询参数的列表
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public List<T> findTList(String sql){
		return sqlSessionTemplate.selectList(sql);
	}
	/**
	 * 根据param获取Map形式返回的list
	 * @param sql
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>>findMapListByPm(String sql,Object param){
		return sqlSessionTemplate.selectList(sql, param);
	}

	/**
	 * 带有参数的列表
	 * 
	 * @param str
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<T> findTListByParam(String sql, Object param) {
		return sqlSessionTemplate.selectList(sql, param);
	}

	/**
	 * 插入一条数据,参数是t
	 * 
	 * @param sql
	 * @param t
	 * @return
	 */
	public int addT(String sql, T t) {
		return sqlSessionTemplate.insert(sql, t);
	}
	/**
	 * 修改一条数据，参数是t
	 * @param sql
	 * @param t
	 * @return
	 */
	public int updateT(String sql,T t){
		return sqlSessionTemplate.update(sql, t);
	}
	/**
	 * 修改
	 */
	public int updateBySql(String sql,Object obj){
		return sqlSessionTemplate.update(sql, obj);
	}
	/**
	 * 删除t,参数是主键
	 * @param sql
	 * @param t
	 * @return
	 */
	public int deleteT(String sql,PK pk){
		return sqlSessionTemplate.delete(sql, pk);
	}
	/**
	 * 删除t，参数任意指定
	 * @param sql
	 * @param obj
	 * @return
	 */
	public int deleteTBySql(String sql,Object obj){
		return sqlSessionTemplate.delete(sql, obj);
	}
	/**
	 * 根据param获取一个对象
	 * @param sql
	 * @param param
	 * @return
	 */
	public Object getObject(String sql,Object param){
		return sqlSessionTemplate.selectOne(sql,param);
	}
	public Object getObject(String sql){
		return sqlSessionTemplate.selectOne(sql);
	}
}
