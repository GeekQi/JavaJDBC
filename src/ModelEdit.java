import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModelEdit {
	public void addModel(Model m) throws Exception {

		// 1连接
		Connection conn = DBUtil.getconnection();

		// 2填写sql语句
		String sql = "insert into jdbc" + "(user_name, sex, age, birthday, email, mobile,"
				+ "create_user, create_date, update_user, update_date, isdel)"+ "value("
				+ "?,  ?,  ?,  ?,  ?,  ?, ?, current_date(), ?,current_date(), ?)";
		// 用？相当于占位符，再给这些参数赋值，这样执行的时候，这些参数再加载到sql语句，把sql语句添加完整再执行，这样就会减少对数据库的操作
		// 这里新的执行sql语句方法：这会把sql语句加载到驱动程序的执行程序中，但并不直接执行。

		// 3.预编译
		PreparedStatement ps = conn.prepareStatement(sql);// 预编译最后一句

		// 4.传参，给？赋值
		ps.setString(1, m.getUser_name());
		ps.setInt(2, m.getSex());
		ps.setInt(3, m.getAge());
		ps.setDate(4, new Date(m.getBirthday().getTime()));
		ps.setString(5, m.getEmail());
		ps.setString(6, m.getMobile());
		ps.setString(7, m.getCreate_user());
		ps.setString(8, m.getUpdate_user());
		ps.setInt(9, m.getIsdel());

		// 5.执行
		ps.execute();// 调用时才执行

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
			// System.out.println(rs.getString("user_name") + "," +
			// rs.getInt("age"));
		}
		return lm;
	}

	public Model get() {
		return null;
	}
}
