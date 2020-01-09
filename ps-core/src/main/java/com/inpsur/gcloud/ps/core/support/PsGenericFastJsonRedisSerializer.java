package com.inpsur.gcloud.ps.core.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class PsGenericFastJsonRedisSerializer implements RedisSerializer<Object> {
    private static final ParserConfig defaultRedisConfig = new ParserConfig();
    private final SerializeFilter[] emptyFilters = new SerializeFilter[0];
    private final String DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public PsGenericFastJsonRedisSerializer() {
    }

    public byte[] serialize(Object object) throws SerializationException {
        if (object == null) {
            return new byte[0];
        } else {
            try {
                return JSON.toJSONBytes(object,SerializeConfig.getGlobalInstance(),emptyFilters,DEFFAULT_DATE_FORMAT,JSON.DEFAULT_GENERATE_FEATURE,new SerializerFeature[]{SerializerFeature.WriteClassName});
            } catch (Exception var3) {
                throw new SerializationException("Could not serialize: " + var3.getMessage(), var3);
            } catch (Throwable var3) {
                throw new SerializationException("Could not serialize: " + var3.getMessage(), var3);
            }
        }
    }

    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes != null && bytes.length != 0) {
            try {
                return JSON.parseObject(new String(bytes, IOUtils.UTF8), Object.class, defaultRedisConfig, new Feature[0]);
            } catch (Exception var3) {
                throw new SerializationException("Could not deserialize: " + var3.getMessage(), var3);
            }
        } else {
            return null;
        }
    }

    static {
        //defaultRedisConfig.setAutoTypeSupport(true);
        defaultRedisConfig.addAccept("org.springframework");
        defaultRedisConfig.addAccept("com.inpsur.gcloud");
    }
}
