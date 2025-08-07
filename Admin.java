public class Admin extends User {
    public Admin(String userId, String username, String password, String phoneNumber,String role) {
        super(userId, username, password, phoneNumber,role);
    }

    public void resetPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        System.out.println("密码已成功重置为：" + newPassword);
    }
}