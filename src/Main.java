import Utilities.User;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        User user =  new User("Hashir", 0, true);
        System.out.println(user.getName() + ", " + user.getAge() + " years old.");

        while(user.getAge() < 13) {
            float time = System.nanoTime();
            while((time + 500000000) > System.nanoTime()) {
            }
            life(user);
            System.out.println(user.getName() + ", " + user.getAge() + " years old.");
        }

        System.out.println("User is now a teenager!");
    }

    public static void life(User user) {
        user.setAge(user.getAge() + 1);
    }
}