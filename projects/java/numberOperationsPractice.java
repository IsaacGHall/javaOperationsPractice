import java.util.Random;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//for some reason VSCode wouldn't like me continually updating things. It would bug out the colored text changes, and cause errors. 
//So after copying and pasting my own code back and forth, it then resolved the errors. I'll probably not use VSCode for Java in the future. 
public class numberOperationsPractice extends JFrame {
    private static Random rand = new Random(); //rng, moved for use in more than one method
    private JTextArea outputArea; //private text area instance for Swing

    public numberOperationsPractice() {
        setTitle("Number Operations"); //title of JFrame window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //default close to exit
        setLayout(new BorderLayout()); //set layout manager

        outputArea = new JTextArea(10, 40); //text area is 10 rows 40 cols
        outputArea.setEditable(false); //not editable
        JScrollPane scrollPane = new JScrollPane(outputArea); //create scrollpane for output area
        add(scrollPane, BorderLayout.CENTER); //scrollpane to center

        JButton generateButton = new JButton("Generate Numbers"); //button in java
        generateButton.addActionListener(new ActionListener() { //eventListener
            @Override //override intends to override another method 
            public void actionPerformed(ActionEvent e) { //if button pressed
                generateNumbers(); //run func
            }
        });
        add(generateButton, BorderLayout.SOUTH); //button to the south

        pack(); //set to preferred size
        setLocationRelativeTo(null); //centered
        setVisible(true); 
    }

    private void generateNumbers() {
        int factorialNum = rand.nextInt(19) + 1; //bounds set at 19, this is the Long.MAX_VALUE's limit.
        int primeNum = rand.nextInt(2147483646) + 1; //maximum int value bounds + 1
        int fibCount = rand.nextInt(11) + 5;
        int n = rand.nextInt(10) + 1;

        StringBuilder output = new StringBuilder();
        output.append("\n--Number Generation--\n"); //all console log lines changed to output lines.
        output.append("Factorial of ").append(factorialNum).append(" is: ").append(calculateFactorial(factorialNum)).append("\n"); //changed to append string for use w/ swing
        output.append("Prime number check: ").append(primeNum).append(" is ").append(isPrime(primeNum) ? "prime" : "not prime").append("\n"); //ternary operator allows for shorthand, basically checking if true or not, and then displaying the correct string.
        output.append("Fibonacci series up to ").append(fibCount).append(" terms: ");
        generateFibonacci(output, fibCount);
        output.append("\n\n---Array Generation---\n");
        int[] array = generateRandomArray(n);
        output.append("Bubble sorted array: \n");
        bubbleSort(array);
        printArray(output, array);
        array = generateRandomArray(n);
        output.append("Merge sorted array: \n");
        mergeSort(array, 0, array.length - 1);
        printArray(output, array);

        outputArea.setText(output.toString());
    }

    private long calculateFactorial(int number) { //using long here since it allows for larger values above 2,147,483,647. 
        if (number == 0 || number == 1) {
            return 1;
        } else {
            return number * calculateFactorial(number - 1);
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(number); i++) {  //check factors if they are divisible.
                if (number % i == 0) { //if there is no remainder to the number, it is divisible.
                    return false;
                }
            }
            return true;
        }
    }

    private void generateFibonacci(StringBuilder output, int count) {
        int num1 = 0, num2 = 1;
        if (count >= 1){
            output.append(num1).append(" "); //print num1
        }
        if (count >= 2){
            output.append(num2).append(" "); //print num2
        }
         //F(n) = F(n-1) + F(n-2)
        for (int i = 3; i <= count; i++) { //now that 1 and 2 are accounted for, for every number equal to or more than 3.
            int sum = num1 + num2; //x = n1 + n2
            output.append(sum).append(" ");
            num1 = num2; //set num1 to num2
            num2 = sum; //set num2 to sum.
        }
    }

    private void bubbleSort(int[] array) {
        int n = array.length; //get length
        for (int i = 0; i < n - 1; i++) { //outer loop runs n-1 passes over array, n is array.length
            for (int j = 0; j < n - i - 1; j++) { //inner loop runs from start to unsorted portion of array
                if (array[j] > array[j + 1]) { //if current element > next element, sort next element.
                    //swap arrays
                    int temp = array[j];
                    array[j] = array[j + 1]; //asign j+1 val to j array
                    array[j + 1] = temp; //assign temp value
                }
            }
        }
    }

    private void mergeSort(int[] array, int left, int right) {
         //checking if left < right, and if not, array/subarray is already sorted or only has 1 element
        if (left < right) {
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

    private void merge(int[] array, int left, int middle, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;
        //create temp arrays for subarrays
        int[] L = new int[n1];
        int[] R = new int[n2];
        //copy use arraycopy to temporary arrays
        System.arraycopy(array, left, L, 0, n1);
        for (int j = 0; j < n2; j++) {
            //Merge the temporary arrays back into the original array
            R[j] = array[middle + 1 + j];
        }
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
        int k = left;
        // Merge elements from L[] and R[] based on their values
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

    private int[] generateRandomArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt(100); //special rand n for arrays only
        }
        return array;
    }

    private void printArray(StringBuilder output, int[] array) {
        for (int i = 0; i < array.length; i++) {
            output.append(array[i]).append(" "); //replaced system.out.println
        }
        output.append("\n"); 
    }

    public static void main(String[] args) {
        //task scheduling for the event thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //make an instance of the GUI.
                new numberOperationsPractice();
            }
        });
    }
}