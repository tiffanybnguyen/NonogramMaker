package edu.ou.cs2334.project5.models;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NonogramMakerModel {

	
	private static final char EMPTY_CELL_CHAR = '0';
	private static final char FILLED_CELL_CHAR = '1';
	
	private int numRows;
	private int numCols;
	private boolean[] grid;
	
	/**
	 * default NonogramMakerModel constructor
	 * initializes numRows, numCols, and grid
	 * @param numRows
	 * @param numCols
	 */
	public NonogramMakerModel(int numRows, int numCols) {
		if (numRows < 1 || numCols < 1) {
			throw new IllegalArgumentException();
		}
		this.numRows = numRows;
		this.numCols = numCols;
		grid = new boolean[numRows*numCols];
		Arrays.fill(grid, false);
	}
	
	/**
	 * NonogramMakerModel constructor
	 * reads data from file to populate the grid array
	 * initializes numRows, numCols, and grid
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public NonogramMakerModel(File file) throws FileNotFoundException, IOException {
		
		Scanner fileScanner = new Scanner(file);
		
		while(fileScanner.hasNextLine()) {
			
			String line = fileScanner.nextLine();
			String[] fileData = null;
			fileData = line.split(" ");
			for (String s : fileData) {
				System.out.println(s);
			}
			numRows = Integer.parseInt(fileData[0]);
			numCols = Integer.parseInt(fileData[1]);
			
			if (numRows < 1 || numCols < 1) {
				throw new IllegalArgumentException();
			}
			
			grid = new boolean[numRows*numCols];
			
			//skips to the cool square thing
			for (int i = 0; i < numRows + numCols; i++) {
				line = fileScanner.nextLine();
			}
			
			String[] arr;
			int counter = 0;
			for (int a = 0; a < numRows; a++) {
				
				line = fileScanner.next();
				arr = line.split("");
				
				for (int b = 0; b < numCols; b++) {
					if (Integer.parseInt(arr[b]) == 0) {
						grid[counter] = false;
					} else {
						grid[counter] = true;
					}
					counter++;
				}
			}	
		}
		
		fileScanner.close();
		
	}
	
	/**
	 * NonogramMakerModel constructor
	 * takes in file name to read data from file and populate the grid array
	 * initializes numRow, numCols, and grid
	 * @param filename
	 * @throws IOException
	 */
	public NonogramMakerModel(String filename) throws IOException {
		this(new File(filename));
	}
	
	/**
	 * creates and returns copy of grid array
	 * @return grid array
	 */
	public boolean[] getGrid() {
		return Arrays.copyOf(grid, grid.length);
	}
	
	/**
	 * returns the cell state at a given position
	 * @param rowIdx
	 * @param colIdx
	 * @return cell state at given position
	 */
	public boolean getCell(int rowIdx, int colIdx) {
		return grid[rowIdx*numCols + colIdx];
	}
	
	/**
	 * updates the cell state at the given position
	 * @param rowIdx
	 * @param colIdx
	 * @param state
	 */
	public void setCell(int rowIdx, int colIdx, boolean state) {
		grid[rowIdx*numCols + colIdx] = state;
	}
	
	/**
	 * returns the number of grid rows
	 * @return number of grid rows
	 */
	public int getNumRows() {
		return numRows;
	}
	
	/**
	 * returns the number of column rows
	 * @return number of column rows
	 */
	
	public int getNumCols() {
		return numCols;
	}
	
	/**
	 * creates and calculates the nonogram numbers for a given array of cell states
	 * @param cells
	 * @return List of nonogram numbers for associated cells
	 */
	
	public static List<Integer> project(boolean[] cells) {
		List<Integer> list = new ArrayList<Integer>();
		int num = 0;
		boolean flag = true;
		
		for (int i = 0; i < cells.length; i++) {
			if (cells[i]) {
				num++;
				flag = false;
			} else {
				if (num != 0) {
					list.add(num);
				}
				num = 0;
			}
		}
		
        //adds the last value to grid if num isn't 0
        if (num != 0) {
            list.add(num);
            flag = false;
        }
        
        //creates 0 placeholder if no true exists
        if (flag) {
            list.add(0);
        }
		
		return list;
	}
	
	/**
	 * creates and calculates the nonogram numbers for the given rowIdx of the array
	 * @param rowIdx
	 * @return List of nonogram numbers for the row associated with rowIdx
	 */
	public List<Integer> projectRow(int rowIdx) {
		boolean[] rowCells = new boolean[numCols];
		for (int i = 0; i < numCols; i++) {
			rowCells[i] = getCell(rowIdx, i);
		}
		return project(rowCells);
	}
	
	/**
	 * creates and calculates the nonogram numbers for the given colIdx of the array
	 * @param colIdx
	 * @return List of nonogram numbers for the column associated with colIdx
	 */
	public List<Integer> projectCol(int colIdx) {
		boolean[] colCells = new boolean[numRows];
		for (int i = 0; i < numRows; i++) {
			colCells[i] = getCell(i, colIdx);
		}
		return project(colCells);
	}
	
	/**
	 * saves the output of toString to a text file with the given name
	 * @param filename
	 * @throws IOException
	 */
	public void saveToFile(String filename) throws IOException {
		FileOutputStream stream = new FileOutputStream(filename);
		PrintWriter output = new PrintWriter(stream);
		String str = this.toString();
		
		output.print(str.strip());
		
		output.close();
		
		
	}
	
	/**
	 * returns a string representation of the puzzle.
	 * The first line contains the dimensions of the grid
	 * The next part contains projections of the rows and columns
	 * The last part encodes the grid elements
	 * @return string representation of the puzzle
	 */
	public String toString() {
		
	    StringBuilder sb = new StringBuilder();
	    String s;
	    sb.append(numRows + " " + numCols);
	    sb.append("\n");
	    
	    List<Integer> list = new ArrayList<Integer>();
	    
	    //rows
	    for (int i = 0; i < numRows; i++) {
	    	list = projectRow(i);
	    	
	    	for (int j = 0; j < list.size(); j++) {
	    		if (j != list.size()-1) {
	    			sb.append(list.get(j) + " ");
	    		} else {
	    			sb.append(list.get(j));
	    		}
	    	}
	    	sb.append(System.lineSeparator());
	    }
	    
	    //columns
	    for (int i = 0; i < numCols; i++) {
	    	list = projectCol(i);
	    	
	    	for (int j = 0; j < list.size(); j++) {
	    		if (j != list.size()-1) {
	    			sb.append(list.get(j) + " ");
	    		} else {
	    			sb.append(list.get(j));
	    		}
	    	}
	    	sb.append(System.lineSeparator());
	    }
	    
	    //grid thing
	    for (int a = 0; a < numRows; a++) {
	    	for (int b = 0; b < numCols; b++) {
	    		if (getCell(a, b)) {
	    			sb.append("1");
	    		} else {
	    			sb.append("0");
	    		}
	    	}
	    	if (a != numRows - 1) {
	    		sb.append(System.lineSeparator());
	    	}
	    }
	    
	    s = sb.toString();
	    
	    return s.strip();
	    
	}
	
}