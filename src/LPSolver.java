/**
 * Solves for a value and a strategy of a two-person zero-sum game
 * using Linear programming, given M-by-N matrix representing the game. 
 * Note that the matrix element e_ij
 * indicates the penalty paid by row i to column j, i.e. column's winning.
 */
import java.util.Arrays;

/**
 * @author Eunsong Choi
 *
 */
public class LPSolver {
	private double[][] A;
	private double value; // value of the game
	private double[] x; // column's best strategy
	private double[] y; // row's best strategy	

	public LPSolver(double[][] A){
		this.A = A;
		this.y = new double[A.length];
		this.x = new double[A[0].length];
		solve();
	}
	
	public double getValue() {
		return this.value;
	}
	
	public double[] getColStrategy() {
		return this.x;
	}
	
	public double[] getRowStrategy(){
		return this.y;
	}
	
	public void solve(){
		int m = this.A.length;
 		int n = this.A[0].length;
 		
 		// build a matrix M in a standard linear programming problem format
 		double[] c = new double[n+2];
 		double[] b = new double[m+2];
 		Arrays.fill(c, 0.0);
 		Arrays.fill(b, 0.0);
 		c[n] = 1.0;
 		c[n+1] = -1.0;
 		b[m] = 1.0;
 		b[m+1] = -1.0;
 		
 		double[][] M = new double[m+2][n+2];
 		for ( int i = 0; i < m+2; i++ ){	
 			if ( i < m ) {
 				for ( int j = 0; j < n+2; j++) {
 					if ( j < n ){
 						M[i][j] = -this.A[i][j];
 					}
 					else if ( j == n){
 						M[i][j] = 1.0;
 					}
 					else M[i][j] = -1.0;
 				} 				
 			}
 			else if ( i == m ) {
 				for ( int j = 0; j < n+2; j++ ){
 					if ( j < n ){
 						M[i][j] = 1.0;
 					}
 					else M[i][j] = 0.0;
 				}
 			}
 			else {
 				for ( int j = 0; j < n+2; j++ ){
 					if ( j < n ){
 						M[i][j] = -1.0;
 					}
 					else M[i][j] = 0.0;
 				}
 			}
 		}
 		
 		// solve LP using Simplex algorithm
 		Simplex LP = new Simplex(M, b, c);
 		double[] x_buff = LP.primal(); // column's best strategy
 		this.value = x_buff[n] - x_buff[n+1];
 		for ( int i = 0; i < n; i++ ){
 			this.x[i] = x_buff[i];
 		}
 		double[] y_buff = LP.dual(); // row's best strategy
 		for ( int i = 0; i < m; i++ ){
 			this.y[i] = y_buff[i];
 		}
 		
	}
	
}
