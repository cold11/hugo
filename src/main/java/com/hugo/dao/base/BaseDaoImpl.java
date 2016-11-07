package com.hugo.dao.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Administrator on 2016/10/4.
 */
@Repository("baseDao")
public class BaseDaoImpl implements IBaseDao {
    private Log log = LogFactory.getLog(this.getClass());
    @Resource
    protected SessionFactory sessionFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @description:获取session，事务必须是开启的(Required)，否则获取不到
     * @author ohj
     * @date 2013年9月17日 下午10:19:18
     * @return
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 保存方法
     * @param entity
     */
    public void save(final Object entity) {
        getSession().save(entity);
    }

    /**
     * 修改方法
     * @param entity
     */
    public void update(final Object entity) {
        getSession().update(entity);
    }

    /**
     * 删除方法
     * @param entity
     */
    public void delete(final Object entity) {
        getSession().delete(entity);
    }

    /**
     * 保存/修改方法
     * @param entity
     */
    public void saveOrUpdate(final Object entity) {
        getSession().saveOrUpdate(entity);
    }

    /**
     * 执行hql,如 delete from 对象，update 对象
     * @param hql
     * @param paramList
     */
    public void executeHql(final String hql, final List<?> paramList){
        Query query = getSession().createQuery(hql);
        if(null != paramList && paramList.size() > 0){
            for(int i=0;i<paramList.size();i++){
                query.setParameter(i, paramList.get(i));
            }
        }
        query.executeUpdate();
    }

    /**
     * 执行sql,如 delete from 表，update 表
     * @param sql
     * @param paramList
     */
    public void executeSql(final String sql, final List<?> paramList) {
        Query query = getSession().createSQLQuery(sql);
        if(null != paramList && paramList.size() > 0){
            for(int i=0;i<paramList.size();i++){
                query.setParameter(i, paramList.get(i));
            }
        }
        query.executeUpdate();
    }

    /**
     * 执行hql,如 delete from 对象，update 对象
     * @param hql
     * @param pMap
     * @Description
    示例:<br>
    String hql=" update TFileInfo where templateType=<b>:templateType</b> and id in<b>(:ids)</b>";<br>
    Map<String,Object> m=new HashMap<String, Object>();<br>
    m.put("templateType", "1");<br>
    <b>m.put("ids", new String[]{"aaa","bbb"}); //或 m.put("ids",Arrays.asList("aaa","bbb"));</b><br>
    dao.executeHql(hql, m);<br>
     */
    public void executeHql(final String hql, final Map<String,Object> pMap){
        Query query = getSession().createQuery(hql);
        setParameters(query, pMap);
        query.executeUpdate();
    }

    /**
     * 执行sql,如 delete from 表，update 表
     * @param sql
     * @param pMap
     */
    public void executeSql(final String sql, final Map<String, Object> pMap) {
        Query query = getSession().createSQLQuery(sql);
        setParameters(query, pMap);
        query.executeUpdate();
    }

    /**
     * 根据主键查询
     * @param entityClass
     * @param id
     * @return
     */
    public <T> T findEntityById(final Class<T> entityClass, final Serializable id) {
        return (T) getSession().get(entityClass, id);
    }

    /**
     * 获取分页集合
     * @param hql
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getPageList(final String hql, final int pageNo, final int pageSize){
        Query query=getSession().createQuery(hql);
        query.setFirstResult(pageSize*(pageNo-1));
        query.setMaxResults(pageSize);
        return query.list();
    }

    /**
     * 根据参数获取分页集合
     * @param hql
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getPageListByParamList(final String hql, final List<?> paramList, final int pageNo, final int pageSize){
        Query query=getSession().createQuery(hql);
        if(null != paramList && paramList.size() > 0){
            for(int i=0;i<paramList.size();i++){
                query.setParameter(i, paramList.get(i));
            }
        }
        query.setFirstResult(pageSize*(pageNo-1));
        query.setMaxResults(pageSize);
        return query.list();
    }

    /**
     * 获取集合数量
     * @param hql
     * @return
     */
    public Integer getCount(final String hql){
        Object count= getUniqueResult(hql, Arrays.asList());
        return null == count ? 0 : new Integer(count.toString());
    }

    /**
     * 根据参数获取集合数量
     * @param hql
     * @param paramList
     * @return
     */
    public Integer getCountByParamList(final String hql, final List<?> paramList){
        Object count= getUniqueResult(hql, paramList);
        return null == count ? 0 : new Integer(count.toString());
    }

