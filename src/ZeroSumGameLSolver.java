/**
 * Solves for a value and a strategy of a two-person zero-sum game
 * using Linear programming, given M-by-N matrix representing the game. 
 */
import java.util.Arrays;

/**
 * @author Eunsong Choi
 *
 */
public class ZeroSumGameLSolver {
	private double[][] A;
	private double value; // value of the game
	private double[] x; // row's best strategy
	private double[] y; // column's best strategy
	
	public ZeroSumGameLSolver(double[][] A){
		this.A = A;
		this.x = new double[A.length];
		this.y = new double[A[0].length];
		solve();
	}
	
	public double getValue() {
		return this.value;
	}
	
	public double[] getColStrategy() {
		return this.y;
	}
	
	public double[] getRowStrategy(){
		return this.x;
	}
	
	public void solve(){
		int m = this.A.length;
 		int n = this.A[0].length;
 		
 		// build a matrix M in a standard linear programming problem format
 		double[] c = new double[n+2];
 		double[] b = new double[n+2];
 		Arrays.fill(c, 0.0);
 		Arrays.fill(b, 0.0);
 		c[n] = 1.0;
 		c[n+1] = -1.0;
 		b[n] = 1.0;
 		b[n+1] = -1.0;
 		
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
 		double[] y_buff = LP.primal(); // column's best strategy
 		this.value = y_buff[n] - y_buff[n+1];
 		for ( int i = 0; i < n; i++ ){
 			this.y[i] = y_buff[i];
 		}
 		double[] x_buff = LP.dual(); // row's best strategy
 		for ( int i = 0; i < m; i++ ){
 			this.x[i] = x_buff[i];
 		}
 		
	}
	
}
