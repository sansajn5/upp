package ftn.upp.naucnacentrala.http;

import ftn.upp.naucnacentrala.domain.magazine.MagazineService;
import ftn.upp.naucnacentrala.domain.user.LoginService;
import ftn.upp.naucnacentrala.domain.user.User;
import ftn.upp.naucnacentrala.domain.user.UserService;
import ftn.upp.naucnacentrala.http.dto.FormFieldsDto;
import ftn.upp.naucnacentrala.http.dto.FormSubmissionDto;
import ftn.upp.naucnacentrala.http.dto.TaskDto;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.Path;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/process")
@CrossOrigin
public class ProcessController {

    public static ProcessInstance pi = null;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Autowired
    private UserService userService;

    @Autowired
    private MagazineService magazineService;

    @GetMapping(path = "/start/{magazineId}", produces = "application/json")
    public ResponseEntity<?> startProcess(@PathVariable Long magazineId) {

        Map<String, Object> variables = new HashMap<String,Object>();
        variables.put("magazineId", magazineId);
        variables.put("userId", LoginService.currentUserId.toString());

        System.out.println("Pokrecem process...");
        pi = runtimeService.startProcessInstanceByKey("Process_1", variables);
//        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
//        System.out.println(task.getName());
//        TaskFormData tfd = formService.getTaskFormData(task.getId());
//
//        List<FormField> properties = tfd.getFormFields();
//
//        return new FormFieldsDto(task.getId(), pi.getId(), properties);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> getAllActiveTasksForLoggedUser() {
        User user = userService.findById(LoginService.currentUserId);

        List<Task> tasks = taskService.createTaskQuery()
            .active()
            .taskAssignee(user.getId().toString())
            .list();

        List<TaskDto> result = new ArrayList<>();
        for(Task task : tasks) {

            final String magazineName = magazineService.getMagazineNameFromId(
                (Long)runtimeService.getVariable(task.getProcessInstanceId(), "magazineId")
            );


            result.add(new TaskDto(
                task.getId(),
                task.getName(),
                magazineName
            ));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTask(
             @PathVariable final String taskId) {
        User user = userService.findById(LoginService.currentUserId);

        Task task;
        try {
            task = taskService.createTaskQuery()
                .taskId(taskId)
                .active()
                .taskAssignee(user.getId().toString())
                .list().get(0);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TaskFormData tfd = formService.getTaskFormData(task.getId());

        List<FormField> properties = tfd.getFormFields();
        final String taskName = task.getName();
        String path = "";
        switch(taskName) {
            case "ScientificWork submission":
                path = "{ \"path\": \"scientificWork/submit\" }";
                break;
            case "ScientificWork check":
                path = "{ \"path\": \"scientificWork/check\" }";
                break;
            default:
        }

        return new ResponseEntity<>(path,HttpStatus.OK);
    }

    @GetMapping("/taskForm/{taskId}")
    public ResponseEntity<?> getTaskFormFields(
        @PathVariable final String taskId
    ) {
        User user = userService.findById(LoginService.currentUserId);

        Task task;
        try {
            task = taskService.createTaskQuery()
                .taskId(taskId)
                .active()
                .taskAssignee(user.getId().toString())
                .list().get(0);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TaskFormData tfd = formService.getTaskFormData(task.getId());
        List<FormField> properties = tfd.getFormFields();
        FormFieldsDto ffd = new FormFieldsDto(task.getId(), properties);

        return new ResponseEntity<>(ffd,HttpStatus.OK);
    }




    @GetMapping(path = "/getNext")
    public TaskDto getNextTask() {
        try{
            Task task =  taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
            if(task!=null){
                System.out.println(task.getName());
                return new TaskDto(task.getId(), task.getName(), "");
            }
        } catch( Exception e) {
            System.out.println("Nema sledeceg taska");
            return new TaskDto(null,"", "");
        }


        return null;
    }

    @PostMapping("submitTask/{taskId}")
    public ResponseEntity<?> submitTask(
        @PathVariable String taskId,
        @RequestBody  List<FormSubmissionDto> formData
    ) {
        User user = userService.findById(LoginService.currentUserId);

        Task task = taskService.createTaskQuery()
            .taskId(taskId)
            .active()
            .taskAssignee(user.getId().toString())
            .list().get(0);
        if(task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Map<String,Object> map = new HashMap<>();

        for(FormSubmissionDto f: formData) {
            map.put(f.getFieldId(),f.getFieldValue());
        }

        runtimeService.setVariables(task.getProcessInstanceId(), map);

        Map<String,Object> map2 = runtimeService.getVariables(task.getProcessInstanceId());

        for(String key: map2.keySet()) {
            System.out.println(key);
            System.out.println("\t"+map2.get(key));
        }

        taskService.complete(taskId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping(path = "/getRegisterForm")
//    public FormFieldsDto getRegisterForm() {
//        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
//        TaskFormData tfd = formService.getTaskFormData(task.getId());
//
//        List<FormField> properties = tfd.getFormFields();
//
//        return new FormFieldsDto(task.getId(), pi.getId(), properties);
//    }
}
