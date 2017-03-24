package com.common.tool.doc.vo;

import java.util.List;

public class ProjectVo {
    private String projectName;

    private List<ModuleVo> moduleVos;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<ModuleVo> getModuleVos() {
        return moduleVos;
    }

    public void setModuleVos(List<ModuleVo> moduleVos) {
        this.moduleVos = moduleVos;
    }

    @Override
    public String toString() {
        return "ProjectVo [projectName=" + projectName + ", moduleVos=" + moduleVos + "]";
    }
}
