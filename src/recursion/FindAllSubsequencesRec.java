package recursion;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FindAllSubsequencesRec {

    // Driver code
    public static void main(String[] args)
    {
        String str = "abc";
        FindAllSubsequencesRecSolution fasr = new FindAllSubsequencesRecSolution();

        Set<String> result = new HashSet<String>();
        result = fasr.findSubSeq(str);

        System.out.println(result);
    }
}

class FindAllSubsequencesRecSolution {
    public static Set<String> findSubSeq(String s) {
        int n = s.length();

        Set<String> resultSet = new HashSet<String>();
        resultSet = findSubSeqUtil(s);

        //Last set will also have an empty string but empty string is not a valid subsequence.
        resultSet.remove("");
        return resultSet;
    }

    private static Set<String> findSubSeqUtil(String s) {

        Set<String> resultSet = new HashSet<String>();

        //Base condition is when String length is 1.
        if(s.length() == 1) {
            resultSet.add("");
            resultSet.add(s);
            return resultSet;
        }
        //This will hold results for whole of the string except first character.
        Set<String> partialResultSet = new HashSet<String>();
        partialResultSet = findSubSeqUtil(s.substring(1));

        resultSet = appendFirstCharToPartialResults(s.charAt(0), partialResultSet);
        return resultSet;
    }

    private static Set<String> appendFirstCharToPartialResults(Character ch, Set<String> partialResultSet) {
        Iterator<String> iterator = partialResultSet.iterator();
        //One string from partialResultSet
        String candidateString;
        //String formed by appending first character to a string from partialResultSet
        String newString;

        Set<String> newResultSet = new HashSet<String>();

        //Iterate over all partialResultSet strings, append first character to it and add them all to newResultSet
        while (iterator.hasNext()) {
            candidateString = iterator.next();
            newString = ch + candidateString;
            newResultSet.add(newString);
        }

        //Also, add whole partialResultSet to newResultSet since those are also valid subsequences.
        newResultSet.addAll(partialResultSet);
        //We add empty string too. This is needed because when this will serve as partialResultSet => it'll
        //allow subsequence of only the firstCharacter. Eg:- When newResultSet is answer to a substring started with
        //index 1 and index 0 had character 'c' => having an empty string will allow us to have 'c' as also one of the
        //subsequences in the final result of that string.
        newResultSet.add("");
        return newResultSet;
    }
}
