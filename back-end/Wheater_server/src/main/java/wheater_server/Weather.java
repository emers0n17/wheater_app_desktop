package java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 *
 * @author usere
 */


public class Weather {
    private static final String API_KEY = "89b87aef95d01c01e62919c562637648"; // Sua chave de API

    public static void main(String[] args) {
        String cityName = "maputo"; // Substitua pelo nome da cidade desejada

        try {
            String weatherData = getWeatherData(cityName);
            System.out.println("Dados do tempo para " + cityName + ":");
            System.out.println(weatherData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getWeatherData(String cityName) throws IOException {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + API_KEY;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        return response.toString();
    }
}