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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(CONTEXT);
		//怎么保持程序一直运行
		
		Scanner scan = new Scanner(System.in);
		
		while(scan.hasNext()){
			String in = scan.next().toString();
			if(OPERATION_EXIT.equals(in.toUpperCase())
					||OPERATION_EXIT.substring(0,1).equals(in.toUpperCase())){
				System.out.println("您已经退出了");
				break;
			}
			System.out.println("您输入的值为：" + in);
		}
	}

}
