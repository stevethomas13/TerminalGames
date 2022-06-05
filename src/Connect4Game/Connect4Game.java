package Connect4Game;/* SELF ASSESSMENT

Connect4Game class (35 marks): 35
My class creates references to the Connect 4 Grid and two Connect 4 Players. 
It asks the user whether he/she would like to play/quit inside a loop. 
If the user decides to play then: 1. Connect4Grid2DArray is created using the Connect4Grid interface, 
2. the two players are initialised - must specify the type to be ConnectPlayer, and 
3. the game starts. In the game, I ask the user where he/she would like to drop the piece. 
I perform checks by calling methods in the Connect4Grid interface. Finally a check is performed to determine a win. 
Comment: 1. and 2. have been provided for. The conditions for point 3 are being performed in the playerTurn method 
    `   in the C4HumanPlayer class and C4RandomAIPlayer class.  

Connect4Grid interface (10 marks): 10
I define all 7 methods within this interface.
Comment: 10, all 7 methods have been defined

Connect4Grid2DArray class (25 marks): 25
My class implements the Connect4Grid interface. It creates a grid using a 2D array Implementation of the method to check whether the column to drop the piece is valid. It provides as implementation of the method to check whether the column to drop the piece is full. It provides as implementation of the method to drop the piece.  It provides as implementation of the method to check whether there is a win.
Comment: 25, all functionality has been provided for

ConnectPlayer abstract class (10 marks): 10
My class provides at lest one non-abstract method and at least one abstract method. 
Comment: non-abstract methods: getToken() and getPlayerName()
         abstract methods: playerTurn( Connect4Grid2DArray board, Scanner input )

C4HumanPlayer class (10 marks): 10
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides the Human player functionality.
Comment: all those have been provided for

C4RandomAIPlayer class (10 marks): 10
My class extends the ConnectPlayer claas and overrides the abstract method(s). It provides AI player functionality. 
Comment: all those have been provided for

Total Marks out of 100: 100
*/
import java.util.Scanner;
public class Connect4Game {
    
    public ConnectPlayer player1;
    public ConnectPlayer player2;
    public Connect4Grid2DArray board;

    Connect4Game( ConnectPlayer player1, ConnectPlayer player2, Connect4Grid2DArray board ){
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
    }

    public void playGame( Scanner input ){
        boolean continueGame = true;
        ConnectPlayer currentPlayer = player2;  
        while( continueGame ){
            if( currentPlayer == player1 ){
                currentPlayer = player2;
            }
            else if( currentPlayer == player2 ){
                currentPlayer = player1;
            }
            currentPlayer.playerTurn( board, input );
            System.out.println( board.toString() ); ;
            if( board.didLastPieceConnect4() ) {
                System.out.println( "Player " + currentPlayer.getPlayerName() + " has won the game." );
                continueGame = false;
            }
            if( board.isGridFull() ){
                System.out.println( "No other valid moves left." );
                System.out.println( "Draw!" );
                continueGame = false; 
            }
        }
    }

    public static void main( String[] args ){
        Scanner input = new Scanner( System.in );
        Connect4Grid2DArray board = new Connect4Grid2DArray();
        board.emptyGrid();
        System.out.println( "Enter name of player 1." );
        String inputName = input.nextLine();
        ConnectPlayer player1 = new C4HumanPlayer( 'r', inputName );
        ConnectPlayer player2 = new C4HumanPlayer( 'y', "" );
        boolean continueLoop = true;
        while( continueLoop ){
            System.out.println( "Enter if you want to play with another human or ai [ Valid inputs: h/ai ]: ");
            String inputText = input.nextLine();
            if( inputText.equalsIgnoreCase( "h" ) ) {
                System.out.println( "Enter name of player 2." );
                inputName = input.nextLine();
                player2.setPlayerName( inputName );
                continueLoop = false;
            }
            else if( inputText.equalsIgnoreCase( "ai" ) ){
                player2 = new C4RandomAIPlayer( 'y', "AI");
                continueLoop = false;
            }
            else{
                System.out.println( "Incorrect input." );
            }
        }
        Connect4Game newGame = new Connect4Game( player1, player2, board );
        System.out.println( board.toString() );
        newGame.playGame( input );
        input.close();
    }
}

