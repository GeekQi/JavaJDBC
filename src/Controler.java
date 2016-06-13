import java.util.List;

public class Controler {
	public static void main(String[] args) throws Exception {
		ModelEdit ge = new ModelEdit();

		List<Model> lge = ge.query();
		for (Model model : lge) {
			System.out.println(model.getUser_name() + "," + model.getAge());
		}

	}
}
