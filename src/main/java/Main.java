import com.github.javafaker.Faker;
import lombok.extern.java.Log;

import java.time.Instant;
import java.util.*;

@Log
public class Main {

    private static final Faker faker = new Faker();
    private static final ArrayList<User> users = new ArrayList<>();

    private static final Scanner sc = new Scanner(System.in);

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
                System.out.print("-------------------------------\n3.) Exit\n");

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
                        + "\n7: [logout]            "
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
                        showCommentsByTitle(loginUser);
                        break;
                    case 5:
                        searchUserByUsername(users);
                        break;
                    case 6:
                        log.info("Perfil de usuario: " + loginUser);
                        break;

                    default:
                        log.info(choice + " not a valid menu option! Please select another.");
                }

            } catch (Exception e) {
                log.warning("The option must be a number");
            }
        } while (choice != 7);


    }

    private static void searchUserByUsername(List<User> users) {
        System.out.println("Nombre de usuario que desexa atopar: \n");
        String username = new Scanner(System.in).nextLine();
        users.stream().filter(x -> x.getUsername().equals(username)).forEach(
                x -> {
                    System.out.println(x);
                }
        );
    }

    private static void showCommentsByTitle(User loginUser) {
        System.out.println("Post title: \n");
        String titlePost = new Scanner(System.in).nextLine();

        loginUser.getPublications().stream().filter(x -> x.getTitle().equals(titlePost))
                .forEach(x -> {
                    if (!x.getCommentList().isEmpty()) {
                        x.getCommentList().forEach(System.out::println);
                    } else {
                        log.warning("There are no comments yet");
                    }
                });
    }

    private static void addPost(User loginUser) {
        System.out.println("¿Qué clase de post vai a publicar?\n");
        System.out.println("///////////////////////////////////"
                + "\n1: Imagen"
                + "\n2: Texto"
                + "\n3: Vídeo   ");

        int choice = sc.nextInt();
        Date currentDate = Date.from(Instant.now());
        switch (choice) {
            case 1:
                addImgPost(loginUser, currentDate);
                break;
            case 2:
                addTextPost(loginUser, currentDate);
                break;
            case 3:
                addVideoPost(loginUser, currentDate);
                break;

            default:
                log.info(choice + " not a valid menu option! Please select another.");
        }

    }

    private static void addVideoPost(User loginUser, Date currentDate) {
        System.out.println("Title of the publication:\n");
        String videoTitle = new Scanner(System.in).nextLine();
        System.out.println("Duration:\n");
        int duration = new Scanner(System.in).nextInt();
        System.out.println("Quality:\n");
        String quality = new Scanner(System.in).nextLine();

        Post videoPost = new VideoPost(videoTitle, currentDate, quality, duration);
        loginUser.getPublications().add(videoPost);
        loginUser.getPublications().forEach(System.out::println);
    }

    private static void addTextPost(User loginUser, Date currentDate) {
        System.out.println("Title of the publication:\n");
        String textTitle = new Scanner(System.in).nextLine();
        System.out.println("Content:\n");
        String content = new Scanner(System.in).nextLine();

        Post textPost = new TextPost(textTitle, currentDate, content);
        loginUser.getPublications().add(textPost);
        loginUser.getPublications().forEach(System.out::println);
    }

    private static void addImgPost(User loginUser, Date currentDate) {
        System.out.println("Image name:\n");
        String imgTitle = new Scanner(System.in).nextLine();
        System.out.println("Dimensions:\n");
        String dimensions = new Scanner(System.in).nextLine();

        Post post = new ImgPost(imgTitle, currentDate, dimensions);
        loginUser.getPublications().add(post);
        loginUser.getPublications().forEach(System.out::println);
    }

    /**
     * Ensina todos os seguidores do usuario.
     *
     * @param loginUser usuario logueado
     */
    private static void showFollowers(User loginUser) {
        if (!loginUser.getFollowers().isEmpty()) {
            loginUser.getFollowers().forEach(System.out::println);
        }
        log.info("You have no followers");
    }

    /**
     * Ensina todos os post do usuario
     *
     * @param loginUser username do usuario logueado
     */
    private static void showPost(User loginUser) {
        if (!loginUser.getPublications().isEmpty()) {
            loginUser.getPublications().forEach(System.out::println);
        }
        log.info("You haven't posted anything yet");
    }

    /**
     * Verifica si se pode engadir un novo usuario e engadeo.
     */
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

    /**
     * Crea 10 usuarios aleatorios.
     */
    private static void randomUsers() {
        for (int i = 0; i < 11; ++i) {
            String ramdomName = faker.name().username();
            users.add(new User(ramdomName, Collections.emptyList(), Collections.emptyList()));
        }
    }

    /**
     * Determina se o username xa existe dentro do array de users
     *
     * @param username nome do usuario
     * @return true se existe, false se non existe
     */
    private static boolean verifyUser(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }
}