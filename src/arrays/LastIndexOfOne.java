package arrays;// { Driver Code Starts
//Initial Template for Java

//Initial Template for Java

import java.lang.*;
import java.io.*;

class LastIndexOfOne {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while(t-->0)
        {
            String s = br.readLine();

            LastIndexOfOneSolution obj = new LastIndexOfOneSolution();
            System.out.println(obj.lastIndex( s));

        }
    }
}
// } Driver Code Ends


//User function Template for Java


class LastIndexOfOneSolution {
    public int lastIndex( String s) {

        int i;

        int length = s.length();

        for(i=length-1; i>=0; i--) {
            if(s.charAt(i) == '1') {
                return i;
            }
        }

        return -1;
    }
}
