import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class RecursionBasic {
    //Write a function called power which accepts a base and an exponent. The function should return the power of the base to the exponent. 
    //This function should mimic the functionality of Math.pow() - do not worry about negative bases and exponents.
    //Assumptions: No negative bases or exponents, not nulls
    static int power(int base, int pow) {
        //base case
        if(pow == 0) return 1;
        return base * power(base, pow - 1);

    }

    //Write a function factorial which accepts a number and returns the factorial of that number. 
    //A factorial is the product of an integer and all the integers below it; 
    //e.g., factorial four (4!) is equal to 24, because 4 *3* 2 * 1 equals 24. factorial zero (0!) is always 1.
    static int factorial(int n) {
        if(n == 0) return 1;
        return n * factorial(n-1);
    }

    //Write a function called productOfArray which takes in an array of numbers and returns the product of them all.
    static int productOfArray(List<Integer> arr) {
        //base case
        if(arr.size() == 0) return 1;
        //different input function call
        return arr.remove(arr.size() - 1) * productOfArray(arr);
    }  

    //Write a function called recursiveRange which accepts a number and adds up all the numbers from 0 to the number passed to the function
    //if r(3) -> 3 + r(2) -> 3 + (2+r(1)) -> 3 + (2+(1+r(0))) -> 3 + 2 + 1 + 0
    static int recursiveRange(int n) {
        //base case
        if(n == 0) return 0;
        //different input function call
        return n + recursiveRange(n - 1);
    }  

    

    public static void main(String[] args) {
        System.out.println(power(2,4)); // 16
        System.out.println(factorial(4)); // 24
        System.out.println(productOfArray(new LinkedList<Integer>(Arrays.asList(1,2,3,10)))); // 60    
        System.out.println(recursiveRange(10));//55
    }


}
