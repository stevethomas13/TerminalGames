package Connect4Game;

import java.util.Scanner;

public abstract class ConnectPlayer {
    private char token;
    private String playerName;

    ConnectPlayer( char token, String playerName ){
        this.token = token;
        this.playerName = playerName;
    }

    public char getToken(){
        return token;
    }

    public void setPlayerName( String playerName ){
        this.playerName = playerName;
    }

    public String getPlayerName(){
        return playerName;
    }

    public abstract void playerTurn( Connect4Grid2DArray board, Scanner input );
}
