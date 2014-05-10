/**
 * Solves a two-person zero-sum game represented by a given m x n matrix,
 * using multiplicative weights update algorithm 
 */

import java.util.Arrays;

/**
 * @author eunsong
 *
 */
public class ZeroSumGameMWSolver {

	private double[][] A;
	private double value; // value of the game
	private double[] x; // row's best strategy
	private double[] y; // column's best strategy
	private double delta;
	private int T;
	private double epsilon;
	
	public ZeroSumGameMWSolver(double[][] A, double delta){
		this.A = A;
		this.x = new double[A.length];
		Arrays.fill(x, 1.0);
		this.y = new double[A[0].length];
		this.delta = delta;
		this.T = (int)(16*Math.log((double)A.length)/(delta*delta)); // number of rounds
		this.epsilon = Math.min(delta/4.0, 0.5);
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
		
		
	}
	
	
}
