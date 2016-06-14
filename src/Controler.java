import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controler {
	public static void main(String[] args) throws Exception {
		ModelEdit ge = new ModelEdit();

		// List<Model> result = ge.query("Xia", "2332","xiao");
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("name", "user_name");
		// param.put("rela", "=");
		param.put("rela", "like");
		param.put("value", "'Xiaoxia'");
		params.add(param);

		param = new HashMap<String, Object>();

		param.put("name", "mobile");
		param.put("rela", "=");
		// param.put("rela", "like");
		param.put("value", "'1232332332'");
		params.add(param);

		List<Model> result = ge.query(params);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());

		}
		// List<Model> lge = ge.query();
		// for (Model model : lge) {
		// System.out.println(model.getUser_name() + "," + model.getAge());
		// }

		Model m1 = new Model();
		m1.setUser_name("小美");
		m1.setAge(17);
		m1.setSex(1);
		m1.setBirthday(new Date());
		m1.setEmail("xiaoxia@gmail.com");
		m1.setMobile("1232332332");
		m1.setCreate_user("geekqi");
		m1.setUpdate_user("geekqi");
		m1.setIsdel(1);

		// ge.addModel(m1);
		// m1.setId(4);
		// ge.updateModel(m1);
		// ge.delModel(5);
		// Model m2 = ge.get(6);
		// System.out.println(m2.toString());
	}
}
