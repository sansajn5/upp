package ftn.upp.naucnacentrala.delegate;

import ftn.upp.naucnacentrala.domain.magazine.Magazine;
import ftn.upp.naucnacentrala.domain.magazine.MagazineRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckMagazineType implements JavaDelegate {

    @Autowired
    private MagazineRepository magazineRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Usao  u task CheckMagazineType");

        final Long magazineId = (Long)execution.getVariable("magazineId");

        final Magazine magazine = magazineRepository.getOne(magazineId);

        if(magazine.isOpenAccess()){
            execution.setVariable("isOpenAccess", true);
        } else {
            execution.setVariable("isOpenAccess", false);
        }
    }
}
