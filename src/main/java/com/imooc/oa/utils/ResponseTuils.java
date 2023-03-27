package com.imooc.oa.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseTuils {
    private String code;
    private String message;
    private Map data = new LinkedHashMap();

    /*
     * 默认构造方法,默认是成功
     * */
    public ResponseTuils() {
        this.setCode("0");
        this.setMessage("success");
    }

    /**
     * 调用失败情况
     *
     * @param code
     * @param message
     */
    public ResponseTuils(String code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    /**
     * @param key   组织数据时，给data额外添加key，value。返回this用来链式调用
     * @param value
     * @return
     */
    public ResponseTuils put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    /**
     * json 序列化
     *
     * @return
     */
    public String toStringJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            String json = objectMapper.writeValueAsString(this);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
