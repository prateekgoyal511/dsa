package string;

// { Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class RomanNumberToInteger {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine().trim());
        while (tc-- > 0) {
            String roman = br.readLine().trim();
            RomanNumberToIntegerSolution ob = new RomanNumberToIntegerSolution();
            System.out.println(ob.romanToDecimal(roman));
        }
    }
}// } Driver Code Ends


// User function Template for Java

class RomanNumberToIntegerSolution {
    // Finds decimal value of a given roman numeral
    public int romanToDecimal(String str) {
        // code here
        //Declare map to store these special stuff
        Map<Character, Integer> numeralsMap = new HashMap<Character, Integer>();

        //Fill map with relevant stuff.
        fillMap(numeralsMap);

        //To iterate over string
        int i=0;

        // To track the number as it forms.
        int number = 0;

        //Value in one iteration to be added to number.
        int currentValue;

        int n = str.length();

        Character currentChar;
        Character nextChar;

        if(str.length() == 1) {
            return numeralsMap.get(Character.valueOf(str.charAt(0)));
        }

        //We can't use for loop because we don't know for sure in every iteration
        //that we'll increase i by 1 or 2.
        // i<n-1 because we are also computing nextChar in the same iteration
        //So, we don't want ArrayIndexOutOfBounds
        while(i<n-1) {
            //We need currentChar and nextChar to figure out if we need
            //to read the two characters together or separately.
            currentChar = Character.valueOf(str.charAt(i));
            nextChar = Character.valueOf(str.charAt(i+1));

            //If next char is greater
            if(numeralsMap.get(nextChar) > numeralsMap.get(currentChar)) {
                currentValue = numeralsMap.get(nextChar) - numeralsMap.get(currentChar);
                number = number + currentValue;
                i = i+2;
            } else {
                currentValue = numeralsMap.get(currentChar);
                number = number + currentValue;
                i = i+1;
            }
        }

        //It is possible that last character was not covered in the loop.
        if(i == n-1) {
            currentChar = Character.valueOf(str.charAt(i));
            currentValue = numeralsMap.get(currentChar);
            number = number + currentValue;
        }

        return number;
    }

    private static void fillMap(Map<Character, Integer> map) {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }
}
