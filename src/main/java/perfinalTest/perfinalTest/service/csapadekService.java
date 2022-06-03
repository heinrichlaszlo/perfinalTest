package perfinalTest.perfinalTest.service;

import org.json.simple.JSONArray;

import java.util.List;

public class csapadekService {

    public static final int CSAPADEK_LIMIT = 5;

    public static boolean getCsapadekBoolean(List<Double> csapadekLista) {

        /*JSONObject countryData = (JSONObject) dataObjectCsapadek.get("daily");
        JSONArray csapadek = (JSONArray) countryData.get("precipitation_sum");

        JSONArray csapadekLista = csapadek;*/

        for (int i = 1; i < csapadekLista.size() - 1; i++) {
            if (((csapadekLista.get(i - 1)) + csapadekLista.get(i)) > CSAPADEK_LIMIT
                    || ((csapadekLista.get(i + 1)) + csapadekLista.get(i)) > CSAPADEK_LIMIT)
                return true;
        }
        return false;
    }
}
