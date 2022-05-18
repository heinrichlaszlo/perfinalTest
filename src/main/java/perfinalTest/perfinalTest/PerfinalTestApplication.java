package perfinalTest.perfinalTest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import perfinalTest.perfinalTest.Repository.cityRepository;
import perfinalTest.perfinalTest.Repository.travelRepository;
import perfinalTest.perfinalTest.entity.city;
import perfinalTest.perfinalTest.entity.person;

import java.util.List;


@SpringBootApplication
public class PerfinalTestApplication implements CommandLineRunner {

	@Autowired
	perfinalTest.perfinalTest.Repository.personRepository personRepository;

	@Autowired
	travelRepository travelrepository;

	@Autowired
	cityRepository cityrepository;

	public static void main(String[] args) {
		SpringApplication.run(PerfinalTestApplication.class, args);
		System.out.println("starting...");
	}

	@Override
	public void run(String... args) throws Exception
	{
		if(cityrepository.findAll().size() == 0){
			city city1 = new city(1L, "New York", "városi",7169,"40.76","-73.96");
			city city2 = new city( 2L,"Las Vegas", "városi",9762,"36.21","-115.19");
			city city3 = new city( 3L,"Los Angeles", "tengerparti",10100,"34.05","-118.23");
			city city4 = new city( 4L,"Berlin", "városi",1105,"52.38","13.51");
			city city5 = new city( 5L,"Tokyo", "városi",8903,"35.55","139.78");
			city city6 = new city( 6L,"Rio De Janeiro", "tengerparti",10151,"-22.90","-43.16");
			city city7 = new city( 7L,"Sydney", "tengerparti",15588,"-33.93","151.18");
			city city8 = new city( 8L,"Lusaka", "városi",7002,"-15.41","28.29");
			city city9 = new city( 9L,"Cape Town", "tengerparti",9063,"-33.96","18.60");
			city city10 = new city( 10L,"West Palm Beach", "tengerparti",8488,"25.68","-80.09");

			cityrepository.saveAll(List.of(city1,city2,city3,city4,city5,city6,city7,city8,city9,city10));

			person person1 = new person("Kiss Béla");
			person person2 = new person("Sass István");
			person person3 = new person("Tóth Eszter");
			person person4 = new person("Mester Tamás");
			person person5 = new person("Nagy Viktória");

			personRepository.saveAll(List.of(person1,person2,person3,person4,person5));
		}

	}

}



