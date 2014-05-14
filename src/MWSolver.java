/**
 * Solves a two-person zero-sum game represented by a given m x n matrix,
 * using multiplicative weights update algorithm. Note that the matrix element e_ij
 * indicates the penalty paid by row i to column j, i.e. column's winning.
 */

import java.util.Arrays;

/**
 * @author Eunsong Choi
 *
 */
public class MWSolver{

	private double[][] A;
	private double value; // value of the game
	private double[] x; // column's best strategy
	private double[] y; // row's best strategy
	private double delta; // error factor
	private int T; // number of MW rounds 
	private double epsilon;
	
	public MWSolver(double[][] A, double delta) throws IllegalArgumentException{
		checkElements(A); // throw an Exception if there are invalid elements
		this.A = A;
		this.y = new double[A.length];
		Arrays.fill(y, 1.0);
		this.x = new double[A[0].length];
		this.delta = delta;
		this.T = (int)(16*Math.log((double)A.length)/(delta*delta)); // number of rounds
		this.epsilon = Math.min(delta/4.0, 0.5);
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
		int m = this.y.length;
		double[] w = new double[m]; // weights
		Arrays.fill(w, 1.0);
		double[][] D = new double[this.T][m]; // distribution of row's pure strategy
		Arrays.fill(D[0], 1.0/((double)m));
		double sum = 0.0;
		
		for ( int t = 0; t < T; t++ ){
			int j = getMaxJ(D[t]);
			sum += getValue(D[t], j);
			double sumW = 0.0;
			for ( int i = 0; i < m; i++ ){
				w[i] = w[i]*Math.pow(1-this.epsilon, this.A[i][j]);
				sumW += w[i];
			};
			for ( int i = 0; i < m; i++ ){
				if ( t != T - 1){
					D[t+1][i] = w[i]/sumW;
				}
			}
		}
		
		this.value = sum/((double)T); // approximate value of the game with possible error of delta
		this.y = D[T-1]; // D_final
		
	}
	
	// returns column index j that maximizes A[D][j] for a given D, the distribution of row strategies
	public int getMaxJ(double[] D){
		double maxValue = 0.0;
		int maxIndex = 0;
		for ( int j = 0; j < x.length; j++ ){
			if ( j == 0 ){
				maxValue = getValue(D, j);
				maxIndex = j;
			}
			else if ( maxValue < getValue(D,j) ) {
				maxValue = getValue(D, j);
				maxIndex = j;				
			}
		}
		return maxIndex;
	}
	
	// returns the value of A[D][j] if D is the distribution of row strategies 
	public double getValue(double[] D, int j){
		double sum = 0.0;
		for ( int i = 0; i < D.length; i++ ){
			sum += D[i]*A[i][j];
		}
		return sum;
	}
	
	public void checkElements(double[][] A) throws IllegalArgumentException{
		for ( int i = 0; i < A.length; i++ ){
			for ( int j = 0; j < A[0].length; j++ ){
				if ( A[i][j] < 0 || A[i][j] > 1.0 ) {
					throw new IllegalArgumentException("All matrix elements should be in range of [0.0,1.0]");
				}
			}
		}	
	}
	
}
