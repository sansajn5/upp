package ftn.upp.naucnacentrala;

import ftn.upp.naucnacentrala.domain.magazine.Magazine;
import ftn.upp.naucnacentrala.domain.magazine.MagazineRepository;
import ftn.upp.naucnacentrala.domain.scientificField.ScientificField;
import ftn.upp.naucnacentrala.domain.scientificField.ScientificFieldRepository;
import ftn.upp.naucnacentrala.domain.user.LoginService;
import ftn.upp.naucnacentrala.domain.user.User;
import ftn.upp.naucnacentrala.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ScientificFieldRepository scientificFieldRepository;

	@Autowired
	private MagazineRepository magazineRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		instertUsers();
		insertScientificFields();
		insertMagazines();

	}

	private void instertUsers() {
		final User me = new User(
			"fpetrovic@ymail.com",
			"qweasd",
			"Filip",
			"Petrovic"
		);

		final User jane = new User(
			"jane@gmail.com",
			"qweasd",
			"Ivan",
			"Jane"
		);

		final User lebron = new User(
			"lebron@gmail.com",
			"qweasd",
			"Bojan",
			"Stipic"
		);

		userRepository.saveAndFlush(me);
		userRepository.saveAndFlush(jane);
		userRepository.saveAndFlush(lebron);
	}

	private void insertScientificFields() {
		ScientificField a = new ScientificField("Matematika");
		ScientificField b = new ScientificField("Fizika");
		ScientificField c = new ScientificField("Hemija");

		scientificFieldRepository.save(a);
		scientificFieldRepository.save(b);
		scientificFieldRepository.save(c);

	}

	private void insertMagazines() {
		Set<ScientificField> s = new HashSet<>();
		ScientificField ss = scientificFieldRepository.findByScientificFieldName("Fizika");
		s.add(ss);

		final Magazine first = new Magazine(
			"Politikin Zabavnik",
			299.0,
			userRepository.findByEmail("fpetrovic@ymail.com"),
			s,
			null,
			true
		);

		final Magazine second = new Magazine(
			"Forbes",
			1199.0,
			userRepository.findByEmail("jane@gmail.com"),
			s,
			null,
			false
		);

		magazineRepository.save(first);
		magazineRepository.save(second);
	}

	private void setCurrentUser() {
		LoginService.currentUserId = 1l;
	}
}

