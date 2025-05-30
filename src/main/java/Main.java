import java.util.*;
import java.util.stream.Collectors;

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
        List<Post> newPost =  getTop10(posts);
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения " + (endTime - startTime));
        for(Post post: newPost) {
            System.out.println(post.likesCount);
        }

        //Задача 3

        //Рассчитать суммарный возраст для определенного имени.
        int totalAge = Arrays.stream(clients)
                .filter(client -> client.getName().equals("Lol"))
                .mapToInt(Client::getAge)
                .sum();

        System.out.println(totalAge);

        //Получить Set, который содержит в себе только имена клиентов в порядке их упоминания в исходном массиве.

        Set<String> names = Arrays.stream(clients)
                .map(Client::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(names);

        //Узнать, содержит ли список хотя бы одного клиента, у которого возраст больше заданного числа.
        boolean isAdult = Arrays.stream(clients)
                .map(Client::getAge)
                .anyMatch(client -> client > 53);
        System.out.println(isAdult);

        //Преобразовать массив в Map, у которой ключ - уникальный идентификатор, значение - имя. Поддержать порядок, в котором клиенты добавлены в массив.
        Map<Integer, String> idName = Arrays.stream(clients)
                .collect(Collectors.toMap(
                        Client::getId,
                        Client::getName,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
        System.out.println(idName.toString());

        //Преобразовать массив в Map, у которой ключ - возраст, значение - коллекция клиентов с таким возрастом.
        Map<Integer, List<Client>> clientStream = Arrays.stream(clients)
                .collect(Collectors.groupingBy(
                        Client::getAge,
                        Collectors.toList()
                ));
        System.out.println(clientStream);

        //Получить строку, содержащую телефоны всех клиентов через запятую. Предусмотреть, что у клиента телефонов может и не быть.
        String phones = Arrays.stream(clients)
                .flatMap(client -> client.getPhones().stream())
                .map(phone -> String.valueOf(phone.number))
                .collect(Collectors.joining(", "));
        System.out.println(phones);

        //Найти самого возрастного клиента, которой пользуется стационарным телефоном
        Client oldest = Arrays.stream(clients)
                .filter(client -> client.getPhones().stream().anyMatch(phone -> phone.phoneType == PhoneType.LANDLINE))
                .max((c1, c2) -> Integer.compare(c1.age, c2.age))
                .get();
        System.out.println(oldest.getName());
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

    /**
     * Задача 3
     */

    enum PhoneType {
        MOBILE,
        LANDLINE,
    }

    public static class Phone {
        private int number;
        private PhoneType phoneType;

        public Phone(int number, PhoneType phoneType) {
            this.number = number;
            this.phoneType = phoneType;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public PhoneType getPhoneType() {
            return phoneType;
        }

        public void setPhoneType(PhoneType phoneType) {
            this.phoneType = phoneType;
        }
    }
    public static class Client {
        private int id;
        private String name;
        private int age;
        private List<Phone> phones;

        public Client(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
            phones = new ArrayList<>();
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Phone> getPhones() {
            return phones;
        }

        public void setPhones(List<Phone> phones) {
            this.phones = phones;
        }
    }

    private static Client[] clients;

    static {
        clients = new Client[] {
                new Client(1, "Aboba", 35),
                new Client(2, "Boyyyy", 25),
                new Client(3, "Lol", 35),
                new Client(4, "Lol", 18),
                new Client(5, "Cheburek", 55),
        };

        clients[0].setPhones(new ArrayList<Phone>(List.of(
                new Phone(12345678, PhoneType.MOBILE),
                new Phone(12451251, PhoneType.LANDLINE),
                new Phone(23523525, PhoneType.MOBILE),
                new Phone(23523523, PhoneType.LANDLINE),
                new Phone(35463634, PhoneType.MOBILE)
        )));

        clients[1].setPhones(new ArrayList<Phone>(List.of(
                new Phone(13464164, PhoneType.MOBILE),
                new Phone(98677143, PhoneType.LANDLINE),
                new Phone(24363117, PhoneType.MOBILE),
                new Phone(65135161, PhoneType.MOBILE),
                new Phone(87435884, PhoneType.LANDLINE)
        )));

        clients[2].setPhones(new ArrayList<Phone>(List.of(
                new Phone(98762369, PhoneType.LANDLINE),
                new Phone(13256326, PhoneType.MOBILE),
                new Phone(23523525, PhoneType.MOBILE),
                new Phone(14361363, PhoneType.LANDLINE),
                new Phone(13461341, PhoneType.MOBILE)
        )));

        clients[3].setPhones(new ArrayList<Phone>(List.of(
                new Phone(12345678, PhoneType.MOBILE),
                new Phone(46328728, PhoneType.LANDLINE),
                new Phone(80723151, PhoneType.LANDLINE),
                new Phone(23523523, PhoneType.LANDLINE),
                new Phone(87362563, PhoneType.LANDLINE)
        )));

        clients[4].setPhones(new ArrayList<Phone>(List.of(
                new Phone(23857662, PhoneType.MOBILE),
                new Phone(23526277, PhoneType.MOBILE),
                new Phone(23957862, PhoneType.MOBILE),
                new Phone(23897562, PhoneType.LANDLINE),
                new Phone(23986299, PhoneType.MOBILE)
        )));


        /**
         * Задача 4
         */

    }
}
