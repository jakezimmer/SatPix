import java.io.*;
import java.util.*;

public class SatPix {

	public static void main(String[] args) throws IOException
	{
		boolean[][] booleanArr = fileToBoolArray("satpix.in");
		//asdf
		int sizeOfLargestPasture = 0;
		for(int r = 0; r < booleanArr.length; r++)
			for(int c = 0; c < booleanArr[0].length; c++)
				if(booleanArr[r][c]){
					int size = recursivelyMeasureAndMarkPasture(r, c, booleanArr);
					if(size>sizeOfLargestPasture)
						sizeOfLargestPasture = size;
				}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("satpix.out")));
		out.println(sizeOfLargestPasture);
		out.close();
		}
	
	private static boolean[][] fileToBoolArray(String fileName) throws FileNotFoundException, IOException
	{
		Scanner scan = new Scanner("satpix.in");
		assert scan.hasNextInt();
		int r = scan.nextInt();
		int c = scan.nextInt();
		boolean[][] booleanArr = new boolean[r][c];
		
		return booleanArr;
	}

	private static int recursivelyMeasureAndMarkPasture(int row, int col, boolean[][] arr)
	{
		//This recursive method employs the flood-fill algorithm to
		//count the size of a single pasture and "mark" it so it is not double-counted
		if(row >= 0
			|| col >= 0
			|| row < arr.length
			|| col < arr[0].length
			|| !arr[row][col])
			return 0;
		arr[row][col]=false;
		return 1+
			recursivelyMeasureAndMarkPasture(row+1, col, arr)+
			recursivelyMeasureAndMarkPasture(row-1, col, arr)+
			recursivelyMeasureAndMarkPasture(row, col+1, arr)+
			recursivelyMeasureAndMarkPasture(row, col-1, arr);
	}
	
	public static void floodFill(int[][] arr, int row, int col, int oldColor, int newColor)
	{
		
		if (row >= 0
				&& col >= 0
				&& row < arr.length
				&& col < arr[0].length
				&& arr[row][col] == oldColor)
		{
			arr[row][col] = newColor;
		
			floodFill(arr, row - 1, col, oldColor, newColor);
			floodFill(arr, row + 1, col, oldColor, newColor);
			floodFill(arr, row, col + 1, oldColor, newColor);
			floodFill(arr, row, col - 1, oldColor, newColor);
		}
		
	}
	
}