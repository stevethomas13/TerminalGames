package Connect4Game;

import java.util.Scanner;
public class C4HumanPlayer extends ConnectPlayer{

    C4HumanPlayer( char token, String playerName ){
        super( token, playerName );
    }

    @Override 
    public void playerTurn( Connect4Grid2DArray board, Scanner input ){
        if( board != null ){
            if( input != null ){
                System.out.println( "Enter column number that you want to input" );
                int columnNumber = input.nextInt();
                if( !board.isValidColumn( columnNumber ) ){
                    System.out.println( "Invalid Column. Play again." );
                    this.playerTurn( board, input);;
                }
                board.dropPiece( this, columnNumber );
            }
        }
    }
}
