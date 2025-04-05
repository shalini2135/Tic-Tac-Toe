package tictactoe;
import java.util.*;
public class GameBoard {
 char [][] board;
 int boardSize;
 Queue<Player> nxtTurn;
 Scanner sc;
  public GameBoard(int boardSize,Player[] player) {
	 this.boardSize=boardSize;
	 this.board=new char[(2*boardSize)-1][(2*boardSize)-1];
	initializeBoard(board); 
	nxtTurn=new LinkedList<>();
	nxtTurn.offer(player[0]);
	nxtTurn.offer(player[1]);
    sc=new Scanner(System.in);	
  }
  public void initializeBoard(char[][] board) {
	  int n=board.length;
	  int m=board[0].length;
	  for(int i=0;i<n;i++) {
		  for(int j=0;j<m;j++) {
	if((i%2==0) && (j%2!=0))
		board[i][j]='|';
	if((i%2!=0)&&(j%2==0))
		board[i][j]='-';
	if((i%2!=0) && (j%2!=0))
		board[i][j]='+';
		  }
	  }
  }
  public void printBoard() {
	  for(char []row:board) {
		  for(char col:row) {
			  System.out.print(col);
		  }
		  System.out.println();
	  }
  }
  public void startGame() {
	  int cnt=0;
	  while(true) {
		  cnt++;
		  if(cnt==((boardSize*boardSize)+1)) {
			  System.out.println("Match Draw");
			  break;
		  }
		  Player p=nxtTurn.poll();
		  int pos=getUserInput(p);
		  int row=2*((pos%boardSize==0)?(pos/boardSize-1):(pos/boardSize));
		  int col=2*((pos%boardSize==0)?boardSize-1:(pos%boardSize)-1);
		  board[row][col]=p.getPlayerSymbol();
		  System.out.println("Board After the move");
		  if(cnt>=boardSize-1 && checkEndGame(p,row,col)) {
			  break;
		  }
		  nxtTurn.offer(p);
	  }
  }
public int getUserInput(Player p) {
	printBoard();
	System.out.println(p.getPlayerName()+" Please Enter a num between 1 to "+boardSize*boardSize);
int val=sc.nextInt();
while(!validate(val)) {
printBoard();
System.out.println("Wrong position");
val=sc.nextInt();
}
return val;
}
private boolean validate(int val) {
	if(val<1 || val>(boardSize*boardSize)) return false;
int row=2*((val%boardSize==0)?(val/boardSize)-1:(val/boardSize));
int col=2*((val%boardSize==0)?boardSize-1:(val%boardSize)-1);
if((int)board[row][col]!=0) return false;
return true;
}
private boolean checkEndGame(Player p, int row, int col) {
    String winstr = "";
    for (int i = 0; i < boardSize; i++)
        winstr += p.getPlayerSymbol();

    // Build row, column, diagonal, and reverse-diagonal strings
    StringBuilder rowstr = new StringBuilder();
    StringBuilder colstr = new StringBuilder();
    StringBuilder diastr = new StringBuilder();
    StringBuilder revdiastr = new StringBuilder();

    for (int i = 0; i < boardSize; i++) {
        rowstr.append(board[row][2 * i]);           // Valid cols: 0, 2, 4,...
        colstr.append(board[2 * i][col]);           // Valid rows: 0, 2, 4,...
        if (row == col) {
            diastr.append(board[2 * i][2 * i]);     // Top-left to bottom-right
        }
        if ((row + col) == (2 * boardSize - 2)) {
            revdiastr.append(board[2 * i][2 * (boardSize - i - 1)]); // Top-right to bottom-left
        }
    }

    // Check win condition
    if (winstr.equals(rowstr.toString()) || winstr.equals(colstr.toString()) ||
        winstr.equals(diastr.toString()) || winstr.equals(revdiastr.toString())) {
        printBoard();
        System.out.println(p.getPlayerName() + " has won the Game");
        return true;
    }

    return false;
}
	
}

