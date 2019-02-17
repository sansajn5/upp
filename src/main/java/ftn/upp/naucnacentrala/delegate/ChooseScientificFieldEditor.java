package ftn.upp.naucnacentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ChooseScientificFieldEditor implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Getting chief editor ID...");
        String scientificFieldEditorId = (String)execution.getVariable("chiefEditorId");
        System.out.println(scientificFieldEditorId);

        System.out.println("Setting scientificFieldEditorId as chiefEditors");
        execution.setVariable("scientificFieldEditorId", scientificFieldEditorId);

    }
}
