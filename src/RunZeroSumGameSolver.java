/**
 * 
 */

import java.io.PrintStream;
import java.io.File;
import java.io.IOException;

/**
 * @author Eunsong Choi
 *
 */
public class RunZeroSumGameSolver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// abort program if correct number of command-line argument is not given
		if ( args.length != 1 ){
			System.out.println(args.length);
			System.out.println("Usage : RunZeroGameSolver output.dat");
			System.exit(0);
		}
		
		final int m0 = 2;
		final int increment = 1;
		final int nRepeat = 20; // number of repeated calculations for better statistical average
		final int maxM = 30;
		
		final double max = 1.0; // maximum element value
		final double delta = 0.1; // error parameter for MW method
		
		File output = new File(args[0]);
		try {
			PrintStream ps = new PrintStream(output);
			ps.println("# Value of zero-sum games computed from Linear programming approach " +
					"and Multiplicative-Weights-Update approach");
			ps.println("#m(matrix size)		value(LP)	value(MW)	comp_time(LP)	comp_time(MW)");
			
			
			for ( int m = m0; m < maxM; m++ ){

				double dTLP = 0.0; // computing time of LP approach
				double dTMW = 0.0; // computing time of MW approach
				double valLP = 0.0; // value computed from LP 
				double valMW = 0.0; // value computed from MW
				
				for ( int r = 0; r < nRepeat; r++ ){
				
					double[][] A = ZeroSumGame.genGame(m, m, max);
				
					// Linear-Programming approach (deterministic algorithm)
					long tiL = System.nanoTime();
					ZeroSumGameLSolver solL = new ZeroSumGameLSolver(A);
					long tfL = System.nanoTime();
					dTLP += (double)(tfL-tiL)/(1000000.0);
					valLP += solL.getValue();
												
					// Multiplicative weights update approach (approximate algorithm)
					long tiM = System.nanoTime();
					ZeroSumGameMWSolver solM = new ZeroSumGameMWSolver(A, delta);
					long tfM = System.nanoTime();				
					dTMW += (double)(tfM-tiM)/(1000000.0);
					valMW += solM.getValue();		
									
				}

				ps.println(String.format("%d	%f		%f		%fms		%fms", 
						m, valLP/(double)nRepeat, valMW/(double)nRepeat, dTLP/(double)nRepeat, dTMW/(double)nRepeat ));
			
			}
			ps.close();
		}
		catch ( IOException ex ){
			System.out.println("Cannot write into file " + args[3]);
		}
		
	

	}

}
