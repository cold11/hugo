package com.hugo.common.redis.session;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

/**
 * Created by ohj on 2018/1/9.
 */
public interface ShiroSessionRepository {

  void saveSession(Session session);

  void deleteSession(Serializable sessionId);

  Session getSession(Serializable sessionId);

  Collection<Session> getAllSessions();
}
