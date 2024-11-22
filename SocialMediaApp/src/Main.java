import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Map<Integer, User> users = new HashMap<>();
    private static final List<Post> posts = new ArrayList<>();
    private static int postCounter = 1;

    public static void main(String[] args) {
        registerUser(1, "Akash");
        registerUser(2, "Hemant");
        registerUser(3, "John");

        uploadPost(1, "This is my first post.");
        uploadPost(1, "I work at Flipkart as an SDE1.");
        uploadPost(2, "I too worked at Flipkart as an SDE1.");
        uploadPost(3, "Hello, world!");

        interactWithUser("FOLLOW", 2, 1); // User 2 follows User 1
        interactWithUser("FOLLOW", 3, 1); // User 3 follows User 1
        interactWithUser("UNFOLLOW", 3, 1); // User 3 unfollows User 1

        System.out.println("Feed for User 2:");
        showFeed(2);

        System.out.println("Feed for User 3:");
        showFeed(3);
    }

    private static void registerUser(int userId, String userName) {
        if (users.containsKey(userId)) {
            System.out.println("User ID already exists!");
            return;
        }
        users.put(userId, new User(userId, userName));
        System.out.println(userName + " Registered!!");
    }

    private static void uploadPost(int userId, String postContent) {
        User user = users.get(userId);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }
        String postId = String.format("%03d", postCounter++);
        Post post = new Post(postId, userId, postContent, LocalDateTime.now());
        posts.add(post);
        System.out.println("Upload Successful with post id: " + postId);
    }

    private static void interactWithUser(String interactionType, int userId1, int userId2) {
        User user1 = users.get(userId1);
        User user2 = users.get(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found!");
            return;
        }

        if (interactionType.equalsIgnoreCase("FOLLOW")) {
            user1.follow(userId2);
            System.out.println("Followed " + user2.getUserName() + "!!");
        } else if (interactionType.equalsIgnoreCase("UNFOLLOW")) {
            user1.unfollow(userId2);
            System.out.println("Unfollowed " + user2.getUserName() + "!!");
        } else {
            System.out.println("Invalid interaction type. Use FOLLOW or UNFOLLOW.");
        }
    }

    private static void showFeed(int userId) {
        User user = users.get(userId);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("User Feed:");

        List<Post> followedPosts = posts.stream()
                .filter(post -> user.getFollowing().contains(post.getUserId()))
                .collect(Collectors.toList());

        List<Post> nonFollowedPosts = posts.stream()
                .filter(post -> !user.getFollowing().contains(post.getUserId()))
                .collect(Collectors.toList());

        followedPosts.sort(Comparator.comparing(Post::getPostTime).reversed());
        nonFollowedPosts.sort(Comparator.comparing(Post::getPostTime).reversed());

        for (Post post : followedPosts) {
            displayPost(post);
        }

        for (Post post : nonFollowedPosts) {
            displayPost(post);
        }
    }

    private static void displayPost(Post post) {
        User user = users.get(post.getUserId());
        System.out.println("UserName: " + user.getUserName());
        System.out.println("Post: " + post.getContent());
        System.out.println("Post Time: " + post.getPostTime());
        System.out.println();
    }
}
