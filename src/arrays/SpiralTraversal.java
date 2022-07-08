package arrays;// { Driver Code Starts
import java.io.*;
import java.util.*;
class SpiralTraversal
{
    public static void main(String args[])throws IOException
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-- > 0)
        {
            int r = sc.nextInt();
            int c = sc.nextInt();

            int matrix[][] = new int[r][c];

            for(int i = 0; i < r; i++)
            {
                for(int j = 0; j < c; j++)
                    matrix[i][j] = sc.nextInt();
            }
            SpiralTraversalSolution ob = new SpiralTraversalSolution();
            ArrayList<Integer> ans = ob.spirallyTraverse(matrix, r, c);
            for (Integer val: ans)
                System.out.print(val+" ");
            System.out.println();
        }
    }
}// } Driver Code Ends


class SpiralTraversalSolution
{
    //Function to return a list of integers denoting spiral traversal of matrix.
    static ArrayList<Integer> spirallyTraverse(int matrix[][], int r, int c)
    {
        // code here
        //Direction = 0 => Left to right
        //Direction = 1 => Top to bottom
        // Direction = 2 => Right to left
        // Direction = 3 => Bottom to top
        int direction = 0;

        int b0 = c-1; //Boundary for train coming from left to right
        int b1 = r-1; //Boundary for train coming from top to bottom
        int b2 = 0; //Boundary for train coming from right to left
        int b3 = 0; //Boundary for train coming from bottom to top.

        //i and j indicate current position
        int i = 0;
        int j = 0;

        ArrayList<Integer> result = new ArrayList<Integer>();

        //Till the time none of the boundaries meet, we can keep printing.
        while(b0>=b2 && b1>=b3) {
            result.add(matrix[i][j]);
            //If travelling from left to right
            if(direction == 0) {
                //If not reached the boundary => move to next.
                if(j<b0) {
                    j++;
                    continue;
                } else {
                    i++; //If on boundary, direction changes.
                    b3 = b3+1; //Now, top layer is done. No one should reach there.
                    direction = 1;
                    continue;
                }
            }


            if(direction == 1) {
                if(i<b1) {
                    i++;
                    continue;
                } else {
                    j--;
                    b0 = b0-1;
                    direction = 2;
                    continue;
                }
            }

            if(direction == 2) {
                if(j>b2) {
                    j--;
                    continue;
                } else {
                    i--;
                    b1 = b1-1;
                    direction = 3;
                    continue;
                }
            }

            if(direction == 3) {
                if(i>b3) {
                    i--;
                    continue;
                } else {
                    j++;
                    b2 = b2+1;
                    direction = 0;
                    continue;
                }
            }

        }
        return result;

    }
}


