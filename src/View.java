import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class View {

	private static final String CONTEXT="欢迎来到：\n" +
			"以下是功能列表：\n" +
			"[MAIN/M]:主菜单：\n" +
			"[QUERY/Q]:查看全部成员信息\n" +
			"[GET/G]:查看某个人的信息\n" +
			"[ADD/A]:添加个人信息\n" +
			"[UPDATE/U]:更新某人的信息\n" +
			"[DELETE/D]:删除某人的信息\n" +
			"[SEARCH/S]:查询某人的信息（根据姓名、手机号查询）\n" +
			"[EXIT/E]:退出\n" +
			"[BREAK/B]:退出当前功能，返回主菜单";
	
	//上边是提示语，下面是操作标记
	private static final String OPERATION_MAIN="MAIN";
	private static final String OPERATION_QUERY="QUERY";
	private static final String OPERATION_GET="GET";
	private static final String OPERATION_ADD="ADD";
	private static final String OPERATION_UPDATE="UPDATE";
	private static final String OPERATION_DELETE="DELETE";
	private static final String OPERATION_SEARCH="SEARCH";
	private static final String OPERATION_EXIT="EXIT";
	private static final String OPERATION_BREAK="BREAK";

	public static void main(String[] args) throws NullPointerException {
		// TODO Auto-generated method stub
		System.out.println(CONTEXT);
		//怎么保持程序一直运行
		
		Scanner scan = new Scanner(System.in);
		Model m = new Model();
		Controler co = new Controler();
		String prenious = null;
		Integer step = 1;
		while(scan.hasNext()){
			String in = scan.next().toString();
			if(OPERATION_QUERY.equals(in.toUpperCase())
					||OPERATION_QUERY.substring(0,1).equals(in.toUpperCase())){

				try {
					List<Model> list = co.query();
					for(Model mo :list){
						System.out.println(mo.getId() + "姓名：" + mo.getUser_name());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(OPERATION_EXIT.equals(in.toUpperCase())
					||OPERATION_EXIT.substring(0,1).equals(in.toUpperCase())){
				System.out.println("您已经退出了");
				break;
			}
			else if(OPERATION_ADD.equals(in.toUpperCase())
					||OPERATION_ADD.substring(0,1).equals(in.toUpperCase())
					||OPERATION_ADD.equals(prenious)){
				prenious = OPERATION_ADD;
				//新增个人信息
				if(1== step){
					System.out.println("请输入该人［姓名］");
					
				}else if(2==step){
					m.setUser_name(in);
					System.out.println("请输入该人［性别］");
				}else if(3==step){
					m.setSex(Integer.valueOf(in));
					System.out.println("请输入该人［年龄］");
				}else if(4==step){
					m.setAge(Integer.valueOf(in));
					System.out.println("请输入该人［生日］，格式如：yyyy-MM-dd");
				}else if(5==step){
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					Date birthday = null;
					try {
						birthday = sf.parse(in);
						m.setBirthday(birthday);
						System.out.println("请输入该人［邮箱］");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("您输入的格式有误");
						step = 4;
					}
				}else if(6==step){
					m.setEmail(in);
					System.out.println("请输入该人［手机号］");
				}else if(7==step){
					m.setMobile(in);
					
					try {
						co.add(m);
						System.out.println("新增联系人成功");
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("新增联系人失败");
					} 
				}
				if(OPERATION_ADD.equals(prenious)){
					
					step++;
				}
				
//				System.out.println("输入的姓名为" + in);
			}
			else{
				System.out.println("您输入的值为：" + in);
				
			}
		}
	}

}
