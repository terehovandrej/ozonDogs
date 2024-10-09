public class Main {

    public static void main(String[] args) {
        DogApi dogApi = new DogApi();
//        System.out.println(dogApi.getRandomBreed("hound"));
//        System.out.println(dogApi.getListSubBreed("hound"));
//        System.out.println(dogApi.getRandomSubBreed("hound", "afghan"));
        dogApi.uploadImageToDisk("hound");



    }
}