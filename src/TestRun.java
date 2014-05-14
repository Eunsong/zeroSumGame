import java.util.*;

public class TestRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = 2;
		int n = 2;
		double max = 10.0;
		//double[][] A = new double[m][n];
		//double[][] A = {{0.2523, 0.873}, {0.82, 0.173}};
		double[][] A = {{0.2, 0.3}, {0.8,0.7}}; 
		LPSolver sol1 = new LPSolver(A);
		double value = sol1.getValue();
		double[] colStrategy = sol1.getColStrategy();
		double[] rowStrategy = sol1.getRowStrategy();

		System.out.println("Col strategy(LP)");
		for ( int i = 0; i < colStrategy.length; i++ ){
			System.out.println(colStrategy[i]);
		}
		System.out.println("Row strategy(LP)");
		for ( int i = 0; i < rowStrategy.length; i++ ){
			System.out.println(rowStrategy[i]);
		}

		
		System.out.println("value of the game is(LP) : " + value);
		//A = ZeroSumGame.genGame(m,n,max);
		double delta = 0.1;
		MWSolver sol2 = new MWSolver(A, delta);
		double value2 = sol2.getValue();
		double[] rowStrategyMW = sol2.getRowStrategy();
		System.out.println("Row strategy(MW)");
		for ( int i = 0; i < rowStrategyMW.length; i++ ){
			System.out.println(rowStrategyMW[i]);
		}
		System.out.println("value of the game is(MW) : " + value2);
		
	}

}
