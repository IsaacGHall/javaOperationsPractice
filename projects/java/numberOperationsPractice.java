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
    public static void main(String[] args) {
        Random rand = new Random(); //rng
        int factorialNum = rand.nextInt(20)+1; //bounds set at 20, this is the Long.MAX_VALUE's limit. 
        System.out.println("Factorial of " + factorialNum + " is: " + calculateFactorial(factorialNum)); 
    }


}
