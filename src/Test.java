import java.util.regex.Pattern;

class demo {
    public demo() {
        System.out.println("have no params");
    }
    public demo(int a) {
        System.out.println("have a param:" + a);
    }
    @Override
    public String toString() {
        return "demo:";
    }
}

public class Test {
    private static Pattern p = Pattern.compile("(\\w+\\.)|final |native ");
    public static void main(String[] args) throws ClassNotFoundException {
        Test t = new Test();
    }
}
