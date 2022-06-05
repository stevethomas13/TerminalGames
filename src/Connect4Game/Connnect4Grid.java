package Connect4Game;

public interface Connnect4Grid {

    public static final int columnLength = 7;
    public static final int rowLength = 6;
    public void emptyGrid();
    public String toString();
    public boolean isValidColumn(int column);
    public boolean isColumnFull(int column);
    public void dropPiece(ConnectPlayer player, int column);
    public boolean didLastPieceConnect4();
    public boolean isGridFull();   
    
}
