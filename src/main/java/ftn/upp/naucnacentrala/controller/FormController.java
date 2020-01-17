package ftn.upp.naucnacentrala.controller;


import ftn.upp.naucnacentrala.dto.response.FormFieldsResponse;
import ftn.upp.naucnacentrala.service.IFormInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forms")
public class FormController {

    private final IFormInternalService _formInternalService;

    @Autowired
    public FormController(IFormInternalService formInternalService) {
        _formInternalService = formInternalService;
    }

    @GetMapping("/start/{taskId}")
    public FormFieldsResponse getStartForm(@PathVariable String taskId) {
        return _formInternalService.getStartingForm(taskId);
    }

    @GetMapping("/{taskId}")
    public FormFieldsResponse getForm(@PathVariable String taskId) {
        return _formInternalService.getFormByTaskId(taskId);
    }
}
