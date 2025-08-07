import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cinema {
    private List<User> users = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();
    private List<Theater> theaters = new ArrayList<>();
    private List<Screening> screenings = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public boolean login(String username, String password) {
        return users.stream()
                .anyMatch(u -> u.getUsername().equals(username) && u.getPassword().equals(password));
    }

    public void showAdminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n=== 管理员菜单 ===");
            System.out.println("1. 添加电影");
            System.out.println("2. 查看电影列表");
            System.out.println("3. 排片");
            System.out.println("4. 查看座位");
            System.out.println("5. 售票");
            System.out.println("6. 重置用户密码");
            System.out.println("7. 查看销售数据");
            System.out.println("0. 退出");
            System.out.print("请选择: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addMovie(scanner);
                    break;
                case "2":
                    listMovies();
                    break;
                case "3":
                    scheduleMovie(scanner);
                    break;
                case "4":
                    showSeats(scanner);
                    break;
                case "5":
                    sellTicket(scanner);
                    break;
                case "6":
                    resetUserPassword(scanner);
                    break;
                case "7":
                    viewSalesData();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("无效选项！");
            }
        }
    }

    private void addMovie(Scanner scanner) {
        System.out.print("电影名称: ");
        String title = scanner.nextLine();
        System.out.print("导演: ");
        String director = scanner.nextLine();
        System.out.print("主演: ");
        String cast = scanner.nextLine();
        System.out.print("简介: ");
        String description = scanner.nextLine();
        System.out.print("时长(分钟): ");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.print("评分(0-10): ");
        double rating = Double.parseDouble(scanner.nextLine());

        movies.add(new Movie(title, director, cast, description, duration, rating));
        System.out.println("电影添加成功！");
    }

    private void listMovies() {
        System.out.println("\n=== 电影列表 ===");
        for (Movie movie : movies) {
            System.out.println(movie.getTitle() + " - 评分: " + movie.getRating());
        }
    }

    private void scheduleMovie(Scanner scanner) {
        if (movies.isEmpty()) {
            System.out.println("请先添加电影！");
            return;
        }

        System.out.print("输入电影名称: ");
        String movieName = scanner.nextLine();
        Movie movie = movies.stream()
                .filter(m -> m.getTitle().equals(movieName))
                .findFirst()
                .orElse(null);

        if (movie == null) {
            System.out.println("电影不存在！");
            return;
        }

        System.out.print("开始时间(格式: 年-月-日 时:分): ");
        String timeStr = scanner.nextLine();
        LocalDateTime startTime = LocalDateTime.parse(timeStr);

        Theater theater = new Theater("大厅1", 5, 10); // 固定放映厅
        double price = 30.0;

        screenings.add(new Screening(movie, theater, startTime, price));
        System.out.println("排片成功！");
    }

    private void showSeats(Scanner scanner) {
        if (screenings.isEmpty()) {
            System.out.println("暂无场次！");
            return;
        }

        System.out.print("输入场次序号(从1开始): ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= screenings.size()) {
            System.out.println("无效的场次序号！");
            return;
        }

        Screening screening = screenings.get(index);

        Theater theater = screening.getTheater();
        System.out.println("\n=== 座位表 ===");
        for (int i = 0; i < theater.getRows(); i++) {
            for (int j = 0; j < theater.getSeatsPerRow(); j++) {
                System.out.print(theater.isSeatAvailable(i, j) ? "O " : "X ");
            }
            System.out.println();
        }
    }

    public void sellTicket(Scanner scanner) {
        if (screenings.isEmpty()) {
            System.out.println("暂无场次！");
            return;
        }

        System.out.print("输入场次序号(从1开始): ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= screenings.size()) {
            System.out.println("无效的场次序号！");
            return;
        }

        Screening screening = screenings.get(index);

        Theater theater = screening.getTheater();
        System.out.println("\n请选择座位:");
        for (int i = 0; i < theater.getRows(); i++) {
            for (int j = 0; j < theater.getSeatsPerRow(); j++) {
                System.out.print(theater.isSeatAvailable(i, j) ? "O " : "X ");
            }
            System.out.println();
        }

        System.out.print("输入行号(0-" + (theater.getRows()-1) + "): ");
        int row = Integer.parseInt(scanner.nextLine());
        System.out.print("输入列号(0-" + (theater.getSeatsPerRow()-1) + "): ");
        int seat = Integer.parseInt(scanner.nextLine());

        if (row < 0 || row >= theater.getRows() || seat < 0 || seat >= theater.getSeatsPerRow()) {
            System.out.println("座位号超出范围！");
            return;
        }

        if (!theater.isSeatAvailable(row, seat)) {
            System.out.println("座位已售！");
        } else {
            theater.bookSeat(row, seat);
            tickets.add(new Ticket(screening, row, seat));
            System.out.println("购票成功！价格: " + screening.getPrice() + "元，票号: " + tickets.get(tickets.size()-1).getTicketId());
        }
    }

    // 重置用户密码功能
    private void resetUserPassword(Scanner scanner) {
        System.out.print("请输入要重置密码的用户名: ");
        String targetUsername = scanner.nextLine();

        User targetUser = users.stream()
                .filter(u -> u.getUsername().equals(targetUsername))
                .findFirst()
                .orElse(null);

        if (targetUser == null) {
            System.out.println("用户不存在！");
            return;
        }

        System.out.print("请输入新密码: ");
        String newPassword = scanner.nextLine();

        // 调用 User 类的 setPassword 方法
        targetUser.setPassword(newPassword);
        System.out.println("用户 '" + targetUsername + "' 的密码已成功重置。");
    }

    // 查看销售数据功能
    public void viewSalesData() {
        System.out.println("\n=== 当日销售数据 ===");
        // 示例代码，实际应用中需要统计具体数据
        System.out.println("总票数: 100");
        System.out.println("总收入: 3000元");
        System.out.println("退票数: 5张");
        System.out.println("退票金额: 150元");
    }
}