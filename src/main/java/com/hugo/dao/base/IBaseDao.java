package com.hugo.dao.base;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/4.
 */
public interface IBaseDao {
    /**
     * 保存方法
     * @param entity
     */
    void save(final Object entity);

    /**
     * 修改方法
     * @param entity
     */
    void update(final Object entity);

    /**
     * 删除方法
     * @param entity
     */
    void delete(final Object entity);

    /**
     * 保存/修改方法
     * @param entity
     */
    void saveOrUpdate(final Object entity);

    /**
     * 根据主键查询
     * @param entityClass
     * @param id
     * @return
     */
    <T> T findEntityById(final Class<T> entityClass, final Serializable id);

    /**
     * 获取分页集合
     * @param hql
     * @param pageNo
     * @param pageSize
     * @return
     */
    <T> List<T> getPageList(final String hql, final int pageNo, final int pageSize);

    /**
     * 根据参数获取分页集合
     * @param hql
     * @param pageNo
     * @param pageSize
     * @return
     */
    <T> List<T> getPageListByParamList(final String hql, final List<?> paramList, final int pageNo, final int pageSize);

    /**
     * 获取集合数量
     * @param hql
     * @return
     */
    Integer getCount(final String hql);

    /**
     * 根据参数获取集合数量
     * @param hql
     * @param paramList
     * @return
     */
    Integer getCountByParamList(final String hql, final List<?> paramList);

    /**
     * 根据hql语句查询， 返回对象集合
     * @param hql
     * @return
     */
    <T> List<T> getListByHql(final String hql);

    /**
     * 根据hql语句查询， 返回对象集合
     * @param hql
     * @param paramList
     * @return
     */
    <T> List<T> getListByHql(final String hql, final List<?> paramList);

    /**
     * 根据sql语句查询，返回object数组
     * @param sql
     * @return
     */
    <T> List<T> getListBySql(final String sql);

    /**
     * 根据sql语句查询 ，返回object数组
     * @param sql
     * @param paramList
     * @return
     */
    <T> List<T> getListBySql(final String sql, final List<?> paramList);

    /**
     * 执行hql,如 delete from 对象，update 对象
     * @param hql
     * @param paramList
     */
    void executeHql(final String hql, final List<?> paramList);

    /**
     * 执行sql,如 delete from 表，update 表
     * @param sql
     * @param paramList
     */
    void executeSql(final String sql, final List<?> paramList);

    /**
     * 获取唯一一行
     * @param hql
     * @param paramList
     * @return
     */
    Object getUniqueResult(final String hql, final List<?> paramList);

    /**
     * @param callSql 要调用的存储过程 例如:  {call ProcedureName(?,?,?)}
     * @param pMap 为参数列表map, 其中  key 为 参数名称, value 为object 数组，<br>
     * 其中 object[0] 为参数的实际值 ,  object[1] 为参数类型 ，参数类型 是 java.sql.Types 中常量类型<br>
     * 其中传出参数 必须以 “out” 字符串开头，<br>
     * 传出参数 value 中  object[0] 直接设置为null 即可，但   object[1] 必须设置<br>
     *
     * @return Map<String,Object>  其中map.get("rs") 为 返回的 ResultSet，其他返回值也可用 传入参数名称获取
     * @Description
     *执行存储过程 返回查询列表                                                                                            <br>
     *---------------调用举例1 无结果集返回----------------------------------<br>
     *   1.存储过程<br/>
     *    CREATE OR REPLACE PROCEDURE test1                                <br>
     *        (                                                            <br>
     *         id            VARCHAR2,                                     <br>
     *         out_count OUT INTEGER                                       <br>
     *        )                                                            <br>
     *    AS                                                               <br>
     *    BEGIN                                                            <br>
     *          SELECT count(*) INTO out_count  FROM MCODE WHERE M_ID>=id; <br>
     *    END ;                                                            <br>
     *                                                                     <br>
     *   2.java 调用代码
     *   Map<String, Object[]> m = new HashMap<String, Object[]>();         <br>
     *   m.put("parameterNames", new Object[]{"id","out_count"});//申明参数顺序 <br>
     *	 m.put("id", new Object[]{"1" ,java.sql.Types.CHAR});                <br>
     *	 m.put("out_count", new Object[]{null,java.sql.Types.INTEGER});//输出参数一定要以"out" 开始 <br>
     *	 Map<String, Object> returnMap = dbdglDao.execQueryProcedure("{call test1(?,?)}", m);    <br>
     *	 ResultSet rs = (ResultSet)returnMap.get("rs");   //对于 sybase ,sqlserver 获取结果集,对于 oracle 有些特殊,见下个例子 <br>
     *	 System.out.println(returnMap.get("out_count"));//获取输出参数   <br>
     * -----------------------调用举例2   有结果集返回------------------------<br>
     *    1. 定义返回集 所用游标                                                                                          <br>
     *    create or replace package pkg_emc                                <br>
     *    as                                                               <br>
     *       type list is ref cursor;                                      <br>
     *    end pkg_emc;                                                     <br>
     *    2. 存储过程                                                                                                             <br>
     *    create or replace procedure mcode_list(out_list out pkg_emc.list)<br>
     *     is                                                              <br>
     *    begin                                                            <br>
     *       open out_list for select * from mcode;                        <br>
     *    end mcode_list;                                                  <br>
     *    3.java 调用代码                                                                                                         <br>
     *    Map<String , Object[]> m=new HashMap<String, Object[]>();          <br>
     *    m.put("parameterNames", new Object[]{"out_list"});  //申明参数顺序    <br>
     *	  m.put("out_list",new Object[]{null,oracle.jdbc.OracleTypes.CURSOR});//输出参数一定要以"out" 开始 <br>
     *	  Map<String, Object> returnMap = dbdglDao.execQueryProcedure("{call mcode_list(?)}", m); <br>
     *	  ResultSet rs=(ResultSet)returnMap.get("out_list");               <br>
     *	  while(rs.next()){ system.out.println( rs.getString("M_CODE"));}  <br>
     */
    Map<String, Object> execQueryProcedure(final String callSql, final Map<String, Object[]> pMap);

    /**
     * JdbcTemplate 查询oracle分页方法
     * @param sql
     * @param paramList
     * @param pageNo
     * @param pageSize
     * @param rowMapper
     * @return
     */
    <T> List<T> getPageListByJdbcTemplate(final String sql, final List<?> paramList, final int pageNo, final int pageSize, final RowMapper<T> rowMapper);

    /**
     * JdbcTemplate 查询oracle分页条数
     * @param sql
     * @param paramList
     * @return
     */
    Integer getCountByJdbcTemplate(final String sql, final List<?> paramList);

    /**
     * JdbcTemplate 查询sql语句
     * @param sql
     * @param paramList
     * @param rowMapper
     * @return
     */
    <T> List<T> getListByJdbcTemplate(final String sql, final List<?> paramList, final RowMapper<T> rowMapper);

    /**
     * 执行hql,如 delete from 对象，update 对象
     * @param hql
     * @param pMap
     */
    void executeHql(final String hql, final Map<String, Object> pMap);

    /**
     * 执行sql,如 delete from 表，update 表
     * @param sql
     * @param pMap
     */
    void executeSql(final String sql, final Map<String, Object> pMap);

    /**
     * 执行hql,返回list ,支持 in 参数列表
     * @param hql
     * @param pMap
     * @return
     */
    <T> List<T> getListByHqlParamMap(final String hql, final Map<String, Object> pMap);

    /**
     * 根据参数获取集合数量
     * @param hql
     * @param pMap
     * @return
     */
    Integer getCountByHqlParamMap(final String hql, final Map<String, Object> pMap);

    /**
     * 执行sql,返回list ,支持 in 参数列表
     * @param sql
     * @param pMap
     * @return
     */
    <T> List<T> getListBySqlParamMap(final String sql, final Map<String, Object> pMap);

    /**
     * 执行sql,返回list ,支持 in 参数列表
     * @param sql
     * @param pMap
     * @return
     */
    <T> List<T> getMapBySqlParamMap(final String sql, final Map<String, Object> pMap);

    /**
     * 根据参数获取集合数量
     * @param sql
     * @param pMap
     * @return
     */
    Integer getCountBySqlParamMap(final String sql, final Map<String, Object> pMap);

    /**
     * 获取唯一一行
     * @param hql
     * @param pMap
     * @return
     */
    Object getUniqueResult(final String hql, final Map<String, Object> pMap);

    /**
     * 分页查询方法
     * @param hql
     * @param pMap
     * @param pageNo
     * @param pageSize
     * @return
     */
    <T> List<T> getPageListByParamMap(final String hql, final Map<String, Object> pMap, final int pageNo, final int pageSize);

    /**
     * 分页查询方法
     * @param sql
     * @param pMap
     * @param pageNo
     * @param pageSize
     * @return
     */
    <T> List<T> getPageListByParamMapSQL(final String sql, final Map<String, Object> pMap, final int pageNo, final int pageSize);

    /**
     *
     * @param sql
     * @param pMap
     * @param pageNo
     * @param pageSize
     * @param cls
     * @param <T>
     * @return
     */
    <T> List<T> getPageListByParamMapSQL(final String sql, final Map<String,Object> pMap, final int pageNo, final int pageSize,Class cls);

    /**
     * 执行sql 并返回分页列表
     * @param sql
     * @param c    要返回的实体类
     * @return
     */
    <T> List<T> getPageListByJdbcSql(final String sql, final List<?> paramList, final int pageNo, final int pageSize, final Class<T> c);

    /**
     * @Description 调用举例： dao.getObjectByJdbcTemplate(sql, String.class, new String[]{ootId})
     * @param sql
     * @param c
     * @param args 这个参数可以不传
     * @return
     */
    <T> T getObjectByJdbcTemplate(final String sql, final Class<T> c, final Object[] args);

    /**
     * @Description dao.getListByJdbcTemplate(sql, new String[]{vo.getOotId()});
     * @param sql
     * @param args 这个参数可以不传
     * @return spring的jdbcTemplate.queryForList( sql );
     *         map对应的key就是数据库里面的字段名，value就是要取的值
     */
    List<Map<String, Object>> getListByJdbcTemplate(final String sql, final Object[] args);

    /**
     * 执行sql 并返回列表
     * @param sql
     * @param paramList
     * @param c         要返回的实体类
     * @return
     */
    <T> List<T> getListByJdbcSql(final String sql, final List<?> paramList, final Class<T> c);

    /**
     * 返回一个对象   如:(String, Integer, Boolean.....)
     * @param sql
     * @param c
     * @return
     */
    Object getObjectBySql(final String sql, final Object[] objs, final Class<?> c);

    /**
     * jdbcTemplate.queryForObject 返回对象
     * @param <T>
     * @param sql
     * @param objs
     * @param rowMapper
     * @return
     */
    <T> T queryForObject(final String sql, final Object[] objs, final RowMapper<T> rowMapper);

    /**
     * 返回一个集合
     * @param sql
     * @param c
     * @return
     */
    List<?> queryListBySql(final String sql, final Object[] objs, final Class<?> c);

    int getPageListByParamCount(final String hql, final List<?> paramList);
}
