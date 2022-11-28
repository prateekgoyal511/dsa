package stacksqueues;

import java.util.Stack;

//https://leetcode.com/problems/decode-string/submissions/
class DecodeString {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<Character>();
        int i;
        int n = s.length();
        char ch;
        char poppedch;
        int numberOfTimes;
        int j;
        char ch2;

        for(i=0; i<n;i++) {
            ch = s.charAt(i);
            //If it is not a closing bracket => put that in stack.
            if(ch != ']') {
                //We need chars on stack which are not processed. We also need opening brackets to group the
                //chars into blocks in which they are to be processed. We also need numbers in stack to figure
                //out, how they are to be processed
                System.out.println("Pushed to stack from original string: " + ch);
                stack.push(ch);
            } else {
                poppedch = stack.pop();
                String toProcess = "";
                //Pop all chars till opening bracket. They are to be processed together
                while(poppedch != '[') {
                    toProcess = poppedch + toProcess;
                    poppedch = stack.pop();
                }
                System.out.println("toProcess: " + toProcess);
                //By now, toProcess has the string that is to be processed together.
                //After opening bracket, there must be a number.
                numberOfTimes = getTopmostNumber(stack);
                System.out.println("numberOfTimes: " + numberOfTimes);
                //Get the string after it is repeated n times
                String processedString = processString(toProcess, numberOfTimes);
                System.out.println("processedString: " + processedString);
                //However, we may have to process this string also as this may also be inside []. So, we should
                //put this in stack too
                for(j=0; j<processedString.length(); j++) {
                    ch2 = processedString.charAt(j);
                    stack.push(ch2);
                }
            }
        }
        //By now, the stack should contain our result and it should not contain any opening or closing brackets
        //or even numbers
        String result = "";
        while(!stack.isEmpty()) {
            ch = stack.pop();
            result = ch + result;
        }
        return result;
    }

    private String processString(String str, int n) {
        int i;
        StringBuilder sb = new StringBuilder();
        for(i=0; i<n; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    private int getTopmostNumber(Stack<Character> stack) {
        Character ch;
        ch = stack.peek();
        int result=0;
        int power = 0;
        int multiplier;
        while(Character.isDigit(ch)) {
            //If it is a digit, we can pop it.
            stack.pop();
            multiplier = (int)Math.pow(10, power);
            power++;
            result = Character.getNumericValue(ch)*multiplier + result;
            if(stack.isEmpty()) break;
            ch = stack.peek();
        }
        return result;
    }
}
