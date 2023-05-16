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

public static void generateFibonacci(int count){
    int num1 = 0, num2 = 1;
    System.out.print("Fibonaci series up to " + count + " terms: ");
    
    if (count >= 1) { //count greater than or equal to one, when count is 1 only num1 needs to be printed.
        System.out.print(num1 + " ");
    }
    if (count >= 2) { //count greater than or equal to two, when count is 2 only num1 and num2 need to be printed.
        System.out.print(num2 + " "); 
    }
    //F(n) = F(n-1) + F(n-2)
    for (int i = 3; i <= count; i++) { //now that 1 and 2 are accounted for, for every number equal to or more than 3.
        int sum = num1 + num2; //x = n1 + n2
        System.out.print(sum + " ");
        num1 = num2; //set num1 to num2
        num2 = sum; //set num2 to sum. 
    }
    
}

    public static void main(String[] args) {
        Random rand = new Random(); //rng
        int factorialNum = rand.nextInt(19)+1; //bounds set at 19, this is the Long.MAX_VALUE's limit.
        int primeNum = rand.nextInt(2147483646)+1; //maximum int value bounds + 1
        int fibCount = rand.nextInt(11)+5;
        System.out.println("Factorial of " + factorialNum + " is: " + calculateFactorial(factorialNum)); 
        System.out.println("Prime number check: " + primeNum + " is " + (isPrime(primeNum) ? "prime" : "not prime")); //ternary operator allows for shorthand, basically checking if true or not, and then displaying the correct string.
        generateFibonacci(fibCount);
        System.out.println();
    }
}
