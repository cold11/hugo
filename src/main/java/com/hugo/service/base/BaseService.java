package com.hugo.service.base;

import com.hugo.dao.base.IBaseDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2016/10/4.
 */
@Service("baseService")
public class BaseService implements IBaseService {

    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private IBaseDao baseDao;

    /**
     * 获取uuid主键策略
     * @return
     */
    public String getUuid() {
        //return (String) UUIDGenerator.getInstance().generate();
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 保存方法
     * @param entity
     */
    public void save(final Object entity) {
        baseDao.save(entity);
    }

    /**
     * 修改方法
     * @param entity
     */
    public void update(final Object entity) {
        baseDao.update(entity);
    }

    /**
     * 删除方法
     * @param entity
     */
    public void delete(final Object entity) {
        baseDao.delete(entity);
    }

    /**
     * 保存/修改方法
     * @param entity
     */
    public void saveOrUpdate(final Object entity) {
        baseDao.saveOrUpdate(entity);
    }

    /**
     * 根据主键查询
     * @param entityClass
     * @param id
     * @return
     */
    public <T> T findEntityById(final Class<T> entityClass, final Serializable id) {
        return baseDao.findEntityById(entityClass, id);
    }

    /**
     * 获取分页集合
     * @param hql
     * @param pageNo
     * @param pageSize
     * @return
     */
    public <T> List<T> getPageList(final String hql, final int pageNo, final int pageSize) {
        return baseDao.getPageList(hql, pageNo, pageSize);
    }

    /**
     * 根据参数获取分页集合
     * @param hql
     * @param pageNo
     * @param pageSize
     * @return
     */
    public <T> List<T> getPageListByParamList(final String hql, final List<?> paramList, final int pageNo, final int pageSize) {
        return baseDao.getPageListByParamList(hql, paramList, pageNo, pageSize);
    }

    /**
     * 获取集合数量
     * @param hql
     * @return
     */
    public Integer getCount(final String hql) {
        return baseDao.getCount(hql);
    }

    /**
     * 根据参数获取集合数量
     * @param hql
     * @param paramList
     * @return
     */
    public Integer getCountByParamList(final String hql, final List<?> paramList) {
        return baseDao.getCountByParamList(hql, paramList);
    }

    /**
     * 根据hql语句查询， 返回对象集合
     * @param hql
     * @return
     */
    public <T> List<T> getListByHql(final String hql) {
        return baseDao.getListByHql(hql);
    }

    /**
     * 根据hql语句查询， 返回对象集合
     * @param hql
     * @param paramList
     * @return
     */
    public <T> List<T> getListByHql(final String hql, final List<?> paramList) {
        return baseDao.getListByHql(hql, paramList);
    }

    /**
     * 根据sql语句查询，返回object数组
     * @param sql
     * @return
     */
    public <T> List<T> getListBySql(final String sql) {
        return baseDao.getListBySql(sql);
    }

    /**
     * 根据sql语句查询 ，返回object数组
     * @param sql
     * @param paramList
     * @return
     */
    public <T> List<T> getListBySql(final String sql, final List<?> paramList) {
        return baseDao.getListBySql(sql, paramList);
    }

    /**
     * 执行hql,如 delete from 对象，update 对象
     * @param hql
     * @param paramList
     */
    public void executeHql(final String hql, final List<?> paramList) {
        baseDao.executeHql(hql, paramList);
    }

    /**
     * 执行sql,如 delete from 表，update 表
     * @param sql
     * @param paramList
     */
    public void executeSql(final String sql, final List<?> paramList) {
        baseDao.executeSql(sql, paramList);
    }

    /**
     * 获取唯一一行
     * @param hql
     * @param paramList
     * @return
     */
    public Object getUniqueResult(final String hql, final List<?> paramList) {
        return baseDao.getUniqueResult(hql, paramList);
    }

    /**
     * 执行存储过程
     * @param callSql
     * @param pMap
     * @return
     */
    public Map<String, Object> execQueryProcedure(final String callSql, final Map<String,Object[]> pMap) {
        return baseDao.execQueryProcedure(callSql, pMap);
    }

    /**
     * JdbcTemplate 查询oracle分页方法
     * @param sql
     * @param paramList
     * @param pageNo
     * @param pageSize
     * @param rowMapper
     * @return
     */
    public <T> List<T> getPageListByJdbcTemplate(final String sql, final List<?> paramList, final int pageNo, final int pageSize, final RowMapper<T> rowMapper) {
        return baseDao.getPageListByJdbcTemplate(sql, paramList, pageNo, pageSize, rowMapper);
    }

    /**
     * JdbcTemplate 查询oracle分页条数
     * @param sql
     * @param paramList
     * @return
     */
    public Integer getCountByJdbcTemplate(final String sql, final List<?> paramList) {
        return baseDao.getCountByJdbcTemplate(sql, paramList);
    }

    /**
     * JdbcTemplate 查询sql语句
     * @param sql
     * @param paramList
     * @param rowMapper
     * @return
     */
    public <T> List<T> getListByJdbcTemplate(final String sql, final List<?> paramList, final RowMapper<T> rowMapper) {
        return baseDao.getListByJdbcTemplate(sql, paramList, rowMapper);
    }

    /**
     * 执行hql,如 delete from 对象，update 对象
     * @param hql
     * @param pMap
     * @return
     */
    public void executeHql(final String hql, final Map<String, Object> pMap) {
        baseDao.executeHql(hql, pMap);
    }

    /**
     * 执行sql,如 delete from 表，update 表
     * @param sql
     * @param pMap
     */
    public void executeSql(final String sql, final Map<String, Object> pMap) {
        baseDao.executeSql(sql, pMap);
    }

    /**
     * 执行hql,返回list ,支持 in 参数列表
     * @param hql
     * @param pMap
     * @return
     */
    public <T> List<T> getListByHqlParamMap(final String hql, final Map<String, Object> pMap) {
        return baseDao.getListByHqlParamMap(hql, pMap);
    }

    /**
     * 根据参数获取集合数量
     * @param hql
     * @param pMap
     * @return
     */
    public Integer getCountByHqlParamMap(final String hql, final Map<String, Object> pMap) {
        return baseDao.getCountByHqlParamMap(hql, pMap);
    }

    /**
     * 执行sql,返回list ,支持 in 参数列表
     * @param sql
     * @param pMap
     * @return
     */
    public <T> List<T> getListBySqlParamMap(final String sql, final Map<String, Object> pMap) {
        return baseDao.getListBySqlParamMap(sql, pMap);
    }

    /**
     * 执行sql,返回list ,支持 in 参数列表
     * @param sql
     * @param pMap
     * @return
     */
    public <T> List<T> getMapBySqlParamMap(final String sql, final Map<String, Object> pMap) {
        return baseDao.getMapBySqlParamMap(sql, pMap);
    }

    /**
     * 根据参数获取集合数量
     * @param sql
     * @param pMap
     * @return
     */
    public Integer getCountBySqlParamMap(final String sql, final Map<String, Object> pMap) {
        return baseDao.getCountBySqlParamMap(sql, pMap);
    }

    /**
     * 获取唯一一行
     * @param hql
     * @param pMap
     * @return
     */
    public Object getUniqueResult(final String hql, final Map<String, Object> pMap) {
        return baseDao.getUniqueResult(hql, pMap);
    }

    /**
     * 分页查询方法
     * @param hql
     * @param pageNo
     * @param pageSize
     * @return
     */
    public <T> List<T> getPageListByParamMap(final String hql, final Map<String, Object> pMap, final int pageNo, final int pageSize) {
        return baseDao.getPageListByParamMap(hql, pMap, pageNo, pageSize);
    }

    /**
     * 分页查询方法 执行sql,返回list ,支持 in 参数列表
     * @param sql
     * @param pMap
     * @param pageNo
     * @param pageSize
     * @return
     */
    public <T> List<T> getPageListByParamMapSQL(final String sql, final Map<String, Object> pMap, final int pageNo, final int pageSize) {
        return baseDao.getPageListByParamMapSQL(sql, pMap, pageNo, pageSize);
    }

    /**
     * 分页查询方法 执行sql,返回list ,支持 in 参数列表
     * @param sql
     * @param pMap
     * @param pageNo
     * @param pageSize
     * @return
     */
    public <T> List<T> getPageListByParamMapSQL(final String sql, final Map<String, Object> pMap, final int pageNo, final int pageSize ,Class cls) {
        return baseDao.getPageListByParamMapSQL(sql, pMap, pageNo, pageSize, cls);
    }

    /**
     * 执行sql 并返回分页列表
     * @param sql
     * @param c    要返回的实体类
     * @return
     */
    public <T> List<T> getPageListByJdbcSql(final String sql, final List<?> paramList, final int pageNo, final int pageSize, final Class<T> c) {
        return baseDao.getPageListByJdbcSql(sql, paramList, pageNo, pageSize, c);
    }

    /**
     * @Description 调用举例： dao.getObjectByJdbcTemplate(sql, String.class, new String[]{ootId})
     * @param sql
     * @param c
     * @param args 这个参数可以不传
     * @return
     */
    public <T> T getObjectByJdbcTemplate(final String sql, final Class<T> c, final Object[] args) {
        return baseDao.getObjectByJdbcTemplate(sql, c, args);
    }

    /**
     * @Description dao.getListByJdbcTemplate(sql, new String[]{vo.getOotId()});
     * @param sql
     * @param args 这个参数可以不传
     * @return spring的jdbcTemplate.queryForList( sql );
     *         map对应的key就是数据库里面的字段名，value就是要取的值
     */
    public List<Map<String, Object>> getListByJdbcTemplate(final String sql, final Object[] args) {
        return baseDao.getListByJdbcTemplate(sql, args);
    }

    /**
     * 执行sql 并返回列表
     * @param sql
     * @param paramList
     * @param c         要返回的实体类
     * @return
     */
    public <T> List<T> getListByJdbcSql(final String sql, final List<?> paramList, final Class<T> c) {
        return baseDao.getListByJdbcSql(sql, paramList, c);
    }

    /**
     * 返回一个对象   如:(String, Integer, Boolean.....)
     * @param sql
     * @param c
     * @return
     */
    public Object getObjectBySql(final String sql, final Object[] objs, final Class<?> c) {
        return baseDao.getObjectBySql(sql, objs, c);
    }

    /**
     * jdbcTemplate.queryForObject 返回对象
     * @param <T>
     * @param sql
     * @param objs
     * @param rowMapper
     * @return
     */
    public <T> T queryForObject(final String sql, final Object[] objs, final RowMapper<T> rowMapper) {
        return baseDao.queryForObject(sql, objs, rowMapper);
    }

    /**
     * 返回一个集合
     * @param sql
     * @param c
     * @return
     */
    public List<?> queryListBySql(final String sql, final Object[] objs, final Class<?> c) {
        return baseDao.queryListBySql(sql, objs, c);
    }

    /**
     * 封装easy ui HQL分页查询
     * @param hql        hql语句
     * @param paramList  参数
     * @param page
     * @param rows
     * @return
     */
    public Map<String, Object> queryPageListByHql(final String hql, final List<?> paramList, final int page, final int rows) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("rows", this.getPageListByParamList(hql, paramList, page, rows));
        data.put("total", this.getCountByParamList(getTotalHql(hql), paramList));
        return data;
    }

