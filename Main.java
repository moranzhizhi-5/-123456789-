import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cinema cinema = new Cinema();

        // 初始化管理员
        User admin = new User("A001", "admin", "123456", "13800000000", "ADMIN");
        cinema.addUser(admin);

        // 登录
        System.out.println("=== 管理员登录 ===");
        System.out.print("用户名: ");
        String username = scanner.nextLine();
        System.out.print("密码: ");
        String password = scanner.nextLine();

        if (cinema.login(username, password)) {
            System.out.println("登录成功！");
            cinema.showAdminMenu(scanner);
        } else {
            System.out.println("登录失败！");
        }

        scanner.close();
    }
}