package es.udc.fi.tfg.rest.dtos;

public class UserProfileDto {

    public interface AllValidations {}

    /**
     * The Interface UpdateValidations.
     */
    public interface UpdateValidations {}

    /** The id. */
    private Long id;

    /** The user name. */
    private String userName;

    /** The first name. */
    private String firstName;

    /** The last name. */
    private String lastName;

    /** The email. */
    private String email;

    /** The role. */
    private String role;

    private byte[] avatar;

    private boolean followed;

    private BlockDto<PostFeedDto> posts;

    private int numFollowers;

    private int numFollowed;


    public UserProfileDto() {
    }

    public UserProfileDto(Long id, String userName, String firstName, String lastName, String email, String role, byte[] avatar,
                          boolean followed, BlockDto<PostFeedDto> posts, int numFollowers, int numFollowed) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.avatar = avatar;
        this.followed = followed;
        this.posts = posts;
        this.numFollowers = numFollowers;
        this.numFollowed = numFollowed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public BlockDto<PostFeedDto> getPosts() {
        return posts;
    }

    public void setPosts(BlockDto<PostFeedDto> posts) {
        this.posts = posts;
    }

    public int getNumFollowers() {
        return numFollowers;
    }

    public void setNumFollowers(int numFollowers) {
        this.numFollowers = numFollowers;
    }

    public int getNumFollowed() {
        return numFollowed;
    }

    public void setNumFollowed(int numFollowed) {
        this.numFollowed = numFollowed;
    }
}
