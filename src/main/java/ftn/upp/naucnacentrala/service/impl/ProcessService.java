package ftn.upp.naucnacentrala.service.impl;

import ftn.upp.naucnacentrala.dto.response.FormFieldsResponse;
import ftn.upp.naucnacentrala.service.IProcessService;
import ftn.upp.naucnacentrala.util.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessService implements IProcessService {

    @Autowired
    private RuntimeService _runtimeService;

    @Autowired
    private TaskService _taskService;

    @Autowired
    private FormService _formService;

    public static ProcessInstance pi = null;

    private final Logger _logger = Logger.getLogger(ProcessService.class.getName());

    @Override
    public FormFieldsResponse initProcess() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userId", Constants.USER_ID.toString());
        _logger.info("Starting process");
        pi = _runtimeService.startProcessInstanceByKey(Constants.PROCESS_ID);
        _logger.info("Process started");
        Task task = _taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);

        return FormFieldsResponse.builder().taskId(task.getId())
            .formFields(_formService.getTaskFormData(task.getId()).getFormFields()).build();
    }
}
