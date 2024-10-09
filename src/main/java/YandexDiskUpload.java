import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class YandexDiskUpload {

    public void uploadFile(String url) {
        String oauthToken = "y0_AgAAAAAMT6B7AADLWwAAAAET1hWbAAAqiG1K3-FJhZmnDfR49Ic4GbA5Mg";
        String path = "test";
        url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_13909.jpg";

        try {
            // Кодируем URL для безопасного использования в запросе
            String encodedUrl = URLEncoder.encode(url, "UTF-8");

            // Строка запроса
            String requestUrl = "https://cloud-api.yandex.net/v1/disk/resources/upload?path=" + path + "&url=" + encodedUrl;

            // Запрос к API
            URL urlObj = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "OAuth " + oauthToken);

            // Получение ответа
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Вывод ответа в консоль
            System.out.println(response.toString());

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}