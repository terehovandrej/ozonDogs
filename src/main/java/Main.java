public class Main {

    public static void main(String[] args) throws InterruptedException {
        DogApi dogApi = new DogApi();
//        System.out.println(dogApi.getRandomBreed("hound"));
//        System.out.println(dogApi.getListSubBreed("hound"));
//        System.out.println(dogApi.getRandomSubBreed("hound", "afghan"));
 //       dogApi.uploadImageToDisk("hound");
        YandexApi yandexApi = new YandexApi();
        //yandexDiskUpload.createFolder("test");
        yandexApi.deleteAllFilesFromDisk();
        System.out.println(yandexApi.getListAllFiles());


    }
}