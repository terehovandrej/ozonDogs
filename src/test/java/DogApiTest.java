import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DogApiTest extends TestBase {

    @DisplayName("Загрузка нескольких картинок, когда у породы есть подпопороды")
    @Test
    void testUploadSubbreeds() {
        List<String> expectedResult = Arrays.asList("hound-afghan", "hound-basset", "hound-blood", "hound-english", "hound-ibizan", "hound-plott", "hound-walker");
        dogApi.uploadImageToDisk("hound");
        List<String> factResult = yandexApi.getListAllFiles();
        Assertions.assertEquals(expectedResult, factResult);
    }
    @DisplayName("Загрузка одной картинки, когда у породы нет подпопороды")
    @Test
    void testUploadBreed() {
        List<String> expectedResult = Collections.singletonList("pitbull");
        dogApi.uploadImageToDisk("pitbull");
        List<String> factResult = yandexApi.getListAllFiles();
        Assertions.assertEquals(expectedResult, factResult);
    }

    @DisplayName("Загрузка одной картинки, когда у породы только одна подпопорода")
    @Test
    void testUploadSingleSubBreed() {
        List<String> expectedResult = Collections.singletonList("dane-great");
        dogApi.uploadImageToDisk("dane");
        List<String> factResult = yandexApi.getListAllFiles();
        Assertions.assertEquals(expectedResult, factResult);
    }

    @DisplayName("Собака не найдена, ничего не загружено")
    @Test
    void testNothingUpload() {
        dogApi.uploadImageToDisk("catdog");
        List<String> factResult = yandexApi.getListAllFiles();
        Assertions.assertEquals(0 , factResult.size());
    }
}
