import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SearchServiceImpl implements SearchService{
    @Override
    public List<User> searchForFriendsInWidth(User me, String name) {
        Queue<User> queue = new LinkedList<>();
        queue.offer(me);
        List<Long> visited = new ArrayList<>();
        List<User> allNatashas = new ArrayList<>();

        if (me == null) {
            return allNatashas;
        }

        while (!queue.isEmpty()) {
            User currentuser = queue.poll();
            if (visited.contains(currentuser.getId())) {
                continue;
            }
            visited.add(currentuser.getId());

            if (currentuser.getName().equals(name)) {
                allNatashas.add(currentuser);
            }

            List<User> friends = currentuser.getFriends();
            if (friends != null) {
                for (User friend : friends) {
                    queue.offer(friend);
                }
            }
        }
        return allNatashas;
    }

    @Override
    public List<User> searchForFriendsInDepth(User me, String name) {
        List<Long> visited = new ArrayList<>();
        List<User> allNatashas = new ArrayList<>();

        searchForFriendsInDepthRecurcion(me, name, visited, allNatashas);
        return allNatashas;
    }

    public void searchForFriendsInDepthRecurcion(User me, String name, List<Long> visited, List<User> allNatashas) {

        if (me == null) {
            return;
        }

        if (visited.contains(me.getId())) {
            return;
        }

        visited.add(me.getId());

        if(me.getName().contains(name)) {
            allNatashas.add(me);
        }

        List<User> friends = me.getFriends();
        for (User friend : friends) {
            searchForFriendsInDepthRecurcion(friend, name, visited, allNatashas);
        }
    }
}
