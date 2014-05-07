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
		double[][] A = {{-2.0, 3.0}, {3.0, -4.0}};
		ZeroSumGameLSolver sol1 = new ZeroSumGameLSolver(A);
		double value = sol1.getValue();
		double[] colStrategy = sol1.getColStrategy();
		double[] rowStrategy = sol1.getRowStrategy();
		for ( int i = 0; i < colStrategy.length; i++ ){
			System.out.println(colStrategy[i]);
		}
		for ( int i = 0; i < rowStrategy.length; i++ ){
			System.out.println(rowStrategy[i]);
		}
		System.out.println("value of the game is : " + value);
		//A = ZeroSumGame.genGame(m,n,max);
		
	}

}
