import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Shank{
    public static void main(String [] args)  {

        
        
        
            if(args.length>1){ //exit if more than 1 args
                System.out.println("There is more than 1 argument");
                System.exit(0);
            }
            
            else{
                Path pathfile= Paths.get(args[0]);
                try {

                    List<String> lines = Files.readAllLines(pathfile); //readAllLines

                    List<Token> hold= new ArrayList<Token>();
                    Lexer lexer = new Lexer();

                    //System.out.println(lines);

                    for(String c: lines){
                        try{
                            hold= lexer.lex(c);
                            //System.out.println("\nIn shank class");
                            for (Token temp: hold) {
                                System.out.println(temp.getToken()+"("+temp.getString()+")");
                                
                            }
                                
                        }catch (Exception e) {
                    
                            System.out.println("Lexer Error ");
                            e.printStackTrace();    
                        }
                        
                    }
                    } catch (IOException e) {
                         System.out.println("File not found");
                        }
            }

        
    }
}