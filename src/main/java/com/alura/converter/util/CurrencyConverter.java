package com.alura.converter.util;

import com.alura.converter.enums.CurrencyUnit;
import com.alura.converter.dto.CurrencyDTO;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class CurrencyConverter {

    private Double amount;
    private String from;
    private String to;

    private static final CurrencyConverter instance = new CurrencyConverter();

    private static final String API_URL = "https://api.apilayer.com/fixer/";

    private CurrencyConverter() {

    }

    public void setProperties(Double amount, CurrencyUnit from, CurrencyUnit to) {
        this.amount = amount;
        this.from = from.getUnitName();
        this.to = to.getUnitName();
    }

    public static CurrencyConverter getInstance() {
        return instance;
    }

    public Double Convert() throws IOException {

        if (Objects.equals(from, to)) {
            return amount;
        }

        String urlSb = API_URL + "convert?to=" + this.to + "&from=" + this.from + "&amount=" + this.amount;
        URL url = new URL(urlSb);

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("apikey", "gilRmrW5NfZwtD0MS1aAB2NvkZONsYNE");

            InputStream inputStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);

            Gson gson = new Gson();
            CurrencyDTO currencyService = gson.fromJson(jsonText, CurrencyDTO.class);

            return currencyService.getResult();
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            stringBuilder.append((char) cp);
        }
        return stringBuilder.toString();
    }

}