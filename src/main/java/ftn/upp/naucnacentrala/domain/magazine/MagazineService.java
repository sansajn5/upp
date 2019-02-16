package ftn.upp.naucnacentrala.domain.magazine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagazineService {

    @Autowired
    private MagazineRepository magazineRepository;

    public List<Magazine> getAllMagazines() {
        return magazineRepository.findAll();
    }
}
