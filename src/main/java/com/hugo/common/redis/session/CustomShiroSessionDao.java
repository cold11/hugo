package com.hugo.common.redis.session;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ohj on 2018/1/9.
 */
public class CustomShiroSessionDao extends AbstractSessionDAO {
  private static Logger logger = LoggerFactory.getLogger(CustomShiroSessionDao.class);
  private ShiroSessionRepository shiroSessionRepository;

  public ShiroSessionRepository getShiroSessionRepository() {
    return shiroSessionRepository;
  }

  public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
    this.shiroSessionRepository = shiroSessionRepository;
  }

  @Override
  public void delete(Session session) {
    if (session == null) {
      logger.error("session or session id is null");
      return;
    }
    Serializable id = session.getId();
    if (id != null) {
      logger.info("delete session----:"+id);
      getShiroSessionRepository().deleteSession(id);
    }
  }

  @Override
  public Collection<Session> getActiveSessions() {
    return getShiroSessionRepository().getAllSessions();
  }

  @Override
  public void update(Session session) throws UnknownSessionException {
    logger.info("update session----更新："+session.getId());
    getShiroSessionRepository().saveSession(session);
  }

  @Override
  protected Serializable doCreate(Session session) {
    Serializable sessionId = this.generateSessionId(session);
    this.assignSessionId(session, sessionId);
    getShiroSessionRepository().saveSession(session);
    logger.info("do create session----建完后："+sessionId);
    return sessionId;
  }

  @Override
  protected Session doReadSession(Serializable sessionId) {
    return getShiroSessionRepository().getSession(sessionId);
  }

}
