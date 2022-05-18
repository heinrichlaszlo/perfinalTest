package perfinalTest.perfinalTest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import perfinalTest.perfinalTest.entity.person;
import perfinalTest.perfinalTest.entity.travel;
import perfinalTest.perfinalTest.service.personService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class personController {

    private personService personService;

    public personController(personService personService) {
        this.personService = personService;
    }

    @GetMapping("/getAllPerson")
    public ResponseEntity<List<person>> findallPerson() {
        List<person> persons = personService.findallPerson();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/getPerson/{name}")
    public ResponseEntity<person> getCarById(@PathVariable String name) {
        try {
            return new ResponseEntity<person>(personService.getPersonByName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<person>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/newTravel/{name}")
    public ResponseEntity<String> addNewTravelByName(@PathVariable String name) {
        try {
            personService.addNewTravelByName(name);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
}
