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
		System.out.println(sizeOfLargestPasture);
		out.close();
		}
	
	private static boolean[][] fileToBoolArray(String fileName) throws FileNotFoundException, IOException
	{	
		Scanner scan = new Scanner(new File(fileName));
		int r = scan.nextInt();
		int c = scan.nextInt();
		
		boolean[][] booleanArr = new boolean[r][c];
		
		scan.nextLine();
		String cL = new String();
		for (int i=0; i<r && scan.hasNextLine(); i++){
			cL = scan.nextLine();
			System.out.println(cL);
			for (int j=0; j<c; j++){
				if ( cL.charAt(j)==('*'))
					booleanArr[i][j]=true;
				else 
					booleanArr[i][j]=false;
			}
		}
		scan.close();
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