    /**
     * 组装SQL分页语句
     * @param sql
     * @return
     */
    private String getTotalSql(final String sql) {
        if(sql.toString().trim().toUpperCase().startsWith("FROM")) {
            return "select count(*) "+sql;
        }else{
            return "select count(*) from ("+sql+") T";

        }
    }

    /**
     * 组装HQL分页语句
     * @param hql
     * @return
     */
    private String getTotalHql(final String hql) {
        if(hql.toString().trim().toUpperCase().startsWith("FROM")) {
            return "select count(*) "+hql;
        }else{
            return "select count(*) "+hql.substring(hql.toString().trim().toUpperCase().indexOf("FROM"));

        }
    }

    /**
     * 封装easy ui SQL分页查询
     * @param sql        sql语句
     * @param paramList  参数
     * @param page
     * @param rows
     * @return
     */
    public Map<String, Object> queryPageListBySql(final String sql, final List<?> paramList, final int page, final int rows) {
        Map<String, Object> data = new HashMap<String, Object>();
        if(page == 0 && rows == 0) {
            data.put("rows", this.getListByJdbcTemplate(sql, paramList, null));
        } else {
            data.put("rows", this.getPageListByJdbcTemplate(sql, paramList, page, rows, null));
        }
        data.put("total", this.getCountByJdbcTemplate(getTotalSql(sql), paramList));
        return data;
    }

