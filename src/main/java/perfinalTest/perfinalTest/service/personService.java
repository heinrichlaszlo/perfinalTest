package perfinalTest.perfinalTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perfinalTest.perfinalTest.Repository.cityRepository;
import perfinalTest.perfinalTest.Repository.personRepository;
import perfinalTest.perfinalTest.Repository.travelRepository;
import perfinalTest.perfinalTest.apiconnector.apiConnector;
import perfinalTest.perfinalTest.entity.city;
import perfinalTest.perfinalTest.entity.person;
import perfinalTest.perfinalTest.entity.travel;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class personService {

    @Autowired
    private final personRepository personRepository;

    @Autowired
    private final travelRepository travelrepository;

    @Autowired
    private final cityRepository cityrepository;

    URL regiurl = new URL("https://api.open-meteo.com/v1/forecast?latitude=latitudehelye&longitude=longlatiudehelye&daily=apparent_temperature_max&timezone=Europe%2FLondon");
    URL csapadekURL = new URL("https://api.open-meteo.com/v1/forecast?latitude=cslatitudehelye&longitude=cslonglatiudehelye&daily=precipitation_sum&timezone=Europe%2FLondon");

    public personService(personRepository personRepository, travelRepository travelrepository, cityRepository cityrepository) throws MalformedURLException {
        this.personRepository = personRepository;
        this.travelrepository = travelrepository;
        this.cityrepository = cityrepository;
    }

    public List<person> findallPerson(){
        return personRepository.findAll();
    }

    public person getPersonByName(String name){
        return personRepository.getById(name);
    }

    public String addNewTravelByName(String name) {
        person Person = personRepository.findById(name).get();
        travel travel = new travel();
        String target;
        //a személy aki kapja az utazást
        travel.setPerson(Person);
        Long utso = travelrepository.getMaxDateId(Person.getName());
        Long utso2 = travelrepository.getSecondMaxDateId(Person.getName());;
        apiConnector apiconnector = new apiConnector();
        apiConnector apiconnector2 = new apiConnector();
        List<city> cities = cityrepository.findAll();
        String newUrlString = new String();
        String newCsapadekString = new String();
        int i;
        city jelenlegiCity = new city();
        jelenlegiCity.setKm(0);

        // a megfelelő város kiválasztása
        for (i = 0; i < cities.size() - 1; i++) {
            newUrlString = regiurl.toString().replace("latitudehelye", cities.get(i).getLatitude()).replace("longlatiudehelye", cities.get(i).getLonglatitude());
            apiconnector.getJSONArray(newUrlString);
            newCsapadekString = csapadekURL.toString().replace("cslatitudehelye", cities.get(i).getLatitude()).replace("cslonglatiudehelye", cities.get(i).getLonglatitude());
            if(cities.get(i).getId()!= utso && cities.get(i).getId()!= utso2 && csapadekService.getCsapadekBoolean(apiconnector2.getCsapadek(newCsapadekString)) == false){
            if (cities.get(i).getTipus() == "városi" && (Integer.parseInt(String.valueOf(apiconnector.getAvarageTemperature(apiconnector.getJSONArray(newUrlString)))) > 10 && Integer.parseInt(String.valueOf(apiconnector)) < 30)) {
                if (jelenlegiCity.getKm() < cities.get(i).getKm())
                    jelenlegiCity = (cities.get(i));
            } else {
                if (jelenlegiCity.getKm() < cities.get(i).getKm())
                    jelenlegiCity = (cities.get(i));
            }
            travel.setCity(jelenlegiCity);
        }}

        //dátum
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        travel.setTravelDate(sqlDate);
        if(travel.getCity() != null){
            travelrepository.save(travel);
            System.out.println(Person.getName() + " ide utazik: " + jelenlegiCity.getName());
             return jelenlegiCity.getName();
        }
        /*else{
            //System.out.println("nincs olyan város ahova " + Person.getName() + " eltudna utazni.");
        }
        return target;*/
        return null;
    }


}
