public class User {
    private String userId;
    private String username;
    private String password;
    private String phoneNumber;
    private String role; // 新增角色字段，用于区分管理员、经理、前台等

    public User(String userId, String username, String password, String phoneNumber, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    // 可选：重写 toString 方法，便于调试和打印信息
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}