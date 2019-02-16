package ftn.upp.naucnacentrala.http;

import ftn.upp.naucnacentrala.domain.user.LoginService;
import ftn.upp.naucnacentrala.http.dto.FormFieldsDto;
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

    @GetMapping(path = "/getNext")
    public TaskDto getNextTask() {
        try{
            Task task =  taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
            if(task!=null){
                System.out.println(task.getName());
                return new TaskDto(task.getId(), task.getName());
            }
        } catch( Exception e) {
            System.out.println("Nema sledeceg taska");
            return new TaskDto(null,"");
        }


        return null;
    }

    @GetMapping(path = "/getRegisterForm")
    public FormFieldsDto getRegisterForm() {
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
        TaskFormData tfd = formService.getTaskFormData(task.getId());

        List<FormField> properties = tfd.getFormFields();

        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }
}
