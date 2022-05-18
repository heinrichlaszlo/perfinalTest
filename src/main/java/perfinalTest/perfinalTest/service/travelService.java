package perfinalTest.perfinalTest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perfinalTest.perfinalTest.Repository.travelRepository;
import perfinalTest.perfinalTest.entity.travel;

import java.util.List;

@Service
public class travelService {

    @Autowired
    private final travelRepository travelrepository;

    public travelService(travelRepository travelrepository) {
        this.travelrepository = travelrepository;
    }

    public List<travel> findallTravel(){
        return travelrepository.findAll();
    }
}
