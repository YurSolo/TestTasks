import java.util.List;

interface SearchService {
    List<User> searchForFriendsInWidth(User me, String name);
    List<User> searchForFriendsInDepth(User me, String name);
}
