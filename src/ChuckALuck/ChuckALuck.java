package ChuckALuck;/*
1. ResolveBet
I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type [Mark out of 7: 7]. 
Comment: self explanatory
My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet [Mark out of 8: 8]. 
Comment: self explanatory
My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5:5 ].
Comment: every time the resolve bet method is called, cash in wallet is comapared to bet amount
My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned [Mark out of 15: 15]..
Comment: this is done in the checkBet method, which is called by ResolveBet
My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet [Mark out of 20: 20].
Comment:self explanatory
My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10: 10 ].
Comment:self explanatory

2. Main
I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15:15 ]
Comment:self explanatory
My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5: 5]
Comment: both conditions are checked for
I ask the user to enter any of the four bet types or quit [Mark out of 5: 5].
Comment: a message similar to that is outputed on the screen 
My program calls resolveBet for each bet type entered [Mark out of 5: 5].
Comment:self explanatory
At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5: 5]
Comment:self explanatory

 Total Mark out of 100 (Add all the previous marks):100
*/

import java.util.Scanner;
public class ChuckALuck {
	public static final String triple = "triple";
	public static final String fields = "fields";
	public static final String high = "high";
	public static final String low = "low";
	public static final int fieldsLowerLimit = 8;
	public static final int fieldUpperLimit = 12;
	public static final int highLimit = 10;
	public static final int lowLimit = 11;
	static Scanner input = new Scanner( System.in );
	
	public static void main( String[] args ) {
		System.out.print( "Enter amount of money in wallet: " );
		double initialMoney = input.nextDouble();
		Wallet newWallet = new Wallet();
		newWallet.put( initialMoney );
		boolean continueGame = true;
		String betType = "";
		do {
			if( newWallet.check() > 0 ) {
				System.out.print( "Enter bet type (possible values: triple, fields, high, low, quit): ");
				if( input.hasNext() ) {
					betType = input.next();
					if( betType.equalsIgnoreCase( triple ) || betType.equalsIgnoreCase( fields ) ||
							betType.equalsIgnoreCase( high ) || betType.equalsIgnoreCase( low ) ) {
						ResolveBet( betType, newWallet );
					}
					else if( betType.equalsIgnoreCase( "quit" ) ) {
						continueGame = false;
					}
					else
						System.out.println( "Wrong value. Try again.");
				}
			}
			else {
				continueGame = false;
			}
		} while( continueGame );
		System.out.println( "\tBet Statistics: ");
		System.out.println( "Initial money in wallet: " + initialMoney );
		System.out.println( "Final money in wallet: " + newWallet.check() );
		input.close();
	}
	
	public static void ResolveBet( String betType, Wallet newWallet ) {
		if ( betType != null && newWallet != null ) {
			System.out.println( "Amount in wallet = " + newWallet.check() );
			System.out.print( "Enter amount to bet: ");
			double betAmount = input.nextDouble();
			if ( betAmount > newWallet.check() ) {
				System.out.println( "Not enough money in wallet. Please try again." );
			}
			else {
				Dice dice1 = new Dice();
				Dice dice2 = new Dice();
				Dice dice3 = new Dice();
				int topFace1 = dice1.roll();
				int topFace2 = dice2.roll();
				int topFace3 = dice3.roll();
				boolean playerWon = checkBet( topFace1, topFace2, topFace3, betType );
				if( playerWon ) {
					System.out.println( "You won!" );
					if ( betType.equals( triple ) ) 
						newWallet.put( betAmount * 30 );
					else
						newWallet.put( betAmount );
				}	
				else {
					System.out.println( "You lost!" );
					newWallet.get( betAmount );
				}
			}
			System.out.println("Current amount in wallet = " + newWallet.check() );
		}
	}
	public static boolean checkBet( int r1, int r2, int r3, String bet ) {
		if ( bet != null ) {
			int sum = r1 + r2 + r3;
			if ( bet.equalsIgnoreCase( triple ) ) {
				if ( r1 == r2 && r2 == r3 ) {
					if ( r1 != 6 || r1 != 1 ) {
						return true;
					}
				}
			}
			if( bet.equalsIgnoreCase( fields ) ) {
				if( sum < fieldsLowerLimit || sum > fieldUpperLimit ) {
					return true;
				}
			}
			if( bet.equalsIgnoreCase( high ) ) {
				if( sum > highLimit ) {
					if( r1 == r2 && r2 == r3 ) {
						if( r1 == 6 && r1 == 5 && r1 == 4 ) {
							return false;
						}
					}
					return true;
				}
			}
			if( bet.equalsIgnoreCase( low ) ) {
				if ( sum < lowLimit ) {
					if( r1 == r2 && r2 == r3 ) {
						if( r1 == 1 && r1 == 2 && r1 == 3 ) {
							return false;
						}
					}
					return true ;
				}
			}
		}
		return false;
	}
}
