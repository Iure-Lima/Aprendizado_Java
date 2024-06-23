
public class Main {
    public static void main(String[] args) {
        int intValue = 10;
        long longValue = intValue; // Casting implícito

        System.out.print(intValue);

        long longValue2 = 1000;
        int intValue2 = (int) longValue2; // Casting explícito

        System.out.println(intValue2);

        char a = 'A';
        String A = String.valueOf(a);
        System.out.println(a);
    }
}