    /**
     * 根据hql语句查询， 返回对象集合
     * @param hql
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getListByHql(final String hql){
        Query query = getSession().createQuery(hql);
        return query.list();
    }

    /**
     * 根据hql语句查询， 返回对象集合
     * @param hql
     * @param paramList
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getListByHql(final String hql, final List<?> paramList) {
        Query query = getSession().createQuery(hql);
        if(null != paramList && paramList.size() > 0){
            for(int i=0;i<paramList.size();i++){
                query.setParameter(i, paramList.get(i));
            }
        }
        return query.list();
    }

    /**
     * 根据sql语句查询，返回object数组
     * @param sql
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getListBySql(final String sql) {
        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    /**
     * 根据sql语句查询 ，返回object数组
     * @param sql
     * @param paramList
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getListBySql(final String sql, final List<?> paramList) {
        Query query = getSession().createSQLQuery(sql);
        if(null != paramList && paramList.size() > 0){
            for(int i=0;i<paramList.size();i++){
                query.setParameter(i, paramList.get(i));
            }
        }
        return query.list();
    }

    /**
     *获取唯一一行
     * @param hql
     * @param paramList
     * @return
     */
    public Object getUniqueResult(final String hql, final List<?> paramList){
        Query query = getSession().createQuery(hql);
        if(null != paramList && paramList.size() > 0){
            for(int i=0;i<paramList.size();i++){
                query.setParameter(i, paramList.get(i));
            }
        }
        return query.uniqueResult();
    }

