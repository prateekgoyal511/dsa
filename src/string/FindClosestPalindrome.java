package string;

//Problem - https://leetcode.com/problems/find-the-closest-palindrome/description/
//Best explanation - https://leetcode.com/problems/find-the-closest-palindrome/solutions/147949/thinking-process/?orderBy=most_votes
class FindClosestPalindrome {
    public String nearestPalindromic(String n) {
        long nl = Long.parseLong(n);

        //If it is less than 10
        if(nl <= 10) {
            return "" + (nl-1);
        }

        //If it is something like 10, 100, 1000, 10000 => 9, 99, 999, 9999
        boolean isCase1 = checkIfCase1(n);
        if(isCase1) {
            return "" + (nl-1);
        }

        //If it is something like 11, 101, 1001, 10001 => 9, 99, 999
        boolean isCase2 = checkIfCase2(n);
        if(isCase2) {
            return "" + (nl-2);
        }

        //If it is something like 9, 99, 999, 9999 => 11, 101, 1001
        boolean isCase3 = checkIfCase3(n);
        if(isCase3) {
            return "" + (nl+2);
        }

        //First half of number, if repeated in reverse can form a palindrome
        Long rootOfNumber = findRootOfNumber(n);
        //First half of number+1
        Long higherThanRootOfNumber = rootOfNumber+1;
        //First half of number-1
        Long lowerThanRootOfNumber = rootOfNumber-1;
        boolean isEvenDigits = checkIfEvenDigits(n);

        //Make closest palindrome by using above 3 numbers as roots
        Long nextPalindromeEqual = makePalindromeFromRoot(rootOfNumber, isEvenDigits);
        System.out.println("nextPalindromeEqual: " + nextPalindromeEqual);
        Long nextPalindromeBigger = makePalindromeFromRoot(higherThanRootOfNumber, isEvenDigits);
        System.out.println("nextPalindromeBigger: " + nextPalindromeBigger);
        Long nextPalindromeSmaller = makePalindromeFromRoot(lowerThanRootOfNumber, isEvenDigits);
        System.out.println("nextPalindromeSmaller: " + nextPalindromeSmaller);

        //Closest among the 3 is our answer
        Long diffBigger = Math.abs(nextPalindromeBigger - nl);
        System.out.println("diffBigger: " + diffBigger);
        Long diffSmaller = Math.abs(nextPalindromeSmaller - nl);
        System.out.println("diffSmaller: " + diffSmaller);
        Long diffEqual = Math.abs(nextPalindromeEqual - nl);
        System.out.println("diffEqual: " + diffEqual);

        if(diffEqual == 0) {
            if(diffBigger < diffSmaller) {
                return "" + nextPalindromeBigger;
            } else {
                return "" + nextPalindromeSmaller;
            }
        } else {
            if(diffSmaller <= diffBigger && diffSmaller <= diffEqual) {
                return "" + nextPalindromeSmaller;
            } else if(diffEqual <= diffBigger && diffEqual <= diffSmaller) {
                return "" + nextPalindromeEqual;
            } else {
                return "" + nextPalindromeBigger;
            }
        }
    }

    private boolean checkIfCase1(String n) {
        //If it is something like 10, 100, 1000, 10000 => 9, 99, 999, 9999
        //If number%10==0. But, 500%10 is also equal to 0. If number starts with 1
        //But, 110 also starts with 1. If rest of the number except first digit will be 0.

        Long nl = Long.parseLong(n);
        boolean mod10IsZero = (nl%10 == 0);
        boolean numberStartsWith1 = (n.charAt(0) == '1');
        boolean restOfNumberIsZero = (Long.parseLong(n.substring(1)) == 0);
        return (mod10IsZero && numberStartsWith1 && restOfNumberIsZero);
    }

    private boolean checkIfCase2(String n) {
        //If it is something like 11, 101, 1001, 10001 => 9, 99, 999
        //Number%10 should be 1. But, 21 is also that. Number should start with 1.
        //But, 121 is also that. Rest of the digits should be 0.
        Long nl = Long.parseLong(n);
        boolean mod10IsOne = (nl%10 == 1);
        boolean numberStartsWith1 = (n.charAt(0) == '1');
        boolean restOfNumberIsLessThan10 = (Long.parseLong(n.substring(1)) < 10);
        return mod10IsOne && numberStartsWith1 && restOfNumberIsLessThan10;
    }

    private boolean checkIfCase3(String n) {
        int i;
        int len = n.length();
        char ch;
        for(i=0; i<len; i++) {
            ch = n.charAt(i);
            if(ch != '9') return false;
        }
        return true;
    }

    private Long findRootOfNumber(String n) {
        int len = n.length();
        int midIndex = len/2;
        String rootString;
        if(len%2 == 0) {
            rootString = n.substring(0, midIndex);
        } else {
            rootString = n.substring(0, midIndex+1);
        }
        return Long.parseLong(rootString);
    }

    private boolean checkIfEvenDigits(String n) {
        return (n.length() %2 == 0);
    }

    private Long makePalindromeFromRoot(Long root, boolean isEvenDigits) {
        String rootStr = "" + root;
        String rootStrReversed = new StringBuilder("" + root).reverse().toString();
        String palindromeStr;
        if(isEvenDigits) {
            palindromeStr = rootStr + rootStrReversed;
        } else {
            palindromeStr = rootStr + rootStrReversed.substring(1);
        }
        return Long.parseLong(palindromeStr);
    }
}
