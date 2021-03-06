import java.util.Random;

/**
 * @author Eunsong Choi
 *
 */
public class ZeroSumGame {
	
	// returns a random mxn matrix representing a zero sum game 
	public static double[][] genGame(int m, int n, double max){
		double[][] A = new double[m][n];
		for ( int i = 0; i < m; i++ ){
			for ( int j = 0; j < n; j++ ){
				Random generator = new Random();
				double element = max*generator.nextDouble(); // random number in range of [0, max]
				A[i][j] = element;
			}
		}
		return A;
	}
	
}
