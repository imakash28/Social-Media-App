import java.time.LocalDateTime;

public class Post {
    private final String postId;
    private final int userId;
    private final String content;
    private final LocalDateTime postTime;

    public Post(String postId, int userId, String content, LocalDateTime postTime){
        this.postId=postId;
        this.userId=userId;
        this.content=content;
        this.postTime=postTime;
    }

    public String getPostId(){
        return postId;
    }

    public int getuserId(){
        return userId;
    }
    public String getContent(){
        return content;
    }
    public LocalDateTime getPostTime(){
        return postTime;
    }

    public String setPostId(){
        return postId;
    }
    public int setUserId(){
        return userId;
    }

    public String setContent(){
        return content;
    }
    public LocalDateTime setPostTime(){
        return postTime;
    }

    public Integer getUserId() {
        return userId;
    }
}
