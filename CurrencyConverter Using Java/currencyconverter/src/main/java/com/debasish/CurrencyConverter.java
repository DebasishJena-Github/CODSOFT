package com.debasish;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {

    private static final String API_KEY = "c811644e5c540f8711e8acae"; // Replace with your actual API key
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Currency Converter!");

        // Input: Base Currency
        System.out.print("Enter the base currency code (e.g., USD, EUR): ");
        String baseCurrency = scanner.next().toUpperCase();

        // Input: Target Currency
        System.out.print("Enter the target currency code (e.g., USD, EUR): ");
        String targetCurrency = scanner.next().toUpperCase();

        // Fetch exchange rates
        double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);
        if (exchangeRate == -1) {
            System.out.println("Error fetching exchange rate. Please check the currency codes and try again.");
            return;
        }

        // Input: Amount to convert
        System.out.print("Enter the amount in " + baseCurrency + ": ");
        double amount = scanner.nextDouble();

        // Perform conversion
        double convertedAmount = convertCurrency(amount, exchangeRate);

        // Display Result
        displayResult(amount, baseCurrency, convertedAmount, targetCurrency);

        scanner.close();
    }

    private static double fetchExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            // Construct the URL correctly
            URL url = new URL(API_URL + baseCurrency);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Check if the response code is 200 (HTTP OK)
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Scanner scanner = new Scanner(url.openStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                if (jsonResponse.has("conversion_rates")) {
                    JSONObject rates = jsonResponse.getJSONObject("conversion_rates");
                    return rates.getDouble(targetCurrency);
                }
            } else {
                System.out.println("Error: HTTP response code " + conn.getResponseCode());
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return -1; // Error case
    }

    private static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }

    private static void displayResult(double amount, String baseCurrency, double convertedAmount, String targetCurrency) {
        System.out.printf("\n--- Conversion Result ---\n");
        System.out.printf("%-20s: %.2f %s\n", "Amount in " + baseCurrency, amount, baseCurrency);
        System.out.printf("%-20s: %.2f %s\n", "Converted Amount in " + targetCurrency, convertedAmount, targetCurrency);
    }
}

