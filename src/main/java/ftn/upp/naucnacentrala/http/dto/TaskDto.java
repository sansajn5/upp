package ftn.upp.naucnacentrala.http.dto;

import java.io.Serializable;

public class TaskDto implements Serializable {
    private String taskId;
    private String taskName;

    public TaskDto(String taskId, String taskName){
        this.taskId = taskId;
        this.taskName = taskName;
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
