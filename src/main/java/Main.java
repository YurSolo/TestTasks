import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Задача 1
        List<String> competitors = List.of("Ivan 5", "Petr 3", "Alex 10", "Petr 8", "Ivan 6", "Alex 5", "Ivan 1", "Petr 5", "Alex 1");
        List<String> competitors2 = List.of("Ivan 12", "Petr 4", "Alex 10", "Petr 1", "Ivan 63", "Alex 51", "Ivan 11", "Petr 57", "Alex 14");
        showWinner(competitors);
        showWinner(competitors2);

        //Задача 2
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Post 1", 10));
        posts.add(new Post("Post 2", 5));
        posts.add(new Post("Post 3", 15));
        posts.add(new Post("Post 4", 5));

        long startTime = System.currentTimeMillis();
        getTop10(posts);
        for(Post post: posts) {
            System.out.println(post.likesCount);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения" + (endTime - startTime));
        
    }

    /**
     * Задача 1
     */
    public static void showWinner(List<String> competitors) {
        Map<String, Integer> scores = new HashMap<>();
        String winner = null;
        int maxScore = 0;
        for (String competitor: competitors){
            String[] participant = competitor.split(" ");
            scores.put(participant[0], scores.getOrDefault(participant[0], 0) + Integer.parseInt(participant[1]));
            if(maxScore < scores.get(participant[0])) {
                maxScore = scores.get(participant[0]);
                winner = participant[0];
            }
        }
        System.out.println(winner);
    }

    /**
     * Задача 2
     */
    public static List<Post> getTop10(List<Post> posts){
        ArrayList<Post> posts1 = new ArrayList<>(posts);
        posts1.sort(Comparator.comparingInt(Post::getLikesCount).reversed());
        return posts1;
    }

    public static class Post{
        private String text;
        private Integer likesCount;

        public Post(String text, Integer likesCount) {
            this.text = text;
            this.likesCount = likesCount;
        }

        public String getText() {
            return text;
        }

        public Integer getLikesCount() {
            return likesCount;
        }
    }
}
