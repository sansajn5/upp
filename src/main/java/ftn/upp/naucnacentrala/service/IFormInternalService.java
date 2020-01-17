package ftn.upp.naucnacentrala.service;

import ftn.upp.naucnacentrala.dto.response.FormFieldsResponse;

public interface IFormInternalService {

    FormFieldsResponse getFormByTaskId(String taskId);

    FormFieldsResponse getStartingForm(String taskId);
}
