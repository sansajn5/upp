package ftn.upp.naucnacentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CheckSubscription implements JavaDelegate {


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Checking subscription...");
        System.out.println("Subscription payed!");
        execution.setVariable("subscriptionPayed", true);
    }
}
