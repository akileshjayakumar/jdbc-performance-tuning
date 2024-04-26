import java.sql.*;

class task1
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
        ResultSet rset1 = stmt1.executeQuery( "SELECT * FROM CUSTOMER WHERE C_CUSTKEY<5 ORDER BY C_CUSTKEY ASC" );
	long customer_key1;
	while ( rset1.next() ) 
        {
  		customer_key1 = rset1.getInt(1);
        	ResultSet rset2 = stmt2.executeQuery( "SELECT * FROM ORDERS" );
		long customer_key2;
                total_money = 0.0;
		while ( rset2.next() ) 
        	{
			customer_key2 = rset2.getInt(2);
			if (customer_key1 == customer_key2)
				if ( (customer_key1 < 10) &&
				     (customer_key2 < 10) )
					 total_money += rset2.getDouble(4);   
		}
                if (total_money != 0.0)
                  System.out.println( "Customer: " + customer_key1 + "Money spent: " + total_money ); 
	}
    }
   catch (SQLException e )
   {
     String errmsg = e.getMessage();
     System.out.println( errmsg );
   }
  }
}