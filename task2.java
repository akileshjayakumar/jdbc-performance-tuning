import java.sql.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;
class task2
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
        ResultSet rset = stmt.executeQuery( "SELECT * FROM ORDERS" );
	int counter =0;
        float o_totalprice;
	while ( rset.next() ) 
        {
  		o_totalprice = rset.getFloat(4);
		if ( o_totalprice > 200000.0 )
		   counter++;
	}
	System.out.println( "Total number of orders above 200K = " + counter );
    }
   catch (SQLException e )
   {
     String errmsg = e.getMessage();
     System.out.println( errmsg );
   }
  }
}