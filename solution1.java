/*===============================================================
* Assignment 4 Task 1
* Name: Akilesh Jayakumar
* Student Number: 7901240
* Date: 2 September 2023
* ===============================================================*/

import java.sql.*;

class solution1
{
  public static void main (String args [])
       throws SQLException, ClassNotFoundException
  {
    // Load the Oracle JDBC driver
    Class.forName ("oracle.jdbc.driver.OracleDriver");
    Connection conn = DriverManager.getConnection
      ("jdbc:oracle:thin:@localhost:1521:db",  "tpchr", "oracle");

  try{
	System.out.println( "Just started");
	Statement stmt1 = conn.createStatement ();
    	Statement stmt2 = conn.createStatement();
	double total_money = 0.0;
        ResultSet rset1 = stmt1.executeQuery( "SELECT O_CUSTKEY, SUM(O_TOTALPRICE) FROM CUSTOMER JOIN ORDERS ON C_CUSTKEY = O_CUSTKEY WHERE C_CUSTKEY < 5 GROUP BY O_CUSTKEY ORDER BY O_CUSTKEY ASC" );
	long customer_key1;
	while ( rset1.next() ) {
  		customer_key1 = rset1.getInt(1);
  		total_money = rset1.getDouble(2);
        System.out.println( "Customer: " + customer_key1 + "Money spent: " + total_money ); 
		}
	}
   catch (SQLException e ) {
     String errmsg = e.getMessage();
     System.out.println( errmsg );
     }
  }
}

// --- Duration and Results of task1.java and solution1.java ---

/*[oracle@localhost TPCHR]$ javac task1.java
[oracle@localhost TPCHR]$ time java task1
Just started
Customer: 1Money spent: 840224.8999999999
Customer: 2Money spent: 1331727.0099999998
Customer: 4Money spent: 3237356.9000000004

real	1m54.463s
user	0m5.723s
sys	0m24.791s
[oracle@localhost TPCHR]$ javac solution1.java
[oracle@localhost TPCHR]$ time java solution1
Just started
Customer: 1Money spent: 840224.9
Customer: 2Money spent: 1331727.01
Customer: 4Money spent: 3237356.9

real	0m3.007s
user	0m1.845s
sys	0m1.378s
[oracle@localhost TPCHR]$*/

// --- Explanation of why the original application was slower than the improved one ---

/* When analysing the task1.java code, it is noted that the segment of application processes two expensive 'SELECT' statements and one loop to compute the total money spent (O_TOTALPRICE).

First the "SELECT * FROM CUSTOMER WHERE C_CUSTKEY<5 ORDER BY C_CUSTKEY ASC" statement. The statement is sent to the dbms server which in turn returns the set of CUSTOMER rows.

Second the "SELECT * FROM ORDERS" statement. The statement is sent to the dbms server which in turn returns the set of ORDERS rows.

Third the 'while(rset2.next())' statement loops through the set of rows returned from the dbms for the ORDERS.

The inefficiency of task1.java comes from two expensive 'SELECT' operations and one loop. These three operations loops increase the processing at the client side.

To improve the performance of the application, we can let the server, which is more powerful, to compute the total amount of money spent before returning the aggregated value to the application to display.*/
