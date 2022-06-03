package perfinalTest.perfinalTest.apiconnector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class apiConnector {

    public JSONArray getJSONArray(String query) {

        JSONObject dataObject = null;
        JSONArray avarageTemperature = null;
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
                dataObject = (JSONObject) parse.parse(String.valueOf(informationString));

                //Get the first JSON object in the JSON array
                JSONObject countryData = (JSONObject) dataObject.get("daily");
                JSONArray temperatureMax = (JSONArray) countryData.get("apparent_temperature_max");

                avarageTemperature = temperatureMax;
                /*
                double total = 0;
                for (int i = 0; i < avarageTemperature.size(); i++) {
                    total = total + Double.parseDouble(String.valueOf(avarageTemperature.get(i)));
                }
                atlag = total / avarageTemperature.size();
*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avarageTemperature;
    }

    public List<Double> getCsapadek(String query) {
        JSONObject dataObjectCsapadek = null;
        List<Double> csapadekLista = new ArrayList<>();
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
                dataObjectCsapadek = (JSONObject) parse.parse(String.valueOf(informationString));

                //Get the first JSON object in the JSON array
                JSONObject countryData = (JSONObject) dataObjectCsapadek.get("daily");
                JSONArray csapadek = (JSONArray) countryData.get("precipitation_sum");

                //csapadekLista = csapadek;

                for (int i = 0; i < csapadek.size(); i++) {
                    csapadekLista.add(Double.valueOf(String.valueOf(csapadek.get(i))));
                }
                /*
                for (int i = 1; i < csapadekLista.size() - 1; i++) {
                    if ((Double.valueOf(String.valueOf(csapadekLista.get(i - 1))) + Double.valueOf(String.valueOf(csapadekLista.get(i)))) > 5 || (Double.valueOf(String.valueOf(csapadekLista.get(i))) + Double.valueOf(String.valueOf(csapadekLista.get(i + 1)))) > 5)
                        return true;
                }
*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return csapadekLista;
    }

    public double getAvarageTemperature(JSONArray avarageTemperature) {
        double atlag = 0;

        /*JSONObject countryData = (JSONObject) dataObject.get("daily");
        JSONArray temperatureMax = (JSONArray) countryData.get("apparent_temperature_max");

        JSONArray avarageTemperature = temperatureMax;*/

        double total = 0;
        for (int i = 0; i < avarageTemperature.size(); i++) {
            total = total + Double.parseDouble(String.valueOf(avarageTemperature.get(i)));
        }
        atlag = total / avarageTemperature.size();

        return atlag;
    }

    public boolean getCsapadekBoolean(JSONArray csapadekLista) {

        /*JSONObject countryData = (JSONObject) dataObjectCsapadek.get("daily");
        JSONArray csapadek = (JSONArray) countryData.get("precipitation_sum");

        JSONArray csapadekLista = csapadek;*/

        for (int i = 1; i < csapadekLista.size() - 1; i++) {
            if ((Double.valueOf(String.valueOf(csapadekLista.get(i - 1))) + Double.valueOf(String.valueOf(csapadekLista.get(i)))) > 5 || (Double.valueOf(String.valueOf(csapadekLista.get(i))) + Double.valueOf(String.valueOf(csapadekLista.get(i + 1)))) > 5)
                return true;
        }
        return false;
    }


}
