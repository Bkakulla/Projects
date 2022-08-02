

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

public class Panel extends JPanel {
    final int maxColumn = 15;
    final int maxRow= 10;
    final int nodeSize= 70;
    final int screenWidth= nodeSize*maxColumn;
    final int screenHeight= nodeSize*maxRow;

    Node [][] node= new Node[maxColumn][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openlist= new ArrayList<>();
    ArrayList<Node> checkedlist= new ArrayList<>();

    boolean goalReached;

    public Panel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(maxRow,maxColumn));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);

        // insert node on to screen
        int column=0;
        int row=0;

        while(column<maxColumn && row <maxRow){
            node[column][row]= new Node(column,row);
            this.add(node[column][row]);

            column++;

            if(column== maxColumn){
                column=0;
                row++;
            }
        }
        //set start and end tile
        setStartNode(3,5);
        setGoalNode(11, 3);

        //set solid blocks
        setSolidNode(10, 2);
        setSolidNode(10, 3);
        setSolidNode(10,4 );
        setSolidNode(10,5 );
        setSolidNode(10, 6);
        setSolidNode(10, 7);
        setSolidNode(6,2 );
        setSolidNode(8,2 );
        setSolidNode(7,2 );
        setSolidNode(9,2 );
        setSolidNode(11,7 );
        setSolidNode(12,7 );
        setSolidNode(6, 1);

        //set the cost of nodes
        setCostOnNodes();


    }

    private void setStartNode(int column, int row){
        node[column][row].setAsStart();
        startNode= node [column][row];
        currentNode= startNode;

    }

    private void setGoalNode(int column, int row){
        node[column][row].setAsGoal();
        goalNode= node [column][row];
        

    }

    private void setSolidNode(int column,int row){
        node[column][row].setAsSolid();
    }
    private void setCostOnNodes(){
        int col=0;
        int row=0;

        while(col<maxColumn && row<maxRow){
            getCost(node[col][row]);
            col++;
            if(col==maxColumn){
                col=0;
                row++;
            }
        }
    }

    private void getCost(Node node){

        // getg g cost of node
        int xDist= Math.abs(node.column-startNode.column);
        int yDist= Math.abs(node.row- startNode.row);
        node.gCost= xDist+yDist;

        // h cost
        xDist= Math.abs(node.column-goalNode.column);
        yDist= Math.abs(node.row- goalNode.row);
        node.hCost= xDist+yDist;

        // f cost 
        node.fCost= node.gCost+node.hCost;

        if(node != startNode && node != goalNode){
            node.setText("<html>F:" + node.fCost + "<br>G:"+ node.gCost + "<html>");
        }


    }

     public void search(){

        if(goalReached==false){
            int col=currentNode.column;
            int row=currentNode.row;

            currentNode.setAsChecked();
            checkedlist.add(currentNode);
            openlist.remove(currentNode);

            // check nodes around left, right, top , and bottom 
            if((row-1>=0)){
            openNode(node[col][row-1]);
            }

            if(col-1>=0){
            openNode(node[col-1][row]);
            }

            if(row+1<maxRow){
            openNode(node[col][row+1]);
            }
            if(col+1<maxColumn){
            openNode(node[col+1][row]);
            }
            int bestNodeIndex=0;
            int bestNodefCost=999;

            for(int i=0;i<openlist.size();i++){
                //check the best f cost for each node

                if(openlist.get(i).fCost<bestNodefCost){
                    bestNodeIndex=i;
                    bestNodefCost=openlist.get(i).fCost;
                    
                }
                //  if f cost are equal, check the g cost
                else if(openlist.get(i).fCost==bestNodefCost){
                    if(openlist.get(i).gCost < openlist.get(bestNodeIndex).gCost){
                        bestNodeIndex=i;
                    }
                }
            }
            // after loop, it will return the best next node
            currentNode= openlist.get(bestNodeIndex);

            if(currentNode==goalNode){
                goalReached=true;
                trackThePath();
            }
        }
            

     }


     private void openNode(Node node){
        if(node.open ==false && node.checked==false && node.solid==false){
            node.setAsOpen();
            node.parent= currentNode;
            openlist.add(node);
        }
     }

    private void trackThePath(){

        Node current=goalNode;
        while(current!=startNode){
            current= current.parent;
            if(current != startNode){
                current.setAsPath();
            }
        }
    }


}
