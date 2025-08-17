package com.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.dto.ResultadoAPI;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public ResultadoAPI buscarLivroPorTitulo(String titulo) {
        try {
            String url = "https://gutendex.com/books?search=" + titulo.replace(" ", "%20");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return mapper.readValue(response.body(), ResultadoAPI.class);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao consumir API: " + e.getMessage());
        }
    }
}
