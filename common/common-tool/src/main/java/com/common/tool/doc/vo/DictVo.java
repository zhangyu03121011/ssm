package com.common.tool.doc.vo;

public class DictVo {

    private String dictName;

    private String dictValue;

    private String dictDesc;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    @Override
    public String toString() {
        return "DictVo [dictName=" + dictName + ", dictValue=" + dictValue + ", dictDesc=" + dictDesc + "]";
    }

}
