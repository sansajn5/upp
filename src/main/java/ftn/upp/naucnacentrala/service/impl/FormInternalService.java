package ftn.upp.naucnacentrala.service.impl;

import ftn.upp.naucnacentrala.dto.response.FormFieldsResponse;
import ftn.upp.naucnacentrala.entity.User;
import ftn.upp.naucnacentrala.repository.IUserRepository;
import ftn.upp.naucnacentrala.service.IFormInternalService;
import ftn.upp.naucnacentrala.util.Constants;
import java.util.logging.Logger;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormInternalService implements IFormInternalService {

    @Autowired
    private TaskService _taskService;

    @Autowired
    private FormService _formService;

    private Logger _logger = Logger.getLogger(FormInternalService.class.getName());

    @Autowired
    private IUserRepository _userRepository;

    public FormFieldsResponse getStartingForm(String taskId) {
        Task task = _taskService.createTaskQuery()
            .processInstanceId(taskId)
            .list()
            .get(0);

        return FormFieldsResponse.builder().taskId(task.getId())
            .formFields(_formService.getTaskFormData(task.getId()).getFormFields()).build();
    }

    @Override
    public FormFieldsResponse getFormByTaskId(String taskId) {
        User user = _userRepository.findById(Constants.USER_ID).get();

        _logger.info("Found user");

        Task task = _taskService.createTaskQuery()
            .taskId(taskId)
            .active()
            .taskAssignee(user.getId().toString())
            .list()
            .get(0);

        _logger.info("Found task");

        return FormFieldsResponse.builder().taskId(task.getId())
            .formFields(_formService.getTaskFormData(task.getId()).getFormFields()).build();
    }
}
