package hellocucumber;

public class IsItFriday {
    static String isItFriday(String today) {
        if (!"Friday".equalsIgnoreCase(today)) {
            return "Nope";
        }
        return "Yep";
    }
}
