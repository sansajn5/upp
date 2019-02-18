package ftn.upp.naucnacentrala.delegate;

import ftn.upp.naucnacentrala.domain.magazine.Magazine;
import ftn.upp.naucnacentrala.domain.magazine.MagazineRepository;
import ftn.upp.naucnacentrala.domain.scientificField.ScientificField;
import ftn.upp.naucnacentrala.domain.scientificField.ScientificFieldRepository;
import ftn.upp.naucnacentrala.domain.scientificWork.ScientificWork;
import ftn.upp.naucnacentrala.domain.scientificWork.ScientificWorkRepository;
import ftn.upp.naucnacentrala.domain.user.User;
import ftn.upp.naucnacentrala.domain.user.UserRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndexingAndDoi implements JavaDelegate {

    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private ScientificWorkRepository scientificWorkRepository;

    @Autowired
    private ScientificFieldRepository scientificFieldRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {


        final Magazine magazine = magazineRepository.getOne(
            (Long)execution.getVariable("magazineId")
        );

        final ScientificField scientificField = scientificFieldRepository.getOne(
            Long.parseLong((String)execution.getVariable("scientificFieldId"))
        );

        final User author = userRepository.getOne(
            Long.parseLong((String)execution.getVariable("userId"))
        );

        final ScientificWork scientificWork = new ScientificWork(
            (String)execution.getVariable("title"),
            (String)execution.getVariable("keywords"),
            (String)execution.getVariable("abstract"),
            (String)execution.getVariable("content"),
            scientificField,
            author,
            magazine
        );

        System.out.println("Saving new Scientific work to database");
        scientificWorkRepository.save(scientificWork);



        System.out.println("ElasticSearch indexing");
        System.out.println("Giving DOI");
    }
}
