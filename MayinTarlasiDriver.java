package mayinTarlasi;

import java.util.Scanner;

public class MayinTarlasiDriver {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int row,column;
		System.out.println("Welcome to Mayin Tarlasi Game");
		System.out.println("Enter row number you want play : ");
		row = scan.nextInt();
		System.out.println("Enter column number : ");
		column = scan.nextInt();
		
		MayinTarlasi newGame = new MayinTarlasi(row, column);
		newGame.run();
			
	}
		
}
