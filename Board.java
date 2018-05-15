import java.util.*;

public class Board{
	public ArrayList<ArrayList<Character>> board;
	public int size;
	public char winner = ' ';

	/*
		If no size is input to the constructor, uses default size of 3
	*/
	public Board(){
		this.size = 3;
		this.board = initBoard(this.size);
	}

	/*
		Overloaded constructor including the size
	*/
	public Board(int size){
		this.size = size;
		
		this.board = initBoard(this.size);
	}
	/* 
		Initializes the board depending on the input size
		Returns the board with all empty Characters in elligible spaces
	*/
	private ArrayList<ArrayList<Character>> initBoard(int size){
		ArrayList<ArrayList<Character>> tempBoard = new ArrayList<ArrayList<Character>>();
		for(int i = 0; i < size; i++){
			ArrayList<Character> temp = new ArrayList<Character>();
			for(int j = 0; j < size; j++){
				temp.add(' ');
			}
			tempBoard.add(temp);
		}

		return tempBoard;
	}
	/*
		Determines if the game is over- returns true if it is,
		false otherwise
	*/
	public boolean gameOver(){
		if(this.winner == 'X'){
			return true;
		}
		else if(this.winner == 'O'){
			return true;
		}
		else if(this.isFull()){
			return true;
		}
		else{
			return false;
		}
	}
	/*
		Returns whether the board is full or not
	*/
	public boolean isFull(){
		for(int i = 0; i < this.size; i++){
			for(int j = 0; j < this.size; j++){
				if(this.board.get(i).get(j) == ' '){
					return false;
				}
			}
		}
		return true;
	}
	/*
		Returns true if the letter was successfully placed,
		and false if the move is invalid

		Note: the position will be an integer representing the
		size*row + col
	*/
	public boolean placeLetter(int pos, char letter){
		int row = pos / this.size;
		int col = pos % this.size;

		if(this.board.get(row).get(col) == ' '){
			this.board.get(row).set(col, letter);
			checkWin(letter);
			return true;
		}
		else{
			return false;
		}
	}
	public char getWinner(){
		return this.winner;
	}
	public int getSize(){
		return this.size;
	}
	public ArrayList<ArrayList<Character>> getBoard(){
		return this.board;
	}
	/*
		Checks to see if there's been a win
		If so, set this.winner to the winning letter
	*/
	public void checkWin(char letter){
		int[] cCount = new int[this.size];
		int[] diagCount = new int[2];

		for(int i = 0; i < this.size; i++){
			int count = 0;
			for(int j = 0; j < this.size; j++){
				//Check the surrounding spots
				if(this.board.get(i).get(j) == letter){
					count++;
					cCount[j] += 1;
					if(i==j){
						diagCount[0] +=1;
					}
					if(i + j == (this.size-1)){
						diagCount[1]++;
					}
				}
			}
			//If three in one row were the same letter...
			if(count == this.size){
				this.winner = letter;
				break;
			}
		}
		for(int i = 0; i < this.size; i++){
			if(i < 2){
				if(diagCount[i] == this.size){
					this.winner = letter;
					break;
				}
			}
			if(cCount[i] == this.size){
				this.winner = letter;
				break;
			}
		}
	}
}