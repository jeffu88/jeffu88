package MidTerm17;

public class Main {
        public static void main(String[] args) {
            int x = 1;
            int y = 10;
            int z = 2;

            int sum = sumOfNumbersBetweenXAndYThatAreDivisibleByZ(x, y, z);
            System.out.println("The sum of numbers between " + x + " and " + y + " that are divisible by " + z + " is: " + sum);
        }

        public static int sumOfNumbersBetweenXAndYThatAreDivisibleByZ(int x, int y, int z) {
            int sum = 0;

            for (int i = x; i <= y; i++) {
                if (i % z == 0) {
                    sum += i;
                }
            }

            return sum;
        }
    }