import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class WeatherAPIData {

    public static void main(String[] args){
        try {
            Scanner scnr = new Scanner(System.in);
            String city;
            do {
                // Retrieve user input
                System.out.println("===================================");
                System.out.println("Enter City (N to Quit");
                city = scnr.nextLine();
                if (city.equalsIgnoreCase("N")) break;

                // Get location data
                JSONObject cityLocationData = (JSONObject) getLocationData(city);
                double latitude = (double) cityLocationData.get("latitude");
                double longitude = (double) cityLocationData.get("longitude");

                displayWeatherData(latitude, longitude);
            } while (!city.equalsIgnoreCase("N"));

        }catch(Exception e){
            e.printStackTrace();
            }
        }

    private static JSONObject getLocationData(String city){
        city = city.replaceAll(regex:" ", replacement: "+");

        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";

        try {
            // 1. Fetch the API response based on API Link
            HttpURLConnection apiConnection = fetchApiResponse(urlString);

            // check for repsonse status
            //200 - mean that connection was a success
            if (apiConnection.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return null;
            }

            // 2. Read the response and convert store String type
            String jsonResponse = readAPIResponse(apiConnection);

            // 3. Parse the string into a JSON Object
            JSONParser parser = new JSONParser();
            JSONObject resultsJsonObj = (JSONObject) parser.parse(jsonResponse);

            // 4. Retrieve Location Data
            JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
            return (JSONObject) locationData.get(0);

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static void displayWeatherData(double latitude, double longitude){...}
    try{
        // Create a StringBuilder to store the reuslting JSON data
        StringBuilder resultJson = new StringBuilder();

        // Create a Scanner to read form the InputStrema of the HttpURLConnection
        Scanner scnr = new Scanner(apiConnection.getInputStream());

        // Loop through each line in the response and eppend it to the StrinbBuilder
        while (scanner.hasNext()){
            // Read and append the current line to the StringBuilder
            resultJson.append(scanner.nextLine());
        }

        // Close the Scanner to realease resources assocaiated with it
        scnr.close();
        return resultJson.toString();
    } catch (IOException e) {
        // Print the exception details in case of an IOException
        e.printStackTrace();
    }

    // Return null if there was an issue reading the response
    return null;

    private static HttpURLConnection fetchApiResponse(String urlString) {
        try {
            // attempt to create connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            // set request method to get
            conn.setRequestMethod("GET");

            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}






