package com.hugo.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by ohj on 2015/3/26.
 */
public class HibernateObjectMapper extends ObjectMapper {
    public HibernateObjectMapper() {

        disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}
