import DataStructures.BTree;
import Utilities.User;

import java.util.Scanner;

public class PlatformManager {

    BTree<User> users = new BTree<>(5);
    Scanner in = new Scanner(System.in);

    // Constructor
    public PlatformManager() {
    }

    public void createNewUser() {
        // Get username
        System.out.print("Username: ");
        String username = in.next();

        // Get password
        System.out.print("Password: ");
        String password = in.next();

        // Get the rest of the details
        System.out.print("Name: ");
        String name = in.next();

        System.out.print("Age: ");
        int age = in.nextInt();

        System.out.print("Gender(M/F): ");
        boolean gender = in.next().equals("M");

//        users.insert(new User(name, username, password, age, gender));
    }

    public void addFriend() {
        User user = findUser();
        if (user == null) {
            System.out.println("User does not exist. Please register first!");
            return;
        }
        System.out.print("Who do you want to be friends with? ");
        User friend = findUser();
        if (friend == null) {
            System.out.println("Such a user does not exist!");
            return;
        }
        user.addFriend(friend);
    }
    public void removeFriend() {
        User user = findUser();
        if (user == null) {
            System.out.println("User does not exist. Please register first!");
            return;
        }
        System.out.print("Who do you want to remove from friends? ");
        User friend = findUser();
        if (friend == null) {
            System.out.println("Such a user does not exist!");
            return;
        }
        user.removeFriend(friend);
    }

    public void viewFriends() {
        User user = findUser();
        System.out.println(user.getName() + ": " + user.viewFriends());
    }

    public void displayUsers() {
        users.traverse();
    }

    public User findUser() {
        System.out.print("Username: ");
        String username = in.next();

        return users.search(new User("", username, "", 0, false , null));
    }

    public void deleteUser() {
        User user = findUser();
        users.delete(user);
    }

    public static void main(String[] args) {
        PlatformManager plat = new PlatformManager();
        Scanner in = new Scanner(System.in);
        int quit = 0;
        System.out.println("Welcome to Social Network!");
        do {
            System.out.println("Options:");
            System.out.println("1 Create new user");
            System.out.println("2 Find a User");
            System.out.println("3 Delete a User");
            System.out.println("4 View Friends");
            System.out.println("5 View All Users");
            System.out.println("6 Add a friend");
            System.out.println("7 Unfriend");
            System.out.println("8 Quit");
            System.out.print("Option: ");
            int option = in.nextInt();
            switch (option) {
                case 1:
                    plat.createNewUser();
                    break;
                case 2:
                    plat.findUser();
                    break;
                case 3:
                    plat.deleteUser();
                    break;
                case 4:
                    plat.viewFriends();
                    break;
                case 5:
                    plat.displayUsers();
                    break;
                case 6:
                    plat.addFriend();
                    break;
                case 7:
                    plat.removeFriend();
                    break;
                default:
                    quit = option;
                    System.out.println("Goodbye!");
                    break;
            }
        }while(quit == 0);

//        plat.createNewUser();
//        plat.createNewUser();
//
//        plat.users.insert(new User("Hashir", "Hashir", "hi", 20, true));
//        plat.users.insert(new User("Imad", "maads", "physics", 20, true));
//        System.out.println();
//
//        plat.displayUsers();
//        System.out.println("\n");
//
//        plat.findUser();
//
//        plat.deleteUser();
//        System.out.println();
//
//        plat.displayUsers();
    }
}