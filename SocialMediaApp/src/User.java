import java.util.*;

class User {
    private final int userId;
    private final String userName;
    private final Set<Integer> following;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.following = new HashSet<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Set<Integer> getFollowing() {
        return following;
    }

    public void follow(int userId) {
        following.add(userId);
    }

    public void unfollow(int userId) {
        following.remove(userId);
    }
}
