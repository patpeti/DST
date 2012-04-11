package dst1.interceptor;

import org.hibernate.EmptyInterceptor;

public class SQLInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 3894614218727237142L;
	public static int computerCount = 0;
	public static int executionCount = 0;

	public String onPrepareStatement(String sql) {
		
		// TODO
		
		if((sql.contains("select") || sql.contains("SELECT")) &&
				(sql.contains("computer") || sql.contains("COMPUTER"))){
			
			computerCount++;
		}
		
		if((sql.contains("select") || sql.contains("SELECT")) &&
				(sql.contains("execution") || sql.contains("EXECUTION"))){
			
			executionCount++;
		}
		return sql;
	}

	public static int getComputerCount() {
		return computerCount;
	}

	public static int getExecutionCount() {
		return executionCount;
	}
	
	public static void resetCounters(){
		executionCount = 0;
		computerCount = 0;
	}
	public static void getLogOutput(){
		System.out.println("##################SQLInterceptor########################");
		System.out.println("Counted Select Statements for Computer "+ computerCount);
		System.out.println("Counted Select statements for Execution "+ executionCount);
	}

}
