package ftn.upp.naucnacentrala.http;

import ftn.upp.naucnacentrala.domain.magazine.Magazine;
import ftn.upp.naucnacentrala.domain.magazine.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/magazine")
public class MagazineController {

    @Autowired
    private MagazineService magazineService;

    @GetMapping("/getAll")
    public List<Magazine> getAllMagazines() {
        return magazineService.getAllMagazines();
    }
}

