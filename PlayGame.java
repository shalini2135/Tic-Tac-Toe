package tictactoe;

public class PlayGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Player p1=new Player();
p1.setPlayerId(1);
p1.setPlayerName("Shalini");
p1.setPlayerSymbol('X');

Player p2=new Player();
p2.setPlayerId(2);
p2.setPlayerName("Corolena");
p2.setPlayerSymbol('O');
Player[] players=new Player[] {p1,p2};
GameBoard gb=new GameBoard(3,players);
gb.startGame();
	}

}
