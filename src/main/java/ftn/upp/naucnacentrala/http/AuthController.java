package ftn.upp.naucnacentrala.http;

import ftn.upp.naucnacentrala.domain.user.UserService;
import ftn.upp.naucnacentrala.http.dto.FormSubmissionDto;
import ftn.upp.naucnacentrala.http.dto.TaskDto;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private FormService formService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login/{email}/{password}", produces = "application/json")
    public ResponseEntity<?> login(
        @PathVariable final String email,
        @PathVariable final String password
    ) {
//        HashMap<String, Object> map = this.mapListToDto(dto);
//        System.out.println("Login controller");
//        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//        String processInstanceId = task.getProcessInstanceId();
//        runtimeService.setVariable(processInstanceId, "loginData", dto);
//        formService.submitTaskForm(taskId, map);
//        return new ResponseEntity<TaskDto>(new TaskDto(task.getId(), task.getName()),HttpStatus.OK);

        if (userService.checkIfUserExists(email,password)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping(path = "/register/{taskId}", produces = "application/json")
    public ResponseEntity<TaskDto> register(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
        HashMap<String, Object> map = this.mapListToDto(dto);
        System.out.println("Register controller");

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        runtimeService.setVariable(processInstanceId, "registerData", dto);

        formService.submitTaskForm(taskId, map);
        return new ResponseEntity<TaskDto>(new TaskDto(task.getId(), task.getName(), ""),HttpStatus.OK);
    }


    private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list)
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        for(FormSubmissionDto temp : list){
            map.put(temp.getFieldId(), temp.getFieldValue());
        }

        return map;
    }


}
