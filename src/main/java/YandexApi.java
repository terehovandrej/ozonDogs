import com.google.gson.Gson;
import dto.Items;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class YandexApi {
    private final String oauthToken = System.getenv().get("token");
    public void uploadFile(String url, String filename) {
        try {
            // Строка запроса
            String requestUrl = "https://cloud-api.yandex.net/v1/disk/resources/upload?path=" + filename + "&url=" + url;
            // Запрос к API
            URL urlObj = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "OAuth " + oauthToken);

            // Получение ответа
            if (oauthToken == null) System.err.println("Нужно присвоить токен в переменных окружения");
            if (connection.getResponseCode() == 202) {
                System.out.println("Изображение " + url + " успешно загружено.");
            } else {
                System.err.println("Ошибка при загрузке изображения " + url);
            }
            Thread.sleep(2000);


        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
    public Set<String> getSetAllFiles() {
        Set<String> result = new HashSet<>();
        try {
            // Строка запроса
            String requestUrl = "https://cloud-api.yandex.net/v1/disk/resources/files";
            // Запрос к API
            URL urlObj = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
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

            // Парсинг ответа в JSON
            Gson gson = new Gson();
            Items items = gson.fromJson(response.toString(), Items.class);
            for (Items.Item item: items.getItems()
                 ) {
                result.add(item.getName());
            }
        } catch (Exception e) {
            if (oauthToken == null) System.err.println("Нужно присвоить токен в переменных окружения");
            System.err.println("Ошибка: " + e.getMessage());
        }
        return result;
    }
    public void deleteResource(String resource) {
        try {
            // Строка запроса
            String requestUrl = "https://cloud-api.yandex.net/v1/disk/resources/?path=" + resource;
            // Запрос к API
            URL urlObj = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "OAuth " + oauthToken);

            // Получение ответа
            if (oauthToken == null) System.err.println("Нужно присвоить токен в переменных окружения");
            if (connection.getResponseCode() == 204) {
                System.out.println( resource + " успешно удален.");
            } else {
                System.err.println("Ошибка при удалении " + resource);
            }


        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    public void clearTrash() {
        try {
            // Строка запроса
            String requestUrl = "https://cloud-api.yandex.net/v1/disk/trash/resources";
            // Запрос к API
            URL urlObj = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "OAuth " + oauthToken);

            // Получение ответа
            if (oauthToken == null) System.err.println("Нужно присвоить токен в переменных окружения");
            if (connection.getResponseCode() == 202) {
                System.out.println("корзина успешно очищена.");
            } else {
                System.err.println("Ошибка при очистке корзины ");
            }


        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
    public void deleteAllFilesFromDisk() {
        for (String file: getSetAllFiles()
             ) {deleteResource(file);
        }
    }
}