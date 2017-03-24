package com.common.tool.doc.vo;

import java.util.List;

public class DictTypeVo {

    private String dictTypeName;

    private List<DictVo> dictVos;

    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName;
    }

    public List<DictVo> getDictVos() {
        return dictVos;
    }

    public void setDictVos(List<DictVo> dictVos) {
        this.dictVos = dictVos;
    }

    @Override
    public String toString() {
        return "DictTypeVo [dictTypeName=" + dictTypeName + ", dictVos=" + dictVos + "]";
    }

}
