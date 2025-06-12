package com.akihabara.market.dao;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

import com.google.gson.*;

public class LlmService {

    // API Key leída desde config.properties
    private String apiKey;

    // Constructor: carga la clave de API desde un archivo de propiedades externo
    public LlmService() {
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            Properties props = new Properties();
            props.load(fis);
            apiKey = props.getProperty("OPENROUTER_API_KEY");

            if (apiKey == null || apiKey.isBlank()) {
                throw new RuntimeException("API Key no encontrada en config.properties");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer config.properties: " + e.getMessage());
        }
    }

    /**
     * Sugiere un nombre llamativo para un producto otaku, utilizando un modelo LLM (IA).
     *
     * @param tipo       Tipo de producto (ej. figura, camiseta)
     * @param franquicia Nombre de la franquicia (ej. Naruto, One Piece)
     * @return Nombre sugerido como texto plano
     */
    public String sugerirNombreProducto(String tipo, String franquicia) {
        // Crear el prompt dinámicamente
        String prompt = String.format(
            "Sugiere un nombre llamativo y original para un producto otaku del tipo '%s' basado en la franquicia '%s'.",
            tipo, franquicia);

        try {
            // Crear cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Crear mensaje JSON para el modelo
            JsonObject message = new JsonObject();
            message.addProperty("role", "user");
            message.addProperty("content", prompt);

            // Arreglo de mensajes (solo uno en este caso)
            JsonArray messages = new JsonArray();
            messages.add(message);

            // Cuerpo de la solicitud
            JsonObject body = new JsonObject();
            body.addProperty("model", "mistralai/mistral-7b-instruct:free");
            body.add("messages", messages);

            // Construir la solicitud HTTP POST
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://openrouter.ai/api/v1/chat/completions"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

            // Enviar solicitud y obtener respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parsear la respuesta JSON
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            // Extraer contenido del mensaje generado
            String resultado = json.getAsJsonArray("choices")
                .get(0).getAsJsonObject()
                .getAsJsonObject("message")
                .get("content")
                .getAsString();

            // Limpiar el nombre sugerido: quitar saltos de línea y espacios
            resultado = resultado.replaceAll("\\r|\\n", "").trim();

            // Limitar a 255 caracteres (longitud máxima permitida en la BD)
            if (resultado.length() > 255) {
                resultado = resultado.substring(0, 255);
            }

            return resultado;

        } catch (Exception e) {
            System.out.println("Error al comunicar con OpenRouter: " + e.getMessage());
            return "Producto Otaku Desconocido";  // Valor por defecto en caso de fallo
        }
    }
}
