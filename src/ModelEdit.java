import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelEdit {
	public void addModel(Model m) throws Exception {

		// 1连接
		Connection conn = DBUtil.getconnection();

		// 2填写sql语句
		String sql = "insert into jdbc" + "(user_name, sex, age, birthday, email, mobile,"
				+ "create_user, create_date, update_user, update_date, isdel)" + "value("
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

	public void updateModel(Model m) throws SQLException {
		// 1连接
		Connection conn = DBUtil.getconnection();

		// 2填写sql语句
		String sql = " update jdbc" + " set user_name=?, sex=?, age=?, birthday=?, email=?, mobile=?, "
				+ " update_user=?, update_date=current_date(), isdel=? " + " where id = ?";
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
		ps.setString(7, m.getUpdate_user());
		ps.setInt(8, m.getIsdel());
		ps.setInt(9, m.getId());

		// 5.执行
		ps.execute();// 调用时才执行

	}

	public void delModel(Integer id) throws SQLException {
		// 1连接
		Connection conn = DBUtil.getconnection();
		// 注：Ctrl ＋ D：可以删除行
		// 2填写sql语句
		String sql = "delete from jdbc" + " where id = ?";
		// 用？相当于占位符，再给这些参数赋值，这样执行的时候，这些参数再加载到sql语句，把sql语句添加完整再执行，这样就会减少对数据库的操作
		// 这里新的执行sql语句方法：这会把sql语句加载到驱动程序的执行程序中，但并不直接执行。

		// 3.预编译
		PreparedStatement ps = conn.prepareStatement(sql);// 预编译最后一句

		// 4.传参，给？赋值

		ps.setInt(1, id);

		// 5.执行
		ps.execute();// 调用时才执行

	}

	public List<Model> query() throws Exception {
		Connection conn = DBUtil.getconnection();
		// 3.通过数据库的连接操作数据库，实现增删改查
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select id, user_name, age from JDBC");

		List<Model> lm = new ArrayList<Model>();
		Model m = null;

		while (rs.next()) {// 怎么从这个对象里面获取到我们想要的数据呢，如果为true则说明里面有数据
			m = new Model();
			m.setId(rs.getInt("id"));
			m.setUser_name(rs.getString("user_name"));
			m.setAge(rs.getInt("age"));
			lm.add(m);
			// System.out.println(rs.getString("user_name") + "," +
			// rs.getInt("age"));
		}
		return lm;
	}

	// 注：上述的query方法相当于全部查询，不管这张表里有多少记录，都会查询出来，没有加过滤条件。
	// 实际开发中，肯定会按照年龄或者性别来查询，我们进一步优化查询方法
	public List<Model> query(String name, String mobile,String email) throws Exception {
		List<Model> result = new ArrayList<Model>();

		Connection conn = DBUtil.getconnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from JDBC");

		// 1.加了一个查询，用户名
//		sb.append(" where user_name = ? and mobile = ?");
		//可能记不住全部的名字
		sb.append(" where user_name like ? and mobile like ? and email like? ");
		// 2.预编译，然后把参数传进去
		PreparedStatement ps = conn.prepareStatement(sb.toString());
//		ps.setString(1, name);
//		ps.setString(2, mobile);
		ps.setString(1, "%"+ name + "%");
		ps.setString(2, "%" + mobile + "%");
		ps.setString(3, "%" + email + "%");
		System.out.println(sb.toString());
		// 3.执行查询
		ResultSet rst = ps.executeQuery();

		Model mo = null;

		// 遍历这个结果集，并把结果方法放到对象里面。通过集合形式返回过去
		while (rst.next()) {// 怎么从这个对象里面获取到我们想要的数据呢，如果为true则说明里面有数据
			mo = new Model();
			mo.setId(rst.getInt("id"));
			mo.setUser_name(rst.getString("user_name"));
			mo.setAge(rst.getInt("age"));
			mo.setSex(rst.getInt("sex"));
			mo.setBirthday(rst.getDate("birthday"));
			// 在新增、修改的时候，传进来的是java.util.Date类型，需要转换成java.sql.Date类型，
			// 但往回转的时候，通过数据库把java.sql.Date类型转换成java.util.Date类型，就不需要再转了
			// 因为java.sql.Date类型是java.util.Date的一个子集
			mo.setEmail(rst.getString("email"));
			mo.setMobile(rst.getString("mobile"));
			mo.setCreate_date(rst.getDate("create_date"));
			mo.setCreate_user(rst.getString("create_user"));
			mo.setUpdate_date(rst.getDate("update_date"));
			mo.setUpdate_user(rst.getString("update_user"));
			mo.setIsdel(rst.getInt("isdel"));

			result.add(mo);
			// System.out.println(rs.getString("user_name") + "," +
			// rs.getInt("age"));
		}
		return result;
	}
	//当传入的参数变得多的时候，可以传入一个集合
	public List<Model> query(List<Map<String,Object>>params) throws Exception {
		List<Model> result = new ArrayList<Model>();

		Connection conn = DBUtil.getconnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from JDBC where 1 = 1");

		if(params!=null& params.size() > 0){
			for(int i = 0; i < params.size();i++){
				Map<String,Object>map = params.get(i);
				sb.append(" and " + map.get("name") + " " + map.get("rela") +" " + map.get("value"));
			}
		}
		// 2.预编译，然后把参数传进去
		PreparedStatement ps = conn.prepareStatement(sb.toString());
		System.out.println(sb.toString());
		// 3.执行查询
		ResultSet rst = ps.executeQuery();

		Model mo = null;

		// 遍历这个结果集，并把结果方法放到对象里面。通过集合形式返回过去
		while (rst.next()) {// 怎么从这个对象里面获取到我们想要的数据呢，如果为true则说明里面有数据
			mo = new Model();
			mo.setId(rst.getInt("id"));
			mo.setUser_name(rst.getString("user_name"));
			mo.setAge(rst.getInt("age"));
			mo.setSex(rst.getInt("sex"));
			mo.setBirthday(rst.getDate("birthday"));
			// 在新增、修改的时候，传进来的是java.util.Date类型，需要转换成java.sql.Date类型，
			// 但往回转的时候，通过数据库把java.sql.Date类型转换成java.util.Date类型，就不需要再转了
			// 因为java.sql.Date类型是java.util.Date的一个子集
			mo.setEmail(rst.getString("email"));
			mo.setMobile(rst.getString("mobile"));
			mo.setCreate_date(rst.getDate("create_date"));
			mo.setCreate_user(rst.getString("create_user"));
			mo.setUpdate_date(rst.getDate("update_date"));
			mo.setUpdate_user(rst.getString("update_user"));
			mo.setIsdel(rst.getInt("isdel"));

			result.add(mo);
			// System.out.println(rs.getString("user_name") + "," +
			// rs.getInt("age"));
		}
		return result;
	}

	public Model get(Integer id) throws SQLException {
		Model mo = null;
		// 1连接
		Connection conn = DBUtil.getconnection();

		// 2填写sql语句
		String sql = "select * from  jdbc" + " where id = ?";
		// 用？相当于占位符，再给这些参数赋值，这样执行的时候，这些参数再加载到sql语句，把sql语句添加完整再执行，这样就会减少对数据库的操作
		// 这里新的执行sql语句方法：这会把sql语句加载到驱动程序的执行程序中，但并不直接执行。

		// 3.预编译
		PreparedStatement ps = conn.prepareStatement(sql);// 预编译最后一句

		// 4.传参，给？赋值
		ps.setInt(1, id);

		// 5.执行
		// ps.execute();// 调用时才执行
		ResultSet rst = ps.executeQuery();
		while (rst.next()) {
			mo = new Model();
			mo.setId(rst.getInt("id"));
			mo.setUser_name(rst.getString("user_name"));
			mo.setAge(rst.getInt("age"));
			mo.setSex(rst.getInt("sex"));
			mo.setBirthday(rst.getDate("birthday"));
			// 在新增、修改的时候，传进来的是java.util.Date类型，需要转换成java.sql.Date类型，
			// 但往回转的时候，通过数据库把java.sql.Date类型转换成java.util.Date类型，就不需要再转了
			// 因为java.sql.Date类型是java.util.Date的一个子集
			mo.setEmail(rst.getString("email"));
			mo.setMobile(rst.getString("mobile"));
			mo.setCreate_date(rst.getDate("create_date"));
			mo.setCreate_user(rst.getString("create_user"));
			mo.setUpdate_date(rst.getDate("update_date"));
			mo.setUpdate_user(rst.getString("update_user"));
			mo.setIsdel(rst.getInt("isdel"));
		}
		return mo;
	}
}
