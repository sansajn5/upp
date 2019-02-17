package ftn.upp.naucnacentrala.http.dto;

import java.io.Serializable;

public class TaskDto implements Serializable {
    private String taskId;
    private String taskName;
    private String magazineName;

    public TaskDto(
        final String taskId,
        final String taskName,
        final String magazineName
    ){
        this.taskId = taskId;
        this.taskName = taskName;
        this.magazineName = magazineName;
    }

    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
