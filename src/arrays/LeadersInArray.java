package arrays;// { Driver Code Starts
import java.io.*;
import java.util.*;

class LeadersInArray {

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); //Inputting the testcases

        while(t-->0){

            //input size of array
            int n = Integer.parseInt(br.readLine().trim());
            int arr[] = new int[n];
            String inputLine[] = br.readLine().trim().split(" ");

            //inserting elements in the array
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(inputLine[i]);
            }

            LeadersInArraySolution obj = new LeadersInArraySolution();

            StringBuffer str = new StringBuffer();
            ArrayList<Integer> res = new ArrayList<Integer>();

            //calling leaders() function
            res = obj.leaders(arr, n);

            //appending result to a String
            for(int i=0; i<res.size(); i++){
                str.append(res.get(i)+" ");
            }

            //printing the String
            System.out.println(str);
        }

    }
}
// } Driver Code Ends


class LeadersInArraySolution{
    //Function to find the leaders in the array.
    static ArrayList<Integer> leaders(int arr[], int n){
        // Your code here
        // We will go through the array right to left and put all leaders
        // that we encounter in this stack.
        Stack<Integer> leadersStack = new Stack<Integer>();

        //Last element is already a leader.
        leadersStack.push(arr[n-1]);

        int i;
        //Travel right to left. Keep the maximum element seen so far in a stack.
        //Stack keeps not just maximum element seen so far, but also, maximum
        // element seen in order.
        for(i=n-2; i>=0; i--) {
            if(arr[i] >= leadersStack.peek()) {
                leadersStack.push(arr[i]);
            }
        }

        return stackToList(leadersStack);
    }

    static ArrayList<Integer> stackToList(Stack<Integer> stack) {
        Integer element;

        ArrayList<Integer> result = new ArrayList<Integer>();
        while(!stack.isEmpty()) {
            element = stack.pop();
            result.add(element);
        }
        return result;
    }
}
