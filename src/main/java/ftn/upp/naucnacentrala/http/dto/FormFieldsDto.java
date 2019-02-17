package ftn.upp.naucnacentrala.http.dto;

import org.camunda.bpm.engine.form.FormField;

import java.util.List;

public class FormFieldsDto {
    String taskId;
    List<FormField> formFields;

    public FormFieldsDto(String taskId, List<FormField> formFields) {
        super();
        this.taskId = taskId;
        this.formFields = formFields;
    }

    public FormFieldsDto() {}

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<FormField> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<FormField> formFields) {
        this.formFields = formFields;
    }
}
