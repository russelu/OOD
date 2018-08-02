public class User {
    // fields
    public final String username;
    private String password;
    private final String firstname;
    private final String lastname;
    private final String phone;

    // methods
    private User(UserBuilder builder) { // JSONObject object
        this.username = builder.username;
        this.password = builder.password;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.phone = builder.phone;
    }

    public static class UserBuilder {
        // fields
        private final String username;
        private String password;
        private String firstname;
        private String lastname;
        private String phone;

        // methods
        public UserBuilder(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public UserBuilder setName(String firstname, String lastname) {
            this.firstname = firstname;
            this.lastname = lastname;
            return this;
        }

        public UserBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static void main(String[] args) {
        UserBuilder ub = new User.UserBuilder("un", "pw");
        User user = ub.setName("John", "Smith").setPhone("12345467890").build();
    }
}
