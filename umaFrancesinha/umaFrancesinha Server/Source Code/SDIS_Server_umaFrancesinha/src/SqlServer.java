import java.sql.*;

public class SqlServer
{
	static Connection conn=null;
	static Statement stmt=null;
  
  public SqlServer()
  {
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:sdis.db");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
    System.out.println("Opened database successfully");
  }
  public boolean executeQuery(String query) throws SQLException {
	  int err=0;
	  try {
		stmt = conn.createStatement();
	    stmt.executeUpdate(query);
	  } catch ( Exception e ) {
		  err=1;
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	  if(err==0) {
		  System.out.println("Executed Successfully");
		  return true;
	  }
	  return false;
  }
  public ResultSet executeQueryRS(String query) throws SQLException {
	  System.out.println(query);
	  conn = DriverManager.getConnection("jdbc:sqlite:sdis.db");
	  ResultSet rs=null;
	  try {
		stmt = conn.createStatement();
	    rs = stmt.executeQuery(query);
	  } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	  System.out.println("Executed SuccessfullyRS");
	  return rs;
  }
  
  public void closes() throws SQLException {
	  stmt.close();
	  conn.close();
  }
}