package Connect4Game;

public class Connect4Grid2DArray implements Connnect4Grid {

    public static final int rowLength = 6;
    public static final int columnLength = 7;
    char[][] board = new char[ rowLength ][ columnLength ];

    public void emptyGrid(){
        if ( board != null ){
            for( int row = 0; row < board.length; row++ ){
                for( int column = 0; column < board[0].length ; column++ ){
                    board[ row ][ column ] = ' ';
                }
            }
        }
    }

    public String toString(){
        if ( board != null ){
            String output = "";
            for( int row = 0; row < rowLength ; row++ ){
                int count = 0;
                for( int column = 0; column < columnLength; column++ ){
                    output = output + "|";
                    output = output + board[ row ][ column ];
                    count = count + 2;
                }
                output = output + "|"; 
                output = output + "\n";
                for( int column = 0; column < count; column++ ){
                    output = output + "-";
                }
                output = output + "\n";
            }
            return output;
        }
        return "";
    }

    public boolean isValidColumn( int column ){
        if( column >= 0 && column < columnLength ){
            if( !isColumnFull( column ) ){
                return true;
            }
        }
        return false;
    }

    public boolean isColumnFull(int column){
        if( board != null ){
            if( column >= 0 && column < columnLength ){
                for( int row = 0; row < rowLength; row++ ){
                    if( board[ row ][ column ] == ' ' ){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void dropPiece( ConnectPlayer player, int column ){
        if( player != null ){
            if( board != null ){
                if( isValidColumn( column ) ){
                    int row = rowLength - 1;
                    boolean pieceDropped = false;
                    while( !pieceDropped ){
                        if( board[ row ][ column ] == ' ' ){
                            board[ row ][ column ] = player.getToken();
                            pieceDropped = true;
                        }
                        if( row < 0 ){
                            pieceDropped = true;
                        }
                        row--;
                    }
                    System.out.println( "Piece dropped at position: " + column ); 
                }
            }
        }
    }

    public boolean didLastPieceConnect4(){
        if( board != null ){
            for( int row = 0; row < rowLength - 3; row++ ){
                for( int column = 0; column < columnLength; column++ ){
                    char key = board[ row ][ column ];
                    char row2 = board[ row + 1 ][ column ];
                    char row3 = board[ row + 2 ][ column ];
                    char row4 = board[ row + 3 ][ column ];
                    if( key != ' ' ){
                        if( key == row2 && row2 == row3 && row3 == row4 ){
                            return true;
                        }
                    }
                }
            }
    
            for( int row = 0; row < rowLength; row++ ){
                for( int column = 0; column < columnLength - 3; column++ ){
                    char key = board[ row ][ column ];
                    char col2 = board[ row ][ column + 1 ];
                    char col3 = board[ row ][ column + 2 ];
                    char col4 = board[ row ][ column + 3 ];
                    if( key != ' ' ){
                        if( key == col2 && col2 == col3 && col3 == col4 ){
                            return true;
                        }
                    }
                }
            }
    
            for( int row = 0; row < rowLength - 3; row++ ){
                for( int column = 0; column < columnLength - 3; column++ ){
                    char key = board[ row ][ column ];
                    char loDiag2 = board[ row + 1 ][ column + 1 ];
                    char loDiag3 = board[ row + 2 ][ column + 2 ];
                    char loDiag4 = board[ row + 3 ][ column + 3 ];
                    if( key != ' ' ){
                        if( key == loDiag2 && loDiag2 == loDiag3 && loDiag3 == loDiag4 ){
                            return true;
                        }
                    }
                }
            }
    
            for( int row = rowLength - 1; row >= 3; row-- ){
                for( int column = 0 ; column < columnLength - 3; column++ ){
                    char upDiag1 = board[ row ][ column ];
                    char upDiag2 = board[ row - 1 ][ column + 1 ];
                    char upDiag3 = board[ row - 2 ][ column + 2 ];
                    char upDiag4 = board[ row - 3 ][ column + 3 ];
                    if( upDiag1 != ' ' ){
                        if( upDiag1 == upDiag2 && upDiag2 == upDiag3 && upDiag3 == upDiag4 ){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isGridFull(){
        if( board != null ){
            for( int row = 0; row < rowLength; row++ ){
                for( int column = 0; column < columnLength; column++ ){
                    if( board[ row ][ column ] == ' ' ){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
