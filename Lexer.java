import java.util.ArrayList;
import java.util.List;

/* @author Bhanu Kakulla 
    311 assignment 1- lexer
     
    5.23 - 8.5 + 3
    8 * -4 + 99999
    7 4 3 1 
    + - * /
 */
public class Lexer {

    public List<Token> lex(String line) {
        List<Token> toks = new ArrayList<Token>();

        char[] ch = line.toCharArray();

        int stateNum = 0;

        String str = "";

        // state 1= space
        // state 2= number
        // state 3 is operand
        if (line.length() == 0) {
            Token temp = new Token("");
            temp.setToken("EndOfLine");
            toks.add(temp);
            return toks;
        }

        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ' ') {// checks for space in case of the first line in the test file
                stateNum = 1;
            } else if (Character.isDigit(ch[i]) == true) { // if the string is a number go to state 2
                stateNum = 2;

            } else if (checkOperand(ch[i])) { // if string is a +,-,*,/
                stateNum = 3;
            }

            if (stateNum == 1) { // if there is a number add to token
                if (str.length() != 0) {
                    Token temp = new Token(str);
                    temp.setToken("number");
                    toks.add(temp);
                    str = "";
                }
            } else if (stateNum == 2) { // if there is mult numbers make the whole thing into 1 string and later add to
                                        // toks
                str += ch[i];

            } else if (stateNum == 3) { //
                str = str + ch[i];
                Token temp = new Token(str);
                temp.setToken(str);
                toks.add(temp);
                str = "";

            }

        }

        if (str.length() <= 1) { // if there is only one number in case of 5, since there is no end of line it
                                 // needs to termiate
            Token temp = new Token(str);
            temp.setToken("number");
            toks.add(temp);
        }

        // manual add end of line at the end of for loop
        
        Token temp = new Token("");
        temp.setToken("EndOfLine");
        toks.add(temp);

        return toks;

    }

    private boolean checkOperand(char str) { // check if string is a operand
        if (str == '+' || str == '-' || str == '*' || str == '/') {
            return true;
        } else {
            return false;
        }
    }
}

/*
 * THIS VERSION IS SO MESSY MAKES ME LIFE RETHINK MY LIFE CHOICES
 * // state 1= first state, which accepts input
 * // state 2= if its positive or negative sign
 * //state 3= number 0-9
 * //state 4= number from state 3 to decimal point (1.)
 * //state 5= number from state 3 to a space after
 * // state 6= decimal from state 1.
 * 
 * 
 * for(int i=0;i<storeChar.length;i++){
 * 
 * if(storeChar[0]==' '){
 * stateNum=1; // if input is space
 * }
 * else if(storeChar[i]==' '&&storeChar[i+1]==' '){
 * //endofline
 * stateNum=7;
 * 
 * }
 * else if(StateMachineNumber(storeChar[i])){ // if the input is a number
 * 
 * 
 * stateNum=3;
 * try{
 * if(StateMachineOperand(storeChar[i+2])){
 * //checks if after number input is a operand.(i+2 bc to skip over the space)
 * stateNum=1;
 * }
 * else if(StateMachineNumber(storeChar[i+1])){
 * //if next input is also a number
 * stateNum=3;
 * }
 * else if(storeChar[i+1]=='.'){
 * //if there is a deciamal as next input after number
 * stateNum=4;
 * 
 * }
 * else if(storeChar[i+1]==' '){
 * //if its space after number
 * stateNum=5;
 * }
 * else{
 * stateNum=3;
 * }
 * 
 * }catch (ArrayIndexOutOfBoundsException e) {
 * // System.out.println("Error in first else if");
 * }
 * }// end of first else if line 28
 * 
 * else if(StateMachineOperand(storeChar[i])){// if first input is a + or -
 * 
 * stateNum=2;
 * // after positive or neagtive it either can be a number or decimal next
 * try{
 * if(storeChar[i+1]=='.'){
 * stateNum=6;
 * }
 * else if(StateMachineNumber(storeChar[i+1])){
 * stateNum=3;
 * }
 * 
 * }catch (ArrayIndexOutOfBoundsException e) {
 * //System.out.println("Error in second else if");
 * }
 * 
 * }//end of second else if
 * 
 * else if(storeChar[i+1]=='.'){//if next decimal
 * stateNum=6;
 * try{
 * if(StateMachineNumber(storeChar[i+2])){
 * stateNum=4;
 * }
 * }catch (ArrayIndexOutOfBoundsException e) {
 * //System.out.println("Error in third else if");
 * }
 * 
 * } //end of else if
 * 
 * if(stateNum==1){
 * if(!str.isBlank()){
 * Token tkk= new Token(str);
 * tkk.setToken("EndOfLine");
 * toks.add(tkk);
 * 
 * }
 * 
 * str="";
 * //System.out.print("From lex1 str =" +str); //manual test what is being
 * stored
 * 
 * }
 * else if(stateNum==2 || stateNum==3 ||stateNum==6){
 * str=str+storeChar[i];
 * if(storeChar.length-1==i){
 * Token tkk = new Token(str);
 * tkk.setToken("number");
 * toks.add(tkk);
 * }
 * // System.out.print("From lex2 str =" +str); //manual test what is being
 * stored
 * }
 * else if(stateNum==){
 * 
 * str=""+storeChar[i];
 * 
 * 
 * 
 * System.out.print("From lex3 str =" +str); //manual test what is being stored
 * str="";
 * 
 * 
 * 
 * }
 * else if(stateNum==7){
 * System.out.println("EndOfLine");
 * Token tkk = new Token(str);
 * //tkk.setToken("");
 * 
 * 
 * }
 * 
 * 
 * 
 * 
 * }
 * 
 * // Token tk1= new Token(" ");
 * // tk1.setToken("number");
 * // toks.add(tk1);
 * // for(int i=0;i<toks.size();i++){
 * // System.out.println(toks.get(i).getToken()+" ");
 * // }
 * 
 * return toks;
 * 
 * }
 * 
 * //check if character is a number
 * private boolean StateMachineNumber(char isNum){
 * return Character.isDigit(isNum);
 * }
 * 
 * //checks if character is +,-,*,/
 * private boolean StateMachineOperand(char operand){
 * if(operand=='+'|| operand=='-'|| operand=='*'|| operand=='/'){
 * return true;
 * }
 * else {
 * return false;
 * }
 * }
 */
