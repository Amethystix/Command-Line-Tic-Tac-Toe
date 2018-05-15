import java.util.*;

public class Driver{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input the size of your Tic Tac Toe Board.");

		int size = Integer.parseInt(sc.nextLine());
		Board game = new Board(size);

		System.out.println("Let's play!");
		play(game, sc);

	}
	public static void play(Board game, Scanner sc){
		int count = 0;
		System.out.println(printBoard(game));
		while(!game.gameOver()){
			//Player one goes
			if(count % 2 == 0){
				System.out.println("Where would you like to place your letter?");
				//Placeholder code
				if(game.placeLetter(Integer.parseInt(sc.nextLine()), 'X')){
					System.out.println("Placed letter");
					System.out.println(printBoard(game));
					count++;
				}
				else{
					System.out.println("Invalid move.  Please try again.");
				}	
			}
			//Player 2 goes
			else{
				while(!game.placeLetter(((int) (Math.random() * (game.getSize()*game.getSize()))), 'O')){
					//Do nothing- loop until successfully placing letter
				}
				System.out.println("Opponent placed letter.");
				System.out.println(printBoard(game));
				count++;
			}
		}
		if(game.getWinner() == 'X'){
			System.out.println("Congrats!  You won!");
		}
		else if(game.getWinner() == 'O'){
			System.out.println("Better luck next time!");
		}
		else{
			System.out.println("It's a tie!");
		}
	}
	public static String printBoard(Board game){
		ArrayList<ArrayList<Character>> b = game.getBoard();
		String print = "";
		for(int i = 0; i < b.size(); i++){
			print+="|";
			for(int j = 0; j < b.size(); j++){
				print+=b.get(i).get(j) + "|";
			}
			print+="\n";
		}
		return print;
	}
}