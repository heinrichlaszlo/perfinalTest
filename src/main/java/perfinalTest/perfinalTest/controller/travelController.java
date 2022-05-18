package perfinalTest.perfinalTest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import perfinalTest.perfinalTest.entity.travel;
import perfinalTest.perfinalTest.service.travelService;

import java.util.List;

@RestController
@RequestMapping("/travel")
public class travelController {

    private travelService travelService;

    public travelController(travelService travelService) {
        this.travelService = travelService;
    }

    @GetMapping("/getAllTravel")
    public ResponseEntity<List<travel>> findallTravel() {
        List<travel> travels = travelService.findallTravel();
        return new ResponseEntity<>(travels, HttpStatus.OK);
    }

}
