package ftn.upp.naucnacentrala.controller;

import ftn.upp.naucnacentrala.dto.request.FieldSubmitRequest;
import ftn.upp.naucnacentrala.dto.response.FormFieldsResponse;
import ftn.upp.naucnacentrala.entity.User;
import ftn.upp.naucnacentrala.repository.IUserRepository;
import ftn.upp.naucnacentrala.service.IProcessService;

import ftn.upp.naucnacentrala.util.Constants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process")
@CrossOrigin
public class ProcessController {

    public static ProcessInstance pi = null;

    @Autowired
    private IProcessService _processService;

    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private TaskService _taskService;

    @Autowired
    private RuntimeService _runtimeService;

    @GetMapping
    public FormFieldsResponse startProcess() {
        return _processService.initProcess();
    }

    @PostMapping("{taskId}")
    public void submitTask(@PathVariable String taskId, @RequestBody List<FieldSubmitRequest> formData) {
        User user = _userRepository.findById(Constants.USER_ID).get();

        Task task = _taskService.createTaskQuery()
            .taskId(taskId)
            .active()
            .taskAssignee(user.getId().toString())
            .list().get(0);

        Map<String, Object> map = new HashMap<>();

        formData.stream().forEach(data -> {
            map.put(data.getFieldId(), data.getFieldValue());
        });

        _runtimeService.setVariables(task.getProcessInstanceId(), map);

        _taskService.complete(taskId);
    }
}
