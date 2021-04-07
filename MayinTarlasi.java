package mayinTarlasi;

import java.util.Random;
import java.util.Scanner;

public class MayinTarlasi {

	int rowNumber, colNumber, size;
	int[][] map; // States mines location
	int[][] board; // Shows the moves of the player
	boolean game = true; // False when game is over

	Random rand = new Random();
	Scanner scan = new Scanner(System.in);

	// This method is constructor. It composes new game.
	MayinTarlasi(int rowN, int colN) {

		rowNumber = rowN;
		colNumber = colN;
		map = new int[rowNumber][colNumber];
		board = new int[rowNumber][colNumber];
		size = rowNumber * colNumber;

	}

	// Player plays the game entering the row and column number choices.
	public void run() {
		int count = 0;
		prepareGame();
		print(map);
		int row, col;
		System.out.println("Game is started !!");
		while (game) {
			print(board);
			System.out.println("Enter row number your choose : ");
			row = scan.nextInt();
			System.out.println("Enter column number your choose : ");
			col = scan.nextInt();
			count++;
			if (count == ((size / 4) * 3)) {
				System.out.println("Tebrikler!! Kazandiniz.");
				break;
			}
			if (map[row][col] != -1)
				checkMine(row, col);
			else {
				game = false;
				System.out.println("Game Over...");
			}

		}
	}

	// Oyunu hazirlar, mayinlarin yerlerini default sekilde belirler.
	public void prepareGame() {

		int randRow, randCol, count = 0;
		while (count < (size / 4)) {
			randRow = rand.nextInt(rowNumber);
			randCol = rand.nextInt(colNumber);
			if (map[randRow][randCol] != -1) {
				map[randRow][randCol] = -1;
				count++;
			}
		}

	}

	// 'run' metodunda seçilen lokasyonun kontrolü yapilir, eger
	// cevresinde mayin varsa ipucu yazdirilir. clue = ipucu.
	public void checkMine(int row, int col) {

		int clue = 0;

		if (col + 1 != colNumber && map[row][col + 1] == -1) // right
			clue += 1;
		if (col != 0 && map[row][col - 1] == -1) // left
			clue += 1;
		if (row + 1 != rowNumber && map[row + 1][col] == -1) // down
			clue += 1;
		if (row != 0 && map[row - 1][col] == -1) // up
			clue += 1;
		if (clue == 0)
			clue = -2;

		board[row][col] = clue;

	}

	// sürekli oyuncuya son durum yazdirilir.
	public void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				// alttaki if yazdirilan boardu düzgün hale getirmek için.
				// tek, çift karakter farki.
				if (arr[i][j] != -1 && arr[i][j] != -2)
					System.out.print(" ");
				System.out.print(arr[i][j] + " ");

			}
			System.out.println("");
		}
	}

	// aktif degildir. oyun bitigini kontrol etmektedir. su anda iki defa ayni sayi
	// tuslandiginda count artiyor ve oyun kazandi görünüyor. bu methodda bunun
	// önüne gecilebilir. fakat çok fazla for kullanildi.
	public boolean endGame(int[][] arr) {

		int zeroCount = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 0)
					zeroCount++;
			}
		}
		if (zeroCount == (size / 4)) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					if (arr[i][j] == 0)
						arr[i][j] = -1;
				}
			}
			System.out.println("Tebrikler!! Kazandiniz.");
			print(board);
			return true;
		}

		return false;
	}
}
