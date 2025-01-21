package com.example.service;

import com.example.model.RespuestaAPI;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service  // Asegúrate de que esté presente
public class GutendexService {
    private static final String BASE_URL = "https://gutendex.com/books/?search=";

    public RespuestaAPI buscarLibroPorTitulo(String titulo) {
        String url = BASE_URL + titulo.replace(" ", "%20");
        System.out.println("🔍 Consultando: " + url);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response.body(), RespuestaAPI.class);
            } else {
                System.out.println("❌ Error en la solicitud: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Error al conectar con la API: " + e.getMessage());
        }

        return null;
    }
}
