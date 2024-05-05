package wheater_server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {
    private static final String API_KEY = "89b87aef95d01c01e62919c562637648"; // Sua chave de API

    public static void main(String[] args) {
        String cityName = "maputo"; // Substitua pelo nome da cidade desejada
        JSONArray forecastArray = new JSONArray(); // Array para armazenar todos os objetos de previsão

        try {
            String forecastData = getFiveDayForecast(cityName);
            JSONParser parser = new JSONParser();
            JSONObject forecastJson = (JSONObject) parser.parse(forecastData);
            JSONArray list = (JSONArray) forecastJson.get("list");

            for (int i = 0; i < list.size(); i += 8) { // Cada 8 itens correspondem a um dia
                JSONObject dayForecast = (JSONObject) list.get(i);
                JSONObject main = (JSONObject) dayForecast.get("main");
                double tempMin = kelvinToCelsius((double) main.get("temp_min"));
                double tempMax = kelvinToCelsius((double) main.get("temp_max"));
                long humidity = (long) main.get("humidity");
                JSONObject wind = (JSONObject) dayForecast.get("wind");
                double windSpeed = (double) wind.get("speed");

                // Criar um objeto JSON para cada dia e adicionar ao array
                JSONObject dailyWeatherInfo = new JSONObject();
                dailyWeatherInfo.put("cidade", cityName);
                dailyWeatherInfo.put("temperatura_maxima", tempMax);
                dailyWeatherInfo.put("temperatura_minima", tempMin);
                dailyWeatherInfo.put("humidade", humidity);
                dailyWeatherInfo.put("velocidade_do_vento", windSpeed);
                forecastArray.add(dailyWeatherInfo);
            }

            // Escrever o array JSON no arquivo
            try (FileWriter fileWriter = new FileWriter("C:\\Users\\usere\\OneDrive\\Desktop\\Eletron\\Meteorologia\\back-end\\Wheater_server\\src\\main\\java\\tempo.json")) {
                fileWriter.write(forecastArray.toJSONString());
                fileWriter.flush();
            }
        } catch (IOException | ParseException e) {
            System.err.println("Erro ao salvar a previsão do tempo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getFiveDayForecast(String cityName) throws IOException {
        // ... (seu código existente para obter os dados da API)
                String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=" + API_KEY;

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

    private static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }
}


//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class Weather {
//    private static final String API_KEY = "89b87aef95d01c01e62919c562637648"; // Sua chave de API
//
//    public static void main(String[] args) {
//        String cityName = "maputo"; // Substitua pelo nome da cidade desejada
//
//        try {
//            String forecastData = getFiveDayForecast(cityName);
//            System.out.println("Previsão do tempo para os próximos 5 dias em " + cityName + ":");
//            System.out.println(forecastData);
//
//            // Criar um objeto JSON com as informações relevantes
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("city", cityName);
//            jsonObject.put("forecastData", forecastData);
//
//            // Salvar o objeto JSON em um arquivo
//            try (FileWriter fileWriter = new FileWriter("C:\\Users\\usere\\OneDrive\\Desktop\\Eletron\\Meteorologia\\back-end\\Wheater_server\\src\\main\\java\\tempo.json", true)){
//                fileWriter.write(jsonObject.toJSONString());
//                fileWriter.flush(); // Garante que todos os dados sejam escritos no arquivo
//            }
//
//            // Analisar e imprimir a previsão do tempo
//            JSONParser parser = new JSONParser();
//            JSONObject forecastJson = (JSONObject) parser.parse(forecastData);
//            JSONArray list = (JSONArray) forecastJson.get("list");
//            for (int i = 0; i < list.size(); i += 8) { // Cada 8 itens correspondem a um dia
//                JSONObject dayForecast = (JSONObject) list.get(i);
//                JSONObject main = (JSONObject) dayForecast.get("main");
//                double tempMin = kelvinToCelsius((double) main.get("temp_min"));
//                double tempMax = kelvinToCelsius((double) main.get("temp_max"));
//
//                System.out.println("Dia " + ((i / 8) + 1) + ":");
//                System.out.println("Temperatura mínima: " + tempMin + "°C");
//                System.out.println("Temperatura máxima: " + tempMax + "°C");
//            }
//        } catch (IOException | ParseException e) {
//            System.err.println("Erro ao salvar a previsão do tempo: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    private static String getFiveDayForecast(String cityName) throws IOException {
//        // ... (seu código existente para obter os dados da API)
//        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=" + API_KEY;
//
//        URL url = new URL(apiUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        StringBuilder response = new StringBuilder();
//        String line;
//
//        while ((line = reader.readLine()) != null) {
//            response.append(line);
//        }
//
//        reader.close();
//        return response.toString();
//    }
//
//    private static double kelvinToCelsius(double kelvin) {
//        return kelvin - 273.15;
//    }
//}


//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;

//public class Weather {
//    private static final String API_KEY = "89b87aef95d01c01e62919c562637648"; // Sua chave de API
//
//    public static void main(String[] args) {
//        String cityName = "maputo"; // Substitua pelo nome da cidade desejada
//
//        try {
//            String forecastData = getFiveDayForecast(cityName);
//            JSONParser parser = new JSONParser();
//            JSONObject forecastJson = (JSONObject) parser.parse(forecastData);
//            JSONArray list = (JSONArray) forecastJson.get("list");
//
//            // Criar um objeto JSON para armazenar as informações relevantes
//            JSONObject weatherInfo = new JSONObject();
//
//            for (int i = 0; i < list.size(); i += 8) { // Cada 8 itens correspondem a um dia
//                JSONObject dayForecast = (JSONObject) list.get(i);
//                JSONObject main = (JSONObject) dayForecast.get("main");
//                double tempMin = kelvinToCelsius((double) main.get("temp_min"));
//                double tempMax = kelvinToCelsius((double) main.get("temp_max"));
//                long humidity = (long) main.get("humidity");
//                JSONObject wind = (JSONObject) dayForecast.get("wind");
//                double windSpeed = (double) wind.get("speed");
//
//                // Adicionar informações ao objeto JSON
//                weatherInfo.put("cidade", cityName);
//                weatherInfo.put("temperatura_maxima", tempMax);
//                weatherInfo.put("temperatura_minima", tempMin);
//                weatherInfo.put("humidade", humidity);
//                weatherInfo.put("velocidade_do_vento", windSpeed);
//
//                // Salvar o objeto JSON em um arquivo
//                try (FileWriter fileWriter = new FileWriter("C:\\Users\\usere\\OneDrive\\Desktop\\Eletron\\Meteorologia\\back-end\\Wheater_server\\src\\main\\java\\tempo.json", true)) {
//                    fileWriter.write(weatherInfo.toJSONString());
//                    fileWriter.flush(); // Garante que todos os dados sejam escritos no arquivo
//                }
//            }
//        } catch (IOException | ParseException e) {
//            System.err.println("Erro ao salvar as informações do tempo: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    private static String getFiveDayForecast(String cityName) throws IOException {
//                // ... (seu código existente para obter os dados da API)
//        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=" + API_KEY;
//
//        URL url = new URL(apiUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        StringBuilder response = new StringBuilder();
//        String line;
//
//        while ((line = reader.readLine()) != null) {
//            response.append(line);
//        }
//
//        reader.close();
//        return response.toString();
//    }
//
//    private static double kelvinToCelsius(double kelvin) {
//        return kelvin - 273.15;
//    }
//}