    /**
     * @param callSql 要调用的存储过程 例如:  {call ProcedureName(?,?,?)}
     * @param pMap 为参数列表map, 其中  key 为 参数名称, value 为object 数组，<br>
     * 其中 object[0] 为参数的实际值 ,  object[1] 为参数类型 ，参数类型 是 java.sql.Types 中常量类型<br>
     * 其中传出参数 必须以 “out” 字符串开头，<br>
     * 传出参数 value 中  object[0] 直接设置为null 即可，但   object[1] 必须设置<br>
     *
     * @return Map<String,Object>  其中map.get("rs") 为 返回的 ResultSet，其他返回值也可用 传入参数名称获取
     * @Description
     * 执行存储过程 返回查询列表                                                                                            <br>
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
     *   Map<String , Object[]> m=new HashMap<String, Object[]>();         <br>
     *   m.put("parameterNames", new Object[]{"id","out_count"});  //申明参数顺序 <br>
     *	 m.put("id"       ,new Object[]{"1" ,java.sql.Types.CHAR   });           <br>
     *	 m.put("out_count",new Object[]{null,java.sql.Types.INTEGER});//输出参数一定要以"out" 开始 <br>
     *	 Map<String, Object> returnMap = dbdglDao.execQueryProcedure("{call test1(?,?)}", m);    <br>
     *	 ResultSet rs=(ResultSet)returnMap.get("rs");   //对于 sybase ,sqlserver 获取结果集,对于 oracle 有些特殊,见下个例子 <br>
     *	 System.out.println(returnMap.get("out_count"));//获取输出参数   <br>
     * -----------------------调用举例2   有结果集返回------------------------<br>
     *    1. 定义返回集 所用游标                                                                                          <br>
     *    create or replace package pkg_emc                                 <br>
     *    as                                                               <br>
     *       type list is ref cursor;                                      <br>
     *    end pkg_emc;                                                     <br>
     *    2. 存储过程                                                                                                             <br>
     *    create or replace procedure mcode_list(out_list out pkg_emc.list) <br>
     *     is                                                                <br>
     *    begin                                                             <br>
     *       open out_list for select * from mcode;                          <br>
     *    end mcode_list;                                                   <br>
     *    3.java 调用代码                                                                                                         <br>
     *    Map<String , Object[]> m=new HashMap<String, Object[]>();          <br>
     *    m.put("parameterNames", new Object[]{"out_list"});  //申明参数顺序    <br>
     *	  m.put("out_list",new Object[]{null,oracle.jdbc.OracleTypes.CURSOR});//输出参数一定要以"out" 开始 <br>
     *	  Map<String, Object> returnMap = dbdglDao.execQueryProcedure("{call mcode_list(?)}", m); <br>
     *	  ResultSet rs=(ResultSet)returnMap.get("out_list");               <br>
     *	  while(rs.next()){ system.out.println( rs.getString("M_CODE"));}  <br>
     */
    @SuppressWarnings("unchecked")
    public Map<String,Object> execQueryProcedure(final String callSql ,final Map<String,Object[]> pMap){
        try {
            return (Map<String,Object>)jdbcTemplate.execute(callSql, new CallableStatementCallback<Object>() {
                public  Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                    List<String> outParam=new ArrayList<String>();
                    Iterator<?> it=null;
                    Map<String,Object> returnMap=new HashMap<String,Object>();
                    if(null!=pMap && !pMap.isEmpty()){
                        /**
                         * 本来认为 oracle 中 用参数名称 设置 参数值就可以忽略参数位置，但事与愿违
                         * 所以添加参数 parameterNames，用于来说明 参数顺序
                         * 例如:
                         * m.put("parameterNames", new Object[]{"p1","p2","p3","out_p"});
                         * 那么在设置参数是就不会发生位置顺序错误 而导致 参数值设置错误,
                         * 如果只有一个参数 则可以不设置此参数
                         */
                        if(null==pMap.get("parameterNames")){
                            it=pMap.keySet().iterator();
                        }else {
                            List<Object> parameterList = Arrays.asList(pMap.get("parameterNames"));
                            it=parameterList.iterator();
                        }
                        while (it.hasNext()) {
                            String next=(String)it.next();
                            if(next.equalsIgnoreCase("parameterNames")){
                                continue;
                            }
                            else if(!next.startsWith("out")){//存储过程返回的参数名称以out 开头
                                cs.setObject(next, pMap.get(next)[0],(Integer)pMap.get(next)[1] );
                            }else{
                                outParam.add(next);
                                cs.registerOutParameter(next, (Integer)pMap.get(next)[1]);
                            }
                        }
                    }

                    ResultSet rs=cs.executeQuery();
                    returnMap.put("rs", rs);
                    it=outParam.iterator();
                    while(it.hasNext()){
                        String key=(String)it.next();
                        Object o=cs.getObject(key);
                        returnMap.put(key, o);
                    }
                    if(null != cs && !cs.isClosed()) {
                        cs.close();
                    }
                    return returnMap;
                }
            });
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * JdbcTemplate 查询oracle分页方法 需要传RowMapper
     * @param sql
     * @param paramList
     * @param pageNo
     * @param pageSize
     * @param rowMapper
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getPageListByJdbcTemplate(final String sql, final List<?> paramList, final int pageNo, final int pageSize, final RowMapper<T> rowMapper) {
        try {
            String newSql = makeMySqlPagedSql(sql, (pageNo-1)*pageSize, pageSize);
            if(null != rowMapper) {
                return jdbcTemplate.query(newSql, null != paramList ? paramList.toArray() : null, rowMapper);
            }else{
                return (List<T>) jdbcTemplate.queryForList(newSql, null != paramList ? paramList.toArray() : null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * JdbcTemplate 查询oracle分页条数
     * @param sql
     * @param paramList
     * @return
     */
    public Integer getCountByJdbcTemplate(final String sql, final List<?> paramList) {
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, null != paramList ? paramList.toArray() : null);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * jdbc模板查询sql语句, 返回需要的类型
     * @param sql
     * @param paramList
     * @param rowMapper
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getListByJdbcTemplate(final String sql, final List<?> paramList, final RowMapper<T> rowMapper) {
        try {
            if(null != rowMapper) {
                return jdbcTemplate.query(sql, null != paramList ? paramList.toArray() : null, rowMapper);
            }else{
                return (List<T>)jdbcTemplate.queryForList(sql, null != paramList ? paramList.toArray() : null);
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 执行hql,返回list ,支持 in 参数列表
     * @param hql
     * @param pMap
     * @Description
     *  示例:<br>
    String sql=" from TFileInfo where templateType=<b>:templateType</b> and id in<b>(:ids)</b>";<br>
    Map<String,Object> m=new HashMap<String, Object>();<br>
    m.put("templateType", "1");<br>
    <b>m.put("ids", new String[]{"aaa","bbb"}); //或 m.put("ids",Arrays.asList("aaa","bbb"));</b><br>
    dao.getListByParamMap(sql, m);<br>
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getListByHqlParamMap(final String hql, final Map<String,Object> pMap){
        Query query = getSession().createQuery(hql);
        setParameters(query, pMap);
        return query.list();
    }

    /**
     * 根据参数获取集合数量
     * @param hql
     * @param pMap
     * @return
     */
    public Integer getCountByHqlParamMap(final String hql, final Map<String, Object> pMap) {
        Object count= getUniqueResult(hql, pMap);
        return null == count ? 0 : new Integer(count.toString());
    }

    /**
     * 执行sql,返回list ,支持 in 参数列表
     * @param sql
     * @param pMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getListBySqlParamMap(final String sql, final Map<String, Object> pMap) {
        Query query = getSession().createSQLQuery(sql);
        setParameters(query, pMap);
        return query.list();
    }

    /**
     * 执行sql,返回list ,支持 in 参数列表
     * @param sql
     * @param pMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getMapBySqlParamMap(final String sql, final Map<String, Object> pMap) {
        Query query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        setParameters(query, pMap);
        return query.list();
    }

    /**
     * 根据参数获取集合数量
     * @param sql
     * @param pMap
     * @return
     */
    public Integer getCountBySqlParamMap(final String sql, final Map<String, Object> pMap) {
        Query query = getSession().createSQLQuery(sql);
        setParameters(query, pMap);
        Object count = query.uniqueResult();
        return null == count ? 0 : Integer.parseInt(count.toString());
    }

    /**
     * 获取唯一一行
     * @param hql
     * @param pMap 参数集合
     * @return
     * @Description
    示例:<br>
    String sql=" from TFileInfo where templateType=<b>:templateType</b> and id in<b>(:ids)</b>";<br>
    Map<String,Object> m=new HashMap<String, Object>();<br>
    m.put("templateType", "1");<br>
    <b>m.put("ids", new String[]{"aaa","bbb"}); //或 m.put("ids",Arrays.asList("aaa","bbb"));</b><br>
    dao.getUniqueResult(sql, m);<br>
     */
    public Object getUniqueResult(final String hql, final Map<String,Object> pMap){
        Query query = getSession().createQuery(hql);
        setParameters(query, pMap);
        return query.uniqueResult();
    }

    /**
     * 分页查询方法
     * @param hql
     * @param pageNo
     * @param pageSize
     * @return
     * @Description:
     * 示例:<br>
    String sql=" from TFileInfo where templateType=<b>:templateType</b> and id in<b>(:ids)</b>";<br>
    Map<String,Object> m=new HashMap<String, Object>();<br>
    m.put("templateType", "1");<br>
    m.put("ids", new String[]{"aaa","bbb"}); //或 m.put("ids",Arrays.asList("aaa","bbb"));</b>
    dao.getPageListByParamMap(sql, m);<br>
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getPageListByParamMap(final String hql, final Map<String,Object> pMap, final int pageNo, final int pageSize){
        Query query=getSession().createQuery(hql);
        setParameters(query, pMap);
        query.setFirstResult(pageSize*(pageNo-1));
        query.setMaxResults(pageSize);
        return query.list();
    }

    /**
     * 分页查询方法
     * @param sql
     * @param pMap
     * @param pageNo
     * @param pageSize
     * @return
     * @Description:
     * 示例:<br>
    String sql=" from TFileInfo where templateType=<b>:templateType</b> and id in<b>(:ids)</b>";<br>
    Map<String,Object> m=new HashMap<String, Object>();<br>
    m.put("templateType", "1");<br>
    m.put("ids", new String[]{"aaa","bbb"}); //或 m.put("ids",Arrays.asList("aaa","bbb"));</b>
    dao.getPageListByParamMap(sql, m);<br>
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getPageListByParamMapSQL(final String sql, final Map<String,Object> pMap, final int pageNo, final int pageSize){
        Query query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        setParameters(query, pMap);
        query.setFirstResult(pageSize*(pageNo-1));
        query.setMaxResults(pageSize);
        return query.list();
    }

    /**
     * 分页查询方法
     * @param sql
     * @param pMap
     * @param pageNo
     * @param pageSize
     * @return
     * @Description:
     * 示例:<br>
    String sql=" from TFileInfo where templateType=<b>:templateType</b> and id in<b>(:ids)</b>";<br>
    Map<String,Object> m=new HashMap<String, Object>();<br>
    m.put("templateType", "1");<br>
    m.put("ids", new String[]{"aaa","bbb"}); //或 m.put("ids",Arrays.asList("aaa","bbb"));</b>
    dao.getPageListByParamMap(sql, m);<br>
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getPageListByParamMapSQL(final String sql, final Map<String,Object> pMap, final int pageNo, final int pageSize,Class cls){
        Query query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.aliasToBean(cls));
        setParameters(query, pMap);
        query.setFirstResult(pageSize*(pageNo-1));
        query.setMaxResults(pageSize);
        return query.list();
    }

    /**
     * 执行sql 并返回分页列表
     * @param sql
     * @param c    要返回的实体类
     * @return
     */
    @SuppressWarnings({"unchecked" })
    public <T> List<T> getPageListByJdbcSql(final String sql, final List<?> paramList, final int pageNo, final int pageSize, final Class<T> c) {
        Query query = getSession().createSQLQuery(sql).addEntity(c);
        if(null != paramList && paramList.size() > 0){
            for(int i=0;i<paramList.size();i++){
                query.setParameter(i, paramList.get(i));
            }
        }
        query.setFirstResult(pageSize*(pageNo-1));
        query.setMaxResults(pageSize);
        return query.list();
    }

    /**
     * 设置hibernate 查询参数
     * @param query
     * @param pMap
     */
    private void setParameters(final Query query, final Map<String, Object> pMap){
        if(null != pMap && !pMap.isEmpty() ){
            for (String key : pMap.keySet()) {
                Object obj = pMap.get(key);
                if(obj instanceof Collection<?>){
                    query.setParameterList(key, (Collection<?>)obj);
                }else if(obj instanceof Object[]){
                    query.setParameterList(key, (Object[])obj);
                }else{
                    query.setParameter(key, obj);
                }
            }
        }
    }

    /**
     * @Description 调用举例： dao.getObjectByJdbcTemplate(sql, String.class, new String[]{ootId})
     * @param sql
     * @param c
     * @param args 这个参数可以不传
     * @return
     */
    public <T> T getObjectByJdbcTemplate(final String sql, final Class<T> c, final Object[] args) {
        try {
            return jdbcTemplate.queryForObject(sql, c, args);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @Description dao.getListByJdbcTemplate(sql, new String[]{vo.getOotId()});
     * @param sql
     * @param args 这个参数可以不传
     * @return spring的jdbcTemplate.queryForList( sql );
     *         map对应的key就是数据库里面的字段名，value就是要取的值
     */
    public List<Map<String, Object>> getListByJdbcTemplate(final String sql, final Object[] args) {
        try {
            return jdbcTemplate.queryForList(sql, args);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * oracle 三层嵌套分页
     * @param sql
     * @param start
     * @param end
     * @return
     */
    @SuppressWarnings("unused")
    private String makeOraclePagedSql(final String sql, final int start, final int end){
        StringBuffer pageSql = new StringBuffer()
                .append("select * from (")
                .append("select m.*,rownum rn from (")
                .append(sql)
                .append(") m where rownum <= ")
                .append(end)
                .append("where rn > ")
                .append(start);
        return pageSql.toString();
    }

    /**
     * mysql分页
     * @param sql
     * @param start
     * @param end
     * @return
     */
    private String makeMySqlPagedSql(final String sql, final int start, final int end){
        StringBuffer pageSql = new StringBuffer()
                .append("select * from (")
                .append(sql)
                .append(") T limit ")
                .append(start)
                .append(",")
                .append(end);
        return pageSql.toString();
    }

    /**
     * 执行sql 并返回列表
     * @param sql
     * @param paramList
     * @param c         要返回的实体类
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getListByJdbcSql(final String sql, final List<?> paramList, final Class<T> c) {
        Query query = getSession().createSQLQuery(sql).addEntity(c);
        if(null != paramList && paramList.size() > 0){
            for(int i=0;i<paramList.size();i++){
                query.setParameter(i, paramList.get(i));
            }
        }
        return query.list();
    }

    /**
     * 返回一个对象   如:(String, Integer, Boolean.....)
     * @param sql
     * @param c
     * @return
     */
    public Object getObjectBySql(final String sql, final Object[] objs, final Class<?> c) {
        try {
            return jdbcTemplate.queryForObject(sql, objs, c);
        } catch (Exception e) {
            return null;
        }
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
        try {
            return jdbcTemplate.queryForObject(sql, objs, rowMapper);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回一个集合
     * @param sql
     * @param c
     * @return
     */
    public List<?> queryListBySql(final String sql, final Object[] objs, final Class<?> c) {
        try {
            return jdbcTemplate.queryForList(sql, objs, c);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据参数获取分页总数
     * @param hql
     * @param paramList
     * @return
     */
    public int getPageListByParamCount(final String hql, final List<?> paramList){
        Query query=getSession().createQuery(hql);
        if(null != paramList && paramList.size() > 0){
            for(int i=0;i<paramList.size();i++){
                query.setParameter(i, paramList.get(i));
            }
        }
        return query.list().size();
    }
}
