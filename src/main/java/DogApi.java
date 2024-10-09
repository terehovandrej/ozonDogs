import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class DogApi {
    public void uploadImageToDisk(String breed) {
        JSONArray listSubBreed = getListSubBreed(breed);
        if (listSubBreed.length() > 0) {
            for (Object subBreed : listSubBreed) {
                System.out.println(getRandomSubBreed(breed, subBreed.toString()));
            }
        }  else if (getRandomBreed(breed) != null) {
            System.out.println(getRandomBreed(breed));

        } else
            System.out.println("ничего не загружено");
    }
    public String getRandomBreed(String breed) {
        try {
            // Запрос к API
            URL url = new URL(String.format("https://dog.ceo/api/breed/%s/images/random", breed));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Получение ответа
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Парсинг ответа в JSON
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Извлечение ссылки на изображениеya

            return jsonResponse.getString("message");
        } catch (Exception e) {
            return null;
        }
    }

    public JSONArray getListSubBreed(String breed) {
        try {
            // Запрос к API
            URL url = new URL(String.format("https://dog.ceo/api/breed/%s/list", breed));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Получение ответа
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Парсинг ответа в JSON
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Извлечение ссылки на изображение

            return jsonResponse.getJSONArray("message");
        } catch (Exception e) {
            return new JSONArray();
        }

    }

    public String getRandomSubBreed(String breed, String subbreed) {
        try {
            // Запрос к API
            URL url = new URL(String.format("https://dog.ceo/api/breed/%s/%s/images/random", breed, subbreed));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Получение ответа
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Парсинг ответа в JSON
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Извлечение ссылки на изображение
            return jsonResponse.getString("message");
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            return null;
        }

    }
}