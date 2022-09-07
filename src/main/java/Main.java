import com.github.javafaker.Faker;
import lombok.extern.java.Log;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

@Log
public class Main {

    private static final Faker faker = new Faker();
    private static final ArrayList<User> users = new ArrayList<>();

    private static final  Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        randomUsers();


        users.forEach(System.out::println);

        int choise = 0;

        do {
            try {
                System.out.println("\n");
                System.out.println("*** Log in ***\n");
                System.out.print("1.) Log in. \n");
                System.out.print("2.) Create new account.\n");
                System.out.print("--------------------------\n(3.) Exit\n");

                choise = sc.nextInt();

                switch (choise) {

                    case 1:
                        System.out.println("Username:\n");
                        String username = new Scanner(System.in).nextLine();
                        if (verifyUser(username)) {
                            login(username);
                        } else {
                            log.info("Login failed");
                        }
                        break;

                    case 2:
                        System.out.println("username: \n");
                        addUser();

                    case 3:
                        log.info("Chau...");
                        System.exit(0);

                    default:
                        log.info(choise + " not a valid menu option! Please select another.");
                }
            } catch (Exception e) {
                log.warning("The option must be a number");
            }

        } while (choise != 4);


    }

    private static void login(String username) {
        User loginUser = users.stream().filter(u -> u.getUsername().equals(username)).findAny().orElse(null);
        log.info("Login successful\n" + "Welcome, " + username);

        int followersCount = loginUser.getFollowers().size();
        int postCount = loginUser.getPublications().size();
        System.out.println("-----------------------------------" + username.toUpperCase() + "[:)]" + "---------------------------------------------------\n");
        System.out.println("Followers: " + followersCount + "\t" + "Publication: " + postCount);

        showActions(loginUser);
    }

    private static void showActions(User loginUser) {

        int choice = 0;
        do {
            try {

                System.out.println("///////////////////////////////////"
                        + "\n1: Show followers"
                        + "\n2: See posts"
                        + "\n3: Publish post   "
                        + "\n4: See Comments         "
                        + "\n5: Search user          "
                        + "\n6: Profile     "
                        + "\n[logout]            "
                        + "\n//////////////////////////////////");

                System.out.println("Opción: \n");
                choice = sc.nextInt();


                switch (choice) {

                    case 1:
                        showFollowers(loginUser);
                        break;
                    case 2:
                        showPost(loginUser);
                        break;
                    case 3:
                        addPost(loginUser);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                }

            } catch (Exception e) {
                log.warning("The option must be a number");
            }
        } while (choice != 7);


    }

    private static void addPost(User loginUser) {
        System.out.println("¿Qué clase de post vai a publicar?\n");
        System.out.println("///////////////////////////////////"
                + "\n1: Imagen"
                + "\n2: Texto"
                + "\n3: Vídeo   ");

        int choice = sc.nextInt();
       switch (choice){
           case 1:
               String title= "Aleatorio";
               String  dimentions="50*50";

              Post post = new ImgPost("", Date.from(Instant.now()), "");
                //add a lista
               break;
           case 2:

               break;
           case 3:

               break;
       }


    }

    private static void showFollowers(User loginUser) {
        if (!loginUser.getFollowers().isEmpty()) {
            loginUser.getFollowers().stream().forEach(System.out::println);
        }
        log.info("You have no followers");
    }

    private static void showPost(User loginUser) {
        if (!loginUser.getPublications().isEmpty()) {
            loginUser.getPublications().stream().forEach(System.out::println);
        }
        log.info("You haven't posted anything yet");
    }

    private static void addUser() {
        String username = new Scanner(System.in).nextLine();
        User user = new User(username, Collections.emptyList(), Collections.emptyList());

        if (verifyUser(username)) {
            log.info("This username already exists");
        } else {
            users.add(user);
            users.forEach(System.out::println);
        }
    }

    private static void randomUsers() {
        for (int i = 0; i < 11; ++i) {
            String ramdomName = faker.name().username();
            users.add(new User(ramdomName, Collections.emptyList(), Collections.emptyList()));
        }
    }

    private static boolean verifyUser(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }
}