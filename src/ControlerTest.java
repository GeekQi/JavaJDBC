import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlerTest {
	public static void main(String[] args) throws Exception {
		Controler co = new Controler();
		Model m = new Model();
		m.setUser_name("小琦2");
		m.setSex(1);
		m.setAge(24);
		m.setBirthday(new Date());
		m.setEmail("xiaoqi@163.com");
		m.setMobile("1882384783");
		m.setIsdel(0);
		m.setId(6);
		
//		co.add(m);
//		co.edit(m);
//		co.del(6);
		List<Map<String, Object>> params = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name","user_name");
		map.put("rela", "=");
		map.put("value", "'小琦'");
		
		params.add(map);
		/*查询*/
		List<Model> result = co.query(params);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getId() + ":" + result.get(i).getUser_name());
		}/**/
	}
}

