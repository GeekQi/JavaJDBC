import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModelEdit {
	public void addModel() {

	}

	public void updateModel() {

	}

	public void delModel() {

	}

	public List<Model> query() throws Exception {
		Connection conn = DBUtil.getconnection();
		// 3.通过数据库的连接操作数据库，实现增删改查
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select user_name, age from JDBC");

		List<Model> lm = new ArrayList<Model>();
		Model m = null;

		while (rs.next()) {// 怎么从这个对象里面获取到我们想要的数据呢，如果为true则说明里面有数据
			m = new Model();
			m.setUser_name(rs.getString("user_name"));
			m.setAge(rs.getInt("age"));
			lm.add(m);
//			System.out.println(rs.getString("user_name") + "," + rs.getInt("age"));
		}
		return lm;
	}

	public Model get() {
		return null;
	}
}
