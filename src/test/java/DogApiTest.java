import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class DogApiTest extends TestBase {

    @DisplayName("Загрузка нескольких картинок, когда у породы есть подпопороды")
    @Test
    void testUploadSubbreeds() {
        Set<String> expectedResult = Set.of("hound-afghan", "hound-basset", "hound-blood", "hound-english", "hound-ibizan", "hound-plott", "hound-walker");
        dogApi.uploadImageToDisk("hound");
        Set<String> factResult = yandexApi.getSetAllFiles();
        Assertions.assertEquals(expectedResult, factResult);
    }
    @DisplayName("Загрузка одной картинки, когда у породы нет подпопороды")
    @Test
    void testUploadBreed() {
        Set<String> expectedResult = Set.of("pitbull");
        dogApi.uploadImageToDisk("pitbull");
        Set<String> factResult = yandexApi.getSetAllFiles();
        Assertions.assertEquals(expectedResult, factResult);
    }

    @DisplayName("Загрузка одной картинки, когда у породы только одна подпопорода")
    @Test
    void testUploadSingleSubBreed() {
        Set<String> expectedResult = Set.of("dane-great");
        dogApi.uploadImageToDisk("dane");
        Set<String> factResult = yandexApi.getSetAllFiles();
        Assertions.assertEquals(expectedResult, factResult);
    }

    @DisplayName("Собака не найдена, ничего не загружено")
    @Test
    void testNothingUpload() {
        dogApi.uploadImageToDisk("catdog");
        Set<String> factResult = yandexApi.getSetAllFiles();
        Assertions.assertEquals(0 , factResult.size());
    }
}
