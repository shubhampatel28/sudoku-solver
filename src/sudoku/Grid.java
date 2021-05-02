package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;


	//
	// DON'T CHANGE THIS.
	//
	// Constructs a Grid instance from a string[] as provided by TestGridSupplier.
	// See TestGridSupplier for examples of input.
	// Dots in input strings represent 0s in values[][].
	//
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}


	//
	// DON'T CHANGE THIS.
	//
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}


	//
	// DON'T CHANGE THIS.
	// Copy ctor. Duplicates its source. You’ll call this 9 times in next9Grids.
	//
	Grid(Grid src)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
			for (int i=0; i<9; i++)
				values[j][i] = src.values[j][i];
	}


	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full. Don’t change
	// . Build 9 new grids.
	// 
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids()
	{		
		int xOfNextEmptyCell = 0;
		int yOfNextEmptyCell = 0;

		// Find x,y of an empty cell.
		for (int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if (values[i][j] == 0) {
					xOfNextEmptyCell = i;
					yOfNextEmptyCell = j;					
				}
			}
		}

		// Construct array list to contain 9 new grids.		
		ArrayList<Grid> grids = new ArrayList<Grid>();

		// Create 9 new grids as described in the comments above. Add them to grids.
		int value = 1;
		for (int i=0; i<9; i++) {
			Grid grid = new Grid(this);
			grid.values[xOfNextEmptyCell][yOfNextEmptyCell] = value;
			grids.add(grid);
			value++;
			System.out.println(grid);
		}

		return grids;
	}

	//Checks if there is a repeat in rol/col/block and determines if the grid is legal or illegal
	// returns true if there are no repeats in the array
	
	private boolean containNonZeroRepeat(int[] ints)
	{
		boolean[] observed = new boolean[10];
		
		for (int i: ints)
		{
			if (i == 0)
				continue;				
			else if (observed[i] == true)
				return true;			
			else
				observed[i] = true;		
		}
		return false;
	}

	//
	// COMPLETE THIS
	//
	// Returns true if this grid is legal. A grid is legal if no row, column, or
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	//
	public boolean isLegal()
	{
		// Check every row. If you find an illegal row, return false.
		for(int row = 0; row<9; row++) {
			if(containNonZeroRepeat(values[row]))
				return false;			
		}

		// Check every column. If you find an illegal column, return false.
		for(int col = 0; col<9; col++) {
			int[] column = new int[9];
			for(int row = 0; row<9; row++) {
				column[row] = values[row][col]; 
			}
			if(containNonZeroRepeat(column))
				return false;	
		}

		// Check every block. If you find an illegal block, return false.
		for (int outerBlock = 0; outerBlock < 3; outerBlock++) {
			for (int innerBlock = 0; innerBlock < 3; innerBlock++) {
				int[] arr = new int[9];
				int counter = 0;
				for (int i = innerBlock * 3; i < innerBlock * 3 + 3; i++) {
					for (int j = outerBlock * 3; j < outerBlock * 3 + 3; j++) {
						arr[counter] = values[i][j];
						counter++;
					}
				}
				if (containNonZeroRepeat(arr) == true)
					return false;
			}
		}		
		// All rows/cols/blocks are legal.
		return true; 

	}


	//
	// COMPLETE THIS
	//
	// Returns true if every cell member of values[][] is a digit from 1-9.
	//
	public boolean isFull()
	{
		for(int j=0; j<9; j++)
			for(int i=0; i<9; i++)
				if(values[j][i]==0)
					return false;
		return true; 
	}


	//
	// COMPLETE THIS
	//
	// Returns true if x is a Grid and, for every (i,j), 
	// x.values[i][j] == this.values[i][j].
	//
	public boolean equals(Object x)
	{
		Grid that = (Grid)x;
		for(int i = 0; i<9; i++)
			for(int j=0; j<9; j++)
				if(this.values[i][j] != that.values[i][j])
					return false;
		return true; 
	}
}
