import java.io.*;
import java.util.*;

public class SatPix {

	public static void main(String[] args) throws IOException
	{
		boolean[][] booleanArr = fileToBoolArray("satpix.in");
		int sizeOfLargestPasture = 0;
		for(int r = 0; r < booleanArr.length; r++)
			for(int c = r%2; c < booleanArr[0].length; c+=2)
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
		String PATH = "satpix.in";
		int r, c;
		Scanner scan = new Scanner(PATH);
		//if (scan.hasNextInt())
			r = scan.nextInt();
		//if (scan.hasNextInt())
			c = scan.nextInt();
		
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
}