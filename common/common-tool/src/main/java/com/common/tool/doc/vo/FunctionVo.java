package com.common.tool.doc.vo;

import java.util.List;

public class FunctionVo {
    private String functionName;

    private String functionMapping;

    private String functionDesc;

    private String functionPkg;

    private String functionMethod;

    private String functionException;

    private List<ParamVo> reqParams;

    private List<ParamVo> resParams;

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionMapping() {
        return functionMapping;
    }

    public void setFunctionMapping(String functionMapping) {
        this.functionMapping = functionMapping;
    }

    public String getFunctionDesc() {
        return functionDesc;
    }

    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
    }

    public List<ParamVo> getReqParams() {
        return reqParams;
    }

    public void setReqParams(List<ParamVo> reqParams) {
        this.reqParams = reqParams;
    }

    public List<ParamVo> getResParams() {
        return resParams;
    }

    public void setResParams(List<ParamVo> resParams) {
        this.resParams = resParams;
    }

    public String getFunctionPkg() {
        return functionPkg;
    }

    public void setFunctionPkg(String functionPkg) {
        this.functionPkg = functionPkg;
    }

    public String getFunctionMethod() {
        return functionMethod;
    }

    public void setFunctionMethod(String functionMethod) {
        this.functionMethod = functionMethod;
    }

    public String getFunctionException() {
        return functionException;
    }

    public void setFunctionException(String functionException) {
        this.functionException = functionException;
    }

    @Override
    public String toString() {
        return "FunctionVo [functionName=" + functionName + ", functionMapping=" + functionMapping + ", functionDesc="
                + functionDesc + ", functionPkg=" + functionPkg + ", functionMethod=" + functionMethod
                + ", functionException=" + functionException + ", reqParams=" + reqParams + ", resParams=" + resParams
                + "]";
    }

}
