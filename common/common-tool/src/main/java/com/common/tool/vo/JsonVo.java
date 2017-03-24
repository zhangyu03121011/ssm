package com.common.tool.vo;

import java.util.List;

public class JsonVo {

    private int key;

    private int parentKey;

    private int rootKey;

    private String value;

    private List<JsonVo> list;

    public int getKey() {
        return key;
    }

    public int getParentKey() {
        return parentKey;
    }

    public void setParentKey(int parentKey) {
        this.parentKey = parentKey;
    }

    public int getRootKey() {
        return rootKey;
    }

    public void setRootKey(int rootKey) {
        this.rootKey = rootKey;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<JsonVo> getList() {
        return list;
    }

    public void setList(List<JsonVo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "JsonVo [key=" + key + ", parentKey=" + parentKey + ", value=" + value + "]";
    }

}
