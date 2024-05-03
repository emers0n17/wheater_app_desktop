package java;

import  org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class WeatherDataExtractor {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("seu_arquivo.json"));

            String cityName = (String) jsonObject.get("name");
            double temp = (double) jsonObject.get("temp");
            double tempMin = (double) jsonObject.get("temp_min");
            double tempMax = (double) jsonObject.get("temp_max");

            System.out.println("Cidade: " + cityName);
            System.out.println("Temperatura atual: " + temp + "°C");
            System.out.println("Temperatura mínima: " + tempMin + "°C");
            System.out.println("Temperatura máxima: " + tempMax + "°C");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
