import com.github.javafaker.Faker;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

@Log
public class Main {

    private static final Faker faker = new Faker();
    private static final ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {


        randomUsers();


        users.forEach(System.out::println);

        int choise = 0;

        do {
            try {
                Scanner sc = new Scanner(System.in);
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
                        System.out.println("Nombre de usuario: \n");
                        addUser();

                    case 3:
                        log.info("Chau...");
                        System.exit(0);

                    default:
                        log.info(choise + " non é unha opción de menú válida! Seleccione outro.");
                }
            } catch (Exception e) {
                log.warning("A opción debe ser un número");
            }

        } while (choise != 4);


    }

    private static void login(String username) {
        User loginUser = users.stream().filter(u -> u.getUsername().equals(username)).findAny().orElse(null);
        log.info("Login successful\n" + "Welcome, " + username);

        int followersCount = loginUser.getFollowers().size();
        int postCount = loginUser.getPublications().size();
        System.out.println("-----------------------------------" + username.toUpperCase() + "[:)]" + "---------------------------------------------------\n");
        System.out.println("Seguidores: " + followersCount + "\t" + "Publicacións: " + postCount);

        showActions();
    }

    private static void showActions() {
        System.out.println("///////////////////////////////////"
                + "\n1: Show followers"
                + "\n2: See posts"
                + "\n3: Publish post   "
                + "\n4: See Comments         "
                + "\n5: Search user          "
                + "\n6: Profile     "
                + "\n[logout]            "
                + "\n//////////////////////////////////");
    }

    private static void addUser() {
        String username = new Scanner(System.in).nextLine();
        User user = new User(username, Collections.emptyList(), Collections.emptyList());

        if (verifyUser(username)) {
            log.info("Este username xa existe");
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