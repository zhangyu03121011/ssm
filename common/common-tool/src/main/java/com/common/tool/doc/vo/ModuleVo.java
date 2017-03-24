package com.common.tool.doc.vo;

import java.util.List;

public class ModuleVo {

    private String moduleName;

    private String moduleMapping;

    private List<FunctionVo> functionVos;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<FunctionVo> getFunctionVos() {
        return functionVos;
    }

    public void setFunctionVos(List<FunctionVo> functionVos) {
        this.functionVos = functionVos;
    }

    public String getModuleMapping() {
        return moduleMapping;
    }

    public void setModuleMapping(String moduleMapping) {
        this.moduleMapping = moduleMapping;
    }

    @Override
    public String toString() {
        return "ModuleVo [moduleName=" + moduleName + ", moduleMapping=" + moduleMapping + ", functionVos="
                + functionVos + "]";
    }

}
