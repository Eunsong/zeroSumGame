/**
 * 
 */

/**
 * @author Eunsong Choi
 *
 */
public class RunZeroSumGameSolver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final int m = 2;
		final int n = 2;
		
		final double max = 1.0; // maximum element value
		final double delta = 0.1; // error parameter for MW method
		
		double[][] A = ZeroSumGame.genGame(m, n, max);
		
		// Linear-Programming approach (deterministic algorithm)
		long tiL = System.nanoTime();
		ZeroSumGameLSolver solL = new ZeroSumGameLSolver(A);
		long tfL = System.nanoTime();
				
		// Multiplicative weights update approach (approximate algorithm)
		long tiM = System.nanoTime();
		ZeroSumGameMWSolver solM = new ZeroSumGameMWSolver(A, delta);
		long tfM = System.nanoTime();
		
		System.out.println("value of the game(LP) : " + solL.getValue());
		System.out.println("computing time(LP) : " + (tfL - tiL));
		System.out.println("value of the game(MW) : " + solM.getValue());
		System.out.println("computing time(MW) : " + (tfM - tiM));
		

	}

}
