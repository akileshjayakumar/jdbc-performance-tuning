/*===============================================================
* Assignment 4 Task 2
* Name: Akilesh Jayakumar
* Student Number: 7901240
* Date: 2 September 2023
* ===============================================================*/

import java.sql.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;
class solution2
{
  public static void main (String args [])
       throws SQLException, ClassNotFoundException
  {
     OracleDataSource ods = new OracleDataSource();
      ods.setURL("jdbc:oracle:thin:@localhost:1521:db");
      ods.setUser("tpchr");
      ods.setPassword("oracle");
      Connection conn = ods.getConnection();
      System.out.println( "Connected as tpchr." ); 
  try{
	System.out.println( "Just started");
	Statement stmt = conn.createStatement();
    ResultSet rset = stmt.executeQuery( "SELECT COUNT(*) FROM ORDERS WHERE O_TOTALPRICE > 200000.0" );
    rset.next();
    System.out.println( "Total number of orders above 200K = " + rset.getInt(1));
    }
   catch (SQLException e )
   {
     String errmsg = e.getMessage();
     System.out.println( errmsg );
   }
  }
}

// --- Duration and Results of task2.java and solution2.java ---

/*[oracle@localhost TPCHR]$ javac task2.java
[oracle@localhost TPCHR]$ time java task2
Connected as tpchr.
Just started
Total number of orders above 200K = 120648

real	0m30.541s
user	0m4.165s
sys	0m6.195s
[oracle@localhost TPCHR]$ javac solution2.java
[oracle@localhost TPCHR]$ time java solution2
Connected as tpchr.
Just started
Total number of orders above 200K = 120648

real	0m3.061s
user	0m1.972s
sys	0m1.433s
[oracle@localhost TPCHR]$*/

// --- Explanation of why the original application was slower than the improved one ---

/* When analysing the task2.java code, it is noted that the segment of application processes one expensive 'SELECT' statement and one loop to compute the total number of orders above 200K.

First the "SELECT * FROM ORDERS" statement. The statement is sent to the dbms server which in turn returns the set of ORDERS rows.

Second the 'while (rset.next())' statement loops through the set of rows returned from the dbms for the ORDERS.

The total number of orders above 200K are accumulated and at the same time, the total number of rows is accumulated.

Finally, after the loop is completed, the total number of orders above 200K is displayed

The inefficiency of task2.java comes from one expensive 'SELECT' operation and one loop. These two operations loops increase the processing at the client side.

To improve the performance of the application, we can let the server, which is more powerful, to compute the total number of orders above 200K before returning the aggregated value to the application to display.*/
