package com.common.tool.doc.vo;

public class ParamVo {

    private String paramName;

    private String paramType;

    private String paramDesc;

    private String isRequire = "0";

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public String getIsRequire() {
        return isRequire;
    }

    public void setIsRequire(String isRequire) {
        this.isRequire = isRequire;
    }

    @Override
    public String toString() {
        return "ParamVo [paramName=" + paramName + ", paramType=" + paramType + ", paramDesc=" + paramDesc
                + ", isRequire=" + isRequire + "]";
    }

}
