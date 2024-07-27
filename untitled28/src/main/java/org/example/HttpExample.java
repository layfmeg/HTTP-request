import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpExample {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        // GET запит
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.example.com/getEndpoint"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Парсинг JSON в POJO клас
        ObjectMapper objectMapper = new ObjectMapper();
        MyResponse myResponse = objectMapper.readValue(response.body(), MyResponse.class);

        System.out.println("Field1: " + myResponse.getField1());
        System.out.println("Field2: " + myResponse.getField2());

        // POST запит
        String requestBody = "{\"field\":\"value\"}";

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.example.com/postEndpoint"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

        // Парсинг JSON в POJO клас
        MyResponse postMyResponse = objectMapper.readValue(postResponse.body(), MyResponse.class);

        System.out.println("Field1: " + postMyResponse.getField1());
        System.out.println("Field2: " + postMyResponse.getField2());
    }
}
