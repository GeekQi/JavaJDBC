import java.util.Date;
import java.util.List;

public class Controler {
	public static void main(String[] args) throws Exception {
		ModelEdit ge = new ModelEdit();

//		List<Model> lge = ge.query();
//		for (Model model : lge) {
//			System.out.println(model.getUser_name() + "," + model.getAge());
//		}

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
		
//		ge.addModel(m1);
//		m1.setId(4);
//		ge.updateModel(m1);
//		ge.delModel(5);
		Model m2 = ge.get(6);
		System.out.println(m2.toString());
	}
}
