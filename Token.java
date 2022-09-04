public class Token {
   
    private String value ="";
    

    enum token{
        EndOfLine("EndOfLine"), plus("plus"),minus("minus"),times("times"),divide("divide"),number("number");
        
        private String value2="";
        
        private token(String word){ 
            value2=word;
        }
        public String getValue(){
            return value2;
        }
    } //close of enum

    private token tokenForTest;

   public Token(String temp){ //Token contstructor that takes in string 
        value= temp;
    }
    public token getToken(){
        return tokenForTest;
    }
    

    public String getString(){
        return value;
    }

    public void setToken(String charac){
        try{
            int num=typeOfToken(charac);

            switch(num){
                case 1:
                    tokenForTest= token.number;
                    break;
                case 2: 
                    tokenForTest=token.plus;
                    break;
                case 3: 
                    tokenForTest= token.minus;
                    break;
                case 4:
                    tokenForTest= token.times;
                    break;
                case 5:
                    tokenForTest= token.divide;
                    break;
                case 6: 
                    tokenForTest= token.EndOfLine;
                    break;
                

            }
        }catch(Exception e){
            System.out.print("Error");
            System.out.print(e);
        }

    }

    public int typeOfToken(String str){
        int temp=0;

        
        
        if(str.equals("+")){
            temp=2;
        }
        
        else if(str.equals("-")){
            temp=3;
        }
        else if(str.equals("*")){
            temp=4;
        }
        else if(str.equals("/")){
            temp=5;
        }
        
        else if(str.equals("number")){
            temp=1;
        }
        else if(str.equals("EndOfLine")){
            temp=6;
        }
        
    
        return temp;
        
    }

    public String toString(){
        
        return tokenForTest.getValue()+"and the value is:  "+value;
        
    }
    
    
}
