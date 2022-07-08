package string;

// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class PermutationsOfAString
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while(t-->0)
        {
            String S = br.readLine().trim();
            PermutationsOfAStringSolution obj = new PermutationsOfAStringSolution();
            List<String> ans = obj.find_permutation(S);
            for( int i = 0; i < ans.size(); i++)
            {
                System.out.print(ans.get(i)+" ");
            }
            System.out.println();

        }
    }
}

// } Driver Code Ends


class PermutationsOfAStringSolution {
    public List<String> find_permutation(String S) {
        // Code here
        // Need to work on sorted string to get output in lexicographic order
        String sortedString = sortString(S);
        // This map will store already precomputed results.
        Map<String, Set<String>> storedResults = new HashMap<String, Set<String>>();

        Set<String> resultSet = permuteUtil(S, storedResults);

        // Convert set to list
        List<String> resultList = new ArrayList<String>(resultSet);
        Collections.sort(resultList);

        return resultList;
    }

    private static Set<String> permuteUtil(String S, Map<String, Set<String>> map) {

        //If the result has already been precomputed.
        if(map.containsKey(S)) {
            return map.get(S);
        }

        int i = 0;
        int n = S.length();

        String swappedString;
        char firstCharacter;
        String relevantSubstring;
        String originalString;

        Set<String> subResults;
        Set<String> appendedResults;
        Set<String> finalResults = new HashSet<String>();

        if(n==1) {
            Set<String> result = new HashSet<String>();
            result.add(S);
            return result;
        }

        for(i=0; i<n; i++) {
            //Swap the first letter of the string one by one.
            swappedString = swap(S, i);
            // System.out.println("swappedString: " + swappedString);
            //We need to note down the first character as this will be appended
            // to the results of the remaining string.
            firstCharacter = swappedString.charAt(0);
            // System.out.println("firstCharacter: " + firstCharacter);
            //Now, we'll run our method on this substring.
            relevantSubstring = swappedString.substring(1);
            // System.out.println("relevantSubstring: " + relevantSubstring);
            subResults = permuteUtil(relevantSubstring, map);
            // System.out.println("subResults: " + subResults);
            //Now, we get the results by appending first character
            appendedResults = appendFirstCharacter(subResults, firstCharacter);
            // System.out.println("appendedResults: " + appendedResults);

            finalResults.addAll(appendedResults);
            // System.out.println("finalResults: " + finalResults);
        }

        map.put(S, finalResults);
        return finalResults;
    }

    private static String swap(String S, int i) {
        char[] charactersArray = S.toCharArray();
        char temp;

        temp = charactersArray[0];
        charactersArray[0] = charactersArray[i];
        charactersArray[i] = temp;

        return new String(charactersArray);
    }

    private static Set<String> appendFirstCharacter(Set<String> results, char ch) {
        Set<String> finalResult = new HashSet<String>();
        Iterator<String> resultsIterator = results.iterator();

        String currentString;
        String newString;
        while(resultsIterator.hasNext()) {
            currentString = resultsIterator.next();
            newString = ch + currentString;
            finalResult.add(newString);
        }

        return finalResult;
    }

    private static String sortString(String s) {
        char[] charactersArray = s.toCharArray();
        Arrays.sort(charactersArray);
        return new String(charactersArray);
    }
}
