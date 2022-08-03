package backtracking;

import java.util.HashSet;
import java.util.Set;

public class FindAllSubsequences {

    // Driver code
    public static void main(String[] args)
    {
        String str = "abc";
        FindAllSubsequencesSolution fas = new FindAllSubsequencesSolution();

        Set<String> result = new HashSet<String>();
        result = fas.findSubSeq(str);

        System.out.println(result);
    }
}

class FindAllSubsequencesSolution {

    public static Set<String> findSubSeq(String s) {
        int n = s.length();
        //This variable will represent a candidate solution. Every element of this will be either 0 or 1.
        //Every position of this array corresponds to the character in the string at the same position.
        //Value 0 represents we are not including that character in the candidate solution. Value 1
        //represents we are including that character in the candidate solution.
        int[] candidateSolution = new int[n];
        //This variable represents the stage at which we have to make choice. The first stage at which we
        //have to make choice is first index of the string.
        int choiceStage = 0;

        //This variable will hold the final results.
        Set<String> resultSet = new HashSet<String>();


        findSubSeqUtil(s, candidateSolution, choiceStage, resultSet);
        return resultSet;
    }

    private static void findSubSeqUtil(String s, int[] candidateSolution, int choiceStage, Set<String> resultSet) {
        //If all choice stages have been covered and we have made one or the other choice at every stage.
        if(choiceStage == s.length()) {
            //CandidateSolution holds values 0/1 in all of its positions. Use that to convert to the relevant string
            //It is our subsequence.
            String ans = convertToString(candidateSolution, s);
            if(!ans.isBlank()) {
                resultSet.add(ans);
            }
            return;
        }

        //Exercising choice when we choose not to include the character at this position
        candidateSolution[choiceStage] = 0;
        findSubSeqUtil(s, candidateSolution, choiceStage+1, resultSet);

        //Exercising choice when we choose to include the character at this position
        candidateSolution[choiceStage] = 1;
        findSubSeqUtil(s, candidateSolution, choiceStage+1, resultSet);
    }

    private static String convertToString(int[] candidateSolution, String s) {
        int i;
        String ans = "";
        for(i=0; i<s.length(); i++) {
            if(candidateSolution[i] == 1) {
                ans = ans + s.charAt(i);
            }
        }
        return ans;
    }

}
