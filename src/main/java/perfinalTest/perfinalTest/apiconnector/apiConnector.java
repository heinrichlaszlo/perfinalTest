package perfinalTest.perfinalTest.apiconnector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class apiConnector {

    public double getJSONArray(String query) {
        double atlag = 0;
        try {
            URL url = new URL(query);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }

                //Close the scanner
                scanner.close();

                //JSON simple library Setup with Maven is used to convert strings to JSON
                JSONParser parse = new JSONParser();
                JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));

                //Get the first JSON object in the JSON array
                JSONObject countryData = (JSONObject) dataObject.get("daily");
                JSONArray temperatureMax = (JSONArray) countryData.get("apparent_temperature_max");

                JSONArray avarageTemperature = temperatureMax;
                double total = 0;
                for (int i = 0; i < avarageTemperature.size(); i++) {
                    total = total + Double.parseDouble(String.valueOf(avarageTemperature.get(i)));
                }
                atlag = total / avarageTemperature.size();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return atlag;
    }
    public boolean getCsapadek(String query) {
        try {
            URL url = new URL(query);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();
                //JSON simple library Setup with Maven is used to convert strings to JSON
                JSONParser parse = new JSONParser();
                JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));

                //Get the first JSON object in the JSON array
                JSONObject countryData = (JSONObject) dataObject.get("daily");
                JSONArray csapadek = (JSONArray) countryData.get("precipitation_sum");

                JSONArray csapadekLista = csapadek;
                for (int i = 1; i < csapadekLista.size()-1; i++) {
                    if((Double.valueOf(String.valueOf(csapadekLista.get(i - 1)))+Double.valueOf(String.valueOf(csapadekLista.get(i)))) >5 || (Double.valueOf(String.valueOf(csapadekLista.get(i)) )+Double.valueOf(String.valueOf(csapadekLista.get(i + 1)) ))> 5)
                      return true;
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
