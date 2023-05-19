import java.util.Random;
//import java.util.Arrays;
//import java.util.Scanner;\





public class numberOperationsPractice {
    private static Random rand = new Random(); //rng, moved for use in more than one method
    public static void main(String[] args) {        
        int factorialNum = rand.nextInt(19)+1; //bounds set at 19, this is the Long.MAX_VALUE's limit.
        int primeNum = rand.nextInt(2147483646)+1; //maximum int value bounds + 1
        int fibCount = rand.nextInt(11)+5;
        int n = rand.nextInt(10) + 1;
        System.out.println("\n--Number Generation--");
        System.out.println("Factorial of " + factorialNum + " is: " + calculateFactorial(factorialNum)); 
        System.out.println("Prime number check: " + primeNum + " is " + (isPrime(primeNum) ? "prime" : "not prime")); //ternary operator allows for shorthand, basically checking if true or not, and then displaying the correct string.
        generateFibonacci(fibCount);
        System.out.println();
        System.out.println("\n---Array Generation---");
        int[] array = generateRandomArray(n);
        System.out.println("Bubble sorted array: ");
        bubbleSort(array);
        printArray(array);
        array = generateRandomArray(n);
        System.out.println("Merge sorted array: ");
        mergeSort(array, 0, array.length - 1);
        printArray(array);
    }

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

private static void bubbleSort(int[] array){
    int x = array.length; //get length
    for (int i = 0; i < x - 1; i++) //outer loop runs n-1 passes over array, n is array.length
        for (int j = 0; j < x - i - 1; j++){ //inner loop runs from start to unsorted portion of array
            if (array[j] > array[j+1]){ //if current element > next element, sort next element.
                //swap arrays
                int temp = array[j]; 
                array[j] = array[j+1]; //assign j+1 val to j array
                array [j+1] = temp; //assign temp value
            }
        }

}

private static void mergeSort(int[] array, int left, int right){
    //checking if left < right, and if not, array/subarray is already sorted or only has 1 element
    if (left < right){
        //calc middle index
        int middle = (left + right) / 2;

        //recursively call mergeSort for left
        mergeSort(array, left, middle);
        //recursively call for right
        mergeSort(array, middle + 1, right);
        //call merge function to sort subarrays
        merge(array, left, middle, right);

    }
}

private static void merge(int[] array, int left, int middle, int right) {
    // Find sizes of two subarrays to be merged
    int n1 = middle - left + 1;
    int n2 = right - middle;

    //create temp arrays for subarrays
    int[] L = new int[n1];
    int[] R = new int[n2];

    //copy use arraycopy to temporary arrays
    System.arraycopy(array, left, L, 0, n1);
    for (int j = 0; j < n2; j++)
        R[j] = array[middle + 1 + j];

    //Merge the temporaray arrays back into the original array

    // Initial indexes of first and second subarrays
    int i = 0, j = 0;

    // Merge elements from L[] and R[] based on their values
    int k = left;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) { //if L[] < or equal to R[]
            array[k] = L[i]; //assign k to L[i]
            i++;
        } else {
            array[k] = R[j]; //assign k to R[i]
            j++;
        }
        k++;
    }

    //copy any remaining elements of L[] if any
    while (i < n1) {
        array[k] = L[i];
        i++;
        k++;
    }

    //copy remaining elements of R[] if any
    while (j < n2) {
        array[k] = R[j];
        j++;
        k++;
    }
}

private static int[] generateRandomArray(int n) {
    int[] array = new int[n];
    for (int i = 0; i < n; i++) {
        array[i] = rand.nextInt(100); //special rand n for arrays only
    }
    return array;
}

private static void printArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
        System.out.print(array[i] + " ");
    }
    System.out.println();
}
}

