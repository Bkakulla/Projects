import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Node extends JButton implements ActionListener{

    Node parent;
    int column;
    int row;
    int gCost;
    int hCost;
    int fCost;
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int column, int row){
        this.column= column;
        this.row= row;

        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
    }

    public void setAsStart(){
        setBackground(Color.blue);
        setForeground(Color.red);
        setText("Start");
        start= true;
    }

    public void setAsGoal(){
        setBackground(Color.yellow);
        setForeground(Color.red);
        setText("Goal");
        goal= true;
    }
    public void setAsSolid(){
        setBackground(Color.black);
        setForeground(Color.black);
        solid=true;
    }

    public void setAsOpen(){
        open =true;

    }

    public void setAsChecked(){
        if(start== false && goal== false){
            setBackground(Color.orange);
            setForeground(Color.black);
        
        }
        checked=true;
    }
    public void setAsPath(){
        setBackground(Color.green);
        setForeground(Color.black);
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        setBackground(Color.red);
       
    }

    
}
