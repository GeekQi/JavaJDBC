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
		m1.setUser_name("XiaoXia");
		m1.setAge(20);
		m1.setSex(1);
		m1.setBirthday(new Date());
		m1.setEmail("xiaoxia@gmail.com");
		m1.setMobile("18810392839");
		m1.setCreate_user("geekqi");
		m1.setUpdate_user("geekqi");
		m1.setIsdel(1);
		
		ge.addModel(m1);
	}
}
