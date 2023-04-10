package doubles;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        String delStringZero = "aab";
        String delStringOne = "aabb";
        String delStringTwo = "abfbaf";
        String delStringThree = "abccba";

        //System.out.println(deleteDoubleStack(delStringZero));
        System.out.println(properStackDeletion(delStringZero));

        //System.out.println(deleteDoubleStack(delStringOne));
        System.out.println(properStackDeletion(delStringOne));

        //System.out.println(deleteDoubleStack(delStringTwo));
        System.out.println(properStackDeletion(delStringTwo));

        //System.out.println(deleteDoubleStack(delStringThree));
        System.out.println(properStackDeletion(delStringThree));


    }

    static String deleteDoubleStack(String workString){

        Stack<Character> workStack = new Stack<>();
        Stack<Character> outStack = new Stack<>();

        for (int i = 0; i < workString.length(); i++){
            workStack.push(workString.charAt(i));
        }

        outStack.push(workStack.pop());

        while (!workStack.isEmpty()){

            if (workStack.peek() == outStack.peek()){
                workStack.pop();
                outStack.pop();
            }else {
                outStack.push(workStack.pop());
            }
            if (outStack.isEmpty() && !workStack.isEmpty()) {
                outStack.push(workStack.pop());
            }
        }
        return outStack.toString();
    }

    static String properStackDeletion(String baseString){
        Stack<Character> outStack = new Stack<>();

        outStack.push(baseString.charAt(0));
        for (int i = 1; i < baseString.length(); i++) {

            if (outStack.peek() == baseString.charAt(i)) {
                outStack.pop();
            } else {
                outStack.push(baseString.charAt(i));
            }

            if (outStack.isEmpty() && (i + 1 < baseString.length())) {
                outStack.push(baseString.charAt(i + 1));
            }
        }
        return outStack.toString();
    }


}
