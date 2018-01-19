package com.hugo.common.redis.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * Created by ohj on 2018/1/9.
 */
public interface ShiroCacheManager extends CacheManager {
  <k, v> Cache<k, v> getCache(String name);

  void destroy();
}

