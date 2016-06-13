import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/JDBC";
	private static final String USER = "root";
	private static final String PASSWORD = "MUSKyang5";

	private static Connection conn = null;

	static {
		try {
			// 1.加载驱动程序。这里用到了反向的技术，通过类名，去反向的将这个类加载到环境中
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获得数据库的连接// 注：小写变大写：Ctrl ＋ shift ＋ x；
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getconnection() {
		return conn;
	}

	public static void main(String[] args) throws ClassNotFoundException, Exception {
		// TODO Auto-generated method stub
		// 1.加载驱动程序。这里用到了反向的技术，通过类名，去反向的将这个类加载到环境中
		Class.forName("com.mysql.jdbc.Driver");
		// 2.获得数据库的连接// 注：小写变大写：Ctrl ＋ shift ＋ x；
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		// 注：这个阶段：连接了数据库，用了navicat，以及需要连接MySQL。
		// 3.通过数据库的连接操作数据库，实现增删改查
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select user_name, age from JDBC");

		while (rs.next()) {// 怎么从这个对象里面获取到我们想要的数据呢，如果为true则说明里面有数据
			System.out.println(rs.getString("user_name") + "," + rs.getInt("age"));
		}
	}

}