    /**
     * 封装easy ui HQL分页查询
     * @param hql   hql语句
     * @param pMap  参数
     * @param page
     * @param rows
     * @return
     */
    public Map<String, Object> queryPageListByHql(final String hql, final Map<String, Object> pMap, final int page, final int rows) {
        Map<String, Object> data = new HashMap<String, Object>();
        if(page == 0 && rows == 0) {
            data.put("rows", this.getListByHqlParamMap(hql, pMap));
        } else {
            data.put("rows", this.getPageListByParamMap(hql, pMap, page, rows));
        }
        data.put("total", this.getCountByHqlParamMap(getTotalHql(hql), pMap));
        return data;
    }

    /**
     * 封装easy ui SQL分页查询
     * @param sql        sql语句
     * @param pMap  参数
     * @param page
     * @param rows
     * @return
     */
    public Map<String, Object> queryPageListBySql(final String sql, final Map<String,Object> pMap, final int page, final int rows) {
        Map<String, Object> data = new HashMap<String, Object>();
        if(page == 0 && rows == 0) {
            data.put("rows", this.getMapBySqlParamMap(sql, pMap));
        } else {
            data.put("rows", this.getPageListByParamMapSQL(sql, pMap, page, rows));
        }
        data.put("total", this.getCountBySqlParamMap(getTotalSql(sql), pMap));
        return data;
    }


    /**
     * 数据sql和总数sql独立查询
     * @param sql
     * @param countSql
     * @param pMap
     * @return
     */
    public Map<String, Object> queryPageListBySql(final String sql, final String countSql, final Map<String, Object> pMap,final Map<String, Object> countMap) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("rows", this.getMapBySqlParamMap(sql, pMap));
        data.put("total", this.getCountBySqlParamMap(countSql, countMap));
        return data;
    }

    /**
     * 数据sql和总数sql独立查询
     * @param sql
     * @param countSql
     * @param paramList
     * @return
     */
    public Map<String, Object> queryPageListBySql(final String sql, final String countSql, final List<?> paramList) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("rows", this.getListByJdbcTemplate(sql, paramList, null));
        data.put("total", this.getCountByJdbcTemplate(countSql, paramList));
        return data;
    }

    @Override
    public int getPageListByParamCount(String hql, List<?> paramList) {
        // TODO Auto-generated method stub
        return baseDao.getPageListByParamCount(hql, paramList);
    }


}

