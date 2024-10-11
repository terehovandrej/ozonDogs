import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    protected static DogApi dogApi;
    protected static YandexApi yandexApi;
    @BeforeAll
    static void init() {
        dogApi = new DogApi();
        yandexApi = new YandexApi();
    }
    @BeforeEach
    void precondition() {
        yandexApi.deleteAllFilesFromDisk();
    }
    @AfterAll
    static void close() {
        yandexApi.deleteAllFilesFromDisk();
        yandexApi.clearTrash();
    }
}
