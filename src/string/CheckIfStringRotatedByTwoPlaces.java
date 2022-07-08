package string;

// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class CheckIfStringRotatedByTwoPlaces {

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t;
        t = sc.nextInt();
        String s12 = sc.nextLine();
        for(int i=0; i<t; i++){
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();

            CheckIfStringRotatedByTwoPlacesSolution obj = new CheckIfStringRotatedByTwoPlacesSolution();

            boolean flag = obj.isRotated(s1, s2);

            if(flag == true) System.out.println("1");
            else System.out.println("0");

        }
    }
}// } Driver Code Ends


class CheckIfStringRotatedByTwoPlacesSolution
{
    //Function to check if a string can be obtained by rotating
    //another string by exactly 2 places.
    public static boolean isRotated(String str1, String str2)
    {
        // Your code here

        if(str1.length() != str2.length()) return false;

        if(str1.length() == 1) {
            if(str1.equals(str2)) {
                return true;
            } else {
                return false;
            }
        }

        String str1CW = rotateCWTwo(str1);
        // System.out.println("str1CW: " + str1CW);
        if(str1CW.equals(str2)) return true;

        String str1ACW = rotateACWTwo(str1);
        // System.out.println("str1ACW: " + str1ACW);
        if(str1ACW.equals(str2)) return true;

        return false;

    }

    private static String rotateCWTwo(String str) {
        if(str.length() == 2) return str;

        int n = str.length();

        // System.out.println("2nd last character: " + str.charAt(n-2));

        // System.out.println("2nd last character and last: " + str.charAt(n-2) + str.charAt(n-1));

        // System.out.println(str.charAt(n-2) + str.charAt(n-1) + str.substring(0, n-2));
        return ("" + str.charAt(n-2) + str.charAt(n-1) + str.substring(0, n-2));
    }

    private static String rotateACWTwo(String str) {
        if(str.length() == 2) return str;

        int n = str.length();
        return (str.substring(2) + str.charAt(0) + str.charAt(1));
    }

}
