import java.util.Random;
//import java.util.Arrays;
//import java.util.Scanner;

public class numberOperationsPractice {
    public static long calculateFactorial(int number){ //using long here since it allows for larger values above 2,147,483,647. 
        if (number == 0 || number == 1) {
            return 1;
        } else {
            return number * calculateFactorial(number - 1); //n! = n * (n-1) * (n-2) ... * 1.

        }
    }

public static boolean isPrime(int number){
    if (number <= 1){
        return false;
    } else {
        for (int i = 2; i <= Math.sqrt(number); i++){ //check factors if they are divisible.
            if (number % 1 == 0) { //if there is no remainder to the number, it is divisible. 
                return false;
            }
        }
        return true;
    }
}
    public static void main(String[] args) {
        Random rand = new Random(); //rng
        int factorialNum = rand.nextInt(19)+1; //bounds set at 19, this is the Long.MAX_VALUE's limit.
        int primeNum = rand.nextInt(2147483646)+1; //maximum int value bounds + 1
        System.out.println("Factorial of " + factorialNum + " is: " + calculateFactorial(factorialNum)); 
        System.out.println("Prime number check: " + primeNum + " is " + (isPrime(primeNum) ? "prime" : "not prime")); //ternary operator allows for shorthand, basically checking if true or not, and then displaying the correct string.

    }


}
