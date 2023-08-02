package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.List;
import java.util.Scanner;

public class UserView {
    Scanner sc = new Scanner(System.in);
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            printActions();
            String command = sc.nextLine();
            System.out.println("-".repeat(30));
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case SHOW:
                    List<User> users = userController.readAll();
                    System.out.println(users);
                    break;
                case CREATE:
                    userController.saveUser(userController.createUser());
                    break;
                case READ:
                    System.out.print("Введите id пользователя ");
                    String id = sc.nextLine();
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case DELETE:
                    System.out.print("Введите id пользователя для удаления ");
                    String id1 = sc.nextLine();
                    userController.deleteUser(id1);
                    break;
                case UPDATE:
                    System.out.println("Введите id пользователя ");
                    String userId = sc.nextLine();
                    userController.updateUser(userId, userController.createUser());
            }
        }
    }

    private void printActions(){
        String textBlock = """
                Available actions: 
                
                SHOW 
                
                CREATE 
                
                READ 
                
                UPDATE 
                
                DELETE
                
                Введите необходимую команду:""";
        System.out.print(textBlock + " ");
    }
}
