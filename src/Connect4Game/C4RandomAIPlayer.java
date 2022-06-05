package Connect4Game;

import java.util.Random;
import java.util.*;
public class C4RandomAIPlayer extends ConnectPlayer{


    C4RandomAIPlayer( char token, String playerName ){
        super( token, playerName );
    }

    @Override 
    public void playerTurn( Connect4Grid2DArray board, Scanner input ){
        if( board != null ){
            if( input != null ){
                Random RandomGenerator = new Random();
                int columnNumber = RandomGenerator.nextInt( 7 );
                if( !board.isValidColumn( columnNumber ) ){
                    System.out.println( "Invalid Column. Play again." ); 
                    this.playerTurn( board, input);
                }
                board.dropPiece( this , columnNumber );
            }
        }
    }
}
