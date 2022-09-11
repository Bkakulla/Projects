#include <stdio.h>

int board[4][4]= {{15,14,13,12},{11,10,9,8},{7,6,5,4},{3,2,1}};
int boardSize= 4;


/**
 * prints the board
 * 
 * @return void
 */

int display_Game_State(){

  printf("0 is the empty spot.\n");
    for(int x= 0;x<boardSize;x++){
        for(int y=0;y<boardSize;y++){
            printf("%3d ", board[x][y]);
        }
        printf("\n");
    }
}
/**
 * begins the game 
 */
int Initialization(){
    printf("Setting up the game\n");

}
/**
 * Given the the tile number to be moved, this functions moves the position of the tile to the empty tile position
 * @param MoveTileNum 
 * @param TileRow 
 * @param TileColumn 
 * @param EmptyTileRow 
 * @param EmptyTileColumn 
 * @return void  
 */
int MoveTile(int MoveTileNum, int TileRow,int TileColumn,int EmptyTileRow, int EmptyTileColumn){

    board[TileRow][TileColumn]=0;  // sets the given tile number to empty 
    board[EmptyTileRow][EmptyTileColumn]=MoveTileNum; //sets the empty tile to the given unmber
    display_Game_State();
    
}
/**
 * Checks if the given tile is movable or not
 * 
 * @param tileNum 
 * @return void
 */
int IsValid(int tileNum){
    int xRow;
    int yColumn;

    int MaxRow=0;
    int MaxColumn=3;

    int Row_of_empty;
    int Column_of_empty;


    int temp=2;

    for (int i=0 ; i<boardSize;i++){ // find the location of the given tile number 
        for(int j=0; j<boardSize;j++){
            if(board[i][j]==tileNum){ 
                xRow=i;
                yColumn=j;
            }
        }
    }
   // printf("Row: %d column: %d\n",xRow,yColumn);
    
    for (int a=0 ; a<boardSize;a++){ // finds the locations of empty tile
        for(int b=0; b<boardSize;b++){
            if(board[a][b]==0){
                Row_of_empty=a;
                Column_of_empty=b;
            }            
        }
    }
   // printf("The place of 0. xRow: %d , yCol: %d\n",Row_of_empty,Column_of_empty);
    while(temp!=0){
        if(board[Row_of_empty][Column_of_empty-1]==tileNum){  // checks if the tile number is to the left of the empty tile
            printf("The tile is valid\n");
            MoveTile(tileNum,xRow,yColumn,Row_of_empty,Column_of_empty);
            temp=0;
            break;
        }
        else if(board[Row_of_empty][Column_of_empty+1]==tileNum){ //checks if the tile number is to the right of the empty tile
            printf("The tile is valid\n");
           MoveTile(tileNum,xRow,yColumn,Row_of_empty,Column_of_empty);
            temp=0;
            break;
        }
        else if(board[Row_of_empty-1][Column_of_empty]==tileNum){ //checks if the tile number is on top of the empty space.
            printf("The tile is valid\n");
            MoveTile(tileNum,xRow,yColumn,Row_of_empty,Column_of_empty);
            temp=0;
            break;
        }
        else if(board[Row_of_empty+1][Column_of_empty]==tileNum){ //checks if the tile numberis below the empty space
            printf("The tile is valid\n");
            MoveTile(tileNum,xRow,yColumn,Row_of_empty,Column_of_empty);
            temp=0;
            break;
        }
        else{ // if the tile number given is not in any of the positions above that means it is invalid anf can't be moved
            printf("Not a valid tile\n ");
            temp=0;
            break;
        }
    }
}

/**
 * Ends the game
 * @return void 
 */
int Teardown(){
    printf("Ending the game\n");
}

/**
 * Ask the user for option of printing the board, moving, or quiting the game.
 * 
 * @return void 
 */
int main(){

    char UserChar='o';
    int tileNumber;

Initialization();

while(UserChar!='q'){
    printf("Menu: [p]rint, [m]ove, [q]uit? ");
    scanf(" %c", &UserChar);
        if(UserChar=='p'){
            display_Game_State();
            }
        else if(UserChar=='m'){
            printf("Which tile to move: ");
            scanf("%d",&tileNumber);
            IsValid(tileNumber);
            }
        else if(UserChar=='q'){
            Teardown();
            UserChar='q';
            break;
            }
        else{
            ("Invalid menu input. \n");
        }
    }
}


