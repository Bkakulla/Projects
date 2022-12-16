#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <dirent.h>

int **Matrix;

/**
 * prints the board
 * 
 * @return void
 */
void display_Game_State(int Size)
{
    int i, j;
    printf("\n");
    for (int i = 0; i < Size; i++)
    {
        for (int j = 0; j < Size; j++)
        {
            printf("%3d ", Matrix[i][j]);
        }
        printf("\n");
    }
}

/**
 * Sets the game
 * @param boardSize
 * @return void 
 */

void Initialization(int Board_size)
{
    printf("\nSetting up the game. (0 is the empty spot) \n");
    int i, x, y, j, k;

    int temp;
    int maxTileNumber;

    maxTileNumber = (Board_size * Board_size) - 1; //number of possible tiles in puzzle

    Matrix = (int **)malloc(Board_size * sizeof(int *)); 

    // allocate memory
    for (int r = 0; r < Board_size; r++)
    {
        Matrix[r] = (int *)malloc(Board_size * sizeof(int)); 
    }

    // fills board
    for (int r = 0; r < Board_size; r++)
    {
        for (int c = 0; c < Board_size; c++)
        {
            Matrix[r][c] = maxTileNumber;
            maxTileNumber--;
        }
    }

    //shuffle the board
    for (i = 0; i < Board_size; i++) //creates random row and column indcies and swaps it with the current.
    {
        for (x = 0; x < Board_size; x++)
        {

            int i1 = (rand() % Board_size);
            int x2 = (rand() % Board_size);

            temp = Matrix[i][x];
            Matrix[i][x] = Matrix[i1][x2];
            Matrix[i1][x2] = temp;
        }
    }
    display_Game_State(Board_size);
}

/**
 * Checks if the given tile number is a valid move or not. 
 * This is done by checking the tiles around the empty tile and comparing it to the given tile number
 * @param checkTile 
 * @return 1 if tile is valid, 0 if tile is invalid
 */
int isValid(int checkTile, int boardSize)
{

    int RowLocation;
    int ColumnLocation;
    int temp = 2;

    for (int a = 0; a < boardSize; a++) // finds the locations of empty tile
    {
        for (int b = 0; b < boardSize; b++)
        {
            if (Matrix[a][b] == 0)
            {
                RowLocation = a;
                ColumnLocation = b;
            }
        }
    }

    while (temp != 0)
    {
        if (Matrix[RowLocation][ColumnLocation - 1] == checkTile)
        { // checks if the tile number is to the left of the empty tile
            return 1;
            temp = 0;
            break;
        }
        else if (Matrix[RowLocation][ColumnLocation + 1] == checkTile)
        { //checks if the tile number is to the right of the empty tile
            return 1;
            temp = 0;
            break;
        }
        else if (Matrix[RowLocation - 1][ColumnLocation] == checkTile)
        { //checks if the tile number is on top of the empty space.
            return 1;
            temp = 0;
            break;
        }
        else if (Matrix[RowLocation + 1][ColumnLocation] == checkTile)
        { //checks if the tile numberis below the empty space
            return 1;

            temp = 0;
            break;
        }
        else
        { // if the tile number given is not in any of the positions above that means it is invalid and can't be moved
            return 0;
            temp = 0;
            break;
        }
    }
}

/**
 * Given that tile number is valid, this function moves the tile to the empty spot
 * 
 * @param Tile_number 
 * @return void
 */
void moveTile(int Tile_number, int boardSize)
{

    int xRow;
    int yColumn;

    int MaxRow = 0;
    int MaxColumn = 3;

    int Row_of_empty;
    int Column_of_empty;

    for (int i = 0; i < boardSize; i++)
    { // find the location of the given tile number
        for (int j = 0; j < boardSize; j++)
        {
            if (Matrix[i][j] == Tile_number)
            {
                xRow = i;
                yColumn = j;
            }
        }
    }
    // printf("Row: %d column: %d\n",xRow,yColumn);

    for (int a = 0; a < boardSize; a++)
    { // finds the locations of empty tile
        for (int b = 0; b < boardSize; b++)
        {
            if (Matrix[a][b] == 0)
            {
                Row_of_empty = a;
                Column_of_empty = b;
            }
        }
    }

    if (isValid(Tile_number, boardSize) == 1)
    {
        Matrix[xRow][yColumn] = 0;                           // sets the given tile number to empty
        Matrix[Row_of_empty][Column_of_empty] = Tile_number; //sets the empty tile to the given unmber
        display_Game_State(boardSize);
    }
    else
    {
        printf("\nThe tile is invalid\n");
        display_Game_State(boardSize);
    }
}

