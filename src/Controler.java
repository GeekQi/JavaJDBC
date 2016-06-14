import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Controler {
	public void add(Model m) throws Exception {
		ModelEdit me = new ModelEdit();
		me.addModel(m);
	}

	public void edit(Model m) throws SQLException {
		ModelEdit me = new ModelEdit();
		me.updateModel(m);
	}

	public void del(Integer id) throws SQLException {
		ModelEdit me = new ModelEdit();
		me.delModel(id);
	}

	public List<Model> query() throws Exception {
		ModelEdit me = new ModelEdit();
		return me.query();
	}

	public List<Model> query(List<Map<String, Object>> params) throws Exception {
		ModelEdit me = new ModelEdit();
		return me.query(params);
	}

	public Model get(Integer id) throws SQLException {
		ModelEdit me = new ModelEdit();
		return me.get(id);
	}

}