int GameWon(int SizeOfBoard)
{
    //creates a array that is the winning board and checks it with the matrix 
    int maxTiles = (SizeOfBoard * SizeOfBoard) - 1;
    int counter = 0;
    for (int i = 0; i < SizeOfBoard; i++)
    {
        for (int j = 0; j < SizeOfBoard; j++)
        {
            if (Matrix[i][j] == counter)
            {
                counter++;
            }
            else
            {
                return 0;
                break;
            }
        }
        if (counter == maxTiles)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
/**
 * @this function clears the matrix. free up memory 
 * 
 * @param Size 
 */
void clear(int Size)
{
    for (int i = 0; i < Size; i++)
    {
        free(Matrix[i]);
    }
    free(Matrix);
}
/**
 * Ends the game
 * @return void 
 */
void Teardown(int boardSize)
{
    clear(boardSize);
    printf("Ending the game\n");
}
/**
 * this function saves the current game state into a given file with name
 * 
 * @param NameOfFile 
 */
void SaveGame(char NameOfFile[])
{
    //system call
    int fd = open(NameOfFile, O_CREAT | O_WRONLY);

    if (fd != -1)
    {
        write(fd, &Matrix, sizeof(Matrix));
        printf("File named %s was made\n", NameOfFile);
        close(fd);
    }
    else
    {
        printf("File was not created\n");
    }
}
/**
 * @This function prints the directory so the user can pick a file to open and load
 * 
 * @param size 
 */
void LoadGame(int size)
{
    char UserFileNameLoad[99];

    DIR *d;
    struct dirent *dir;
    d = opendir(".");

    if (d)
    {
        while ((dir = readdir(d)) != NULL)
        {
            printf("%s\n", dir->d_name); //like linked list
        }
        closedir(d);
    }
    printf("Enter file name to load: ");
    scanf("%s", UserFileNameLoad);

    FILE *fp;
    fp = fopen(UserFileNameLoad, "r");
    if (fp == NULL)
    {
        printf("could not read file");
    }
    for (int i = 0; i < size; ++i)
    {
        for (int k = 0; k < size; ++k)
        {
            fscanf(fp, "%d", Matrix[i] + k);
        }
    }
    fclose(fp);
}

/**
 * Ask the user for option of printing the board, moving, or quiting the game.
 * Ask the user for new options of saving board, new board, or load old game
 * 
 * @return void
 */
int main()
{

    char Menu1_Char = 'o';
    int tileNumber;
    int UserBoardSize = 4;
    int counter = 0;
    char UserFileNameSave[100];
    Initialization(UserBoardSize);

    while (Menu1_Char != 'q')
    {

        if (GameWon(UserBoardSize) == 1)
        {
            printf("You won the game, Congrats\n");
            printf("New Game, Enter a new size: ");
            scanf("%d", &UserBoardSize);
            Initialization(UserBoardSize);
        }

        printf("\n");
        printf("Menu 1: [p]rint, [m]ove, [q]uit?\n");
        printf("Menu 2: [n]ew, [s]ave, [l]oad?\n");
        printf("Enter menu option: ");
        scanf(" %c", &Menu1_Char);

        if (Menu1_Char == 'p')
        {
            display_Game_State(UserBoardSize);
        }
        else if (Menu1_Char == 'm')
        {
            printf("Which tile to move: ");
            scanf("%d", &tileNumber);
            moveTile(tileNumber, UserBoardSize);
        }
        else if (Menu1_Char == 'q')
        {
            Teardown(UserBoardSize);
            Menu1_Char = 'q';
            break;
        }
        else if (Menu1_Char == 'n')
        {
            printf("Enter board size: ");
            scanf("%d", &UserBoardSize);
            if ((UserBoardSize >= 2) && (UserBoardSize <= 10))
            {
                Initialization(UserBoardSize);
            }
            else
            {
                printf("Invalid board size\n");
            }
        }
        else if (Menu1_Char == 's')
        {
            printf("\nEnter file name to be saved: \n");
            scanf("%s", UserFileNameSave);
            SaveGame(UserFileNameSave);
        }
        else if (Menu1_Char == 'l')
        {
            LoadGame(UserBoardSize);
        }
    }
}
