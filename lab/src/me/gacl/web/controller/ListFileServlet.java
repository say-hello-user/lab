package me.gacl.web.controller;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: ListFileServlet
 * @Description: �г�Webϵͳ�����������ļ�
 * @author: �ֽ���
 * @date: 2017-3-8
 * 
 */
public class ListFileServlet extends HttpServlet {
	static String sql = null;
	static DBHelper db1 = null;
	static ResultSet ret = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ�ϴ��ļ���Ŀ¼
		// String uploadFilePath =
		// this.getServletContext().getRealPath("/WEB-INF/upload");
		// �洢Ҫ���ص��ļ���
		// Map<String, String> fileNameMap = new HashMap<String, String>();

		// �ݹ����filepathĿ¼�µ������ļ���Ŀ¼�����ļ����ļ����洢��map������
		// listfile(new File(uploadFilePath), fileNameMap);//
		// File�ȿ��Դ���һ���ļ�Ҳ���Դ���һ��Ŀ¼
		// ��Map���Ϸ��͵�listfile.jspҳ�������ʾ
		// request.setAttribute("fileNameMap", fileNameMap);
		Map<String, String> mydata = new HashMap<String, String>();
		String id = request.getParameter("id");
		sql = "select * from libmatch where Id=" + id;// SQL���
		System.out.println(sql);
		db1 = new DBHelper(sql);// ����DBHelper����
		try {
			ret = db1.pst.executeQuery();
			if (ret.next()) {

				String title = ret.getString(2);
				String date = ret.getString(3);
				String content = ret.getString(4);
				String filePath = ret.getString(5);
				System.out.println(title + "\t" + date + "\t" + content + "\t"
						+ filePath);
				mydata.put("title", title);
				mydata.put("date", date);
				mydata.put("content", content);
				if (filePath.equals("���ϴ�����ļ�")) {
					request.setAttribute("hasFile", "���ϴ�����ļ�");
				} else {
					mydata.put("filePath", filePath);
					request.setAttribute("hasFile", "���ϴ��ļ�");
				}

				request.setAttribute("mydata", mydata);
				request.getRequestDispatcher("/listfile.jsp").forward(request,
						response);
			}// ��ʾ����
			else {
				request.setAttribute("message", "û�в�ѯ���ü�¼");
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);

			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// ִ����䣬�õ������

		// System.out.println("-------------------------"+fileNameMap);

	}

	/**
	 * @Method: listfile
	 * @Description: �ݹ����ָ��Ŀ¼�µ������ļ�
	 * @Anthor:�ֽ���
	 * @param file
	 *            ������һ���ļ���Ҳ����һ���ļ�Ŀ¼
	 * @param map
	 *            �洢�ļ�����Map����
	 */
	public void listfile(File file, Map<String, String> map) {
		// ���file����Ĳ���һ���ļ�������һ��Ŀ¼
		if (!file.isFile()) {
			// �г���Ŀ¼�µ������ļ���Ŀ¼
			File files[] = file.listFiles();
			// ����files[]����
			for (File f : files) {
				// �ݹ�
				listfile(f, map);
			}
		} else {
			/**
			 * �����ļ������ϴ�����ļ�����uuid_�ļ�������ʽȥ���������ģ�ȥ���ļ�����uuid_����
			 * file.getName().indexOf
			 * ("_")�����ַ����е�һ�γ���"_"�ַ���λ�ã�����ļ��������ڣ�9349249849-88343-8344_��_��_��.avi
			 * ��ôfile.getName().substring(file.getName().indexOf("_")+1)
			 * ����֮��Ϳ��Եõ���_��_��.avi����
			 */
			String realName = file.getName().substring(
					file.getName().indexOf("_") + 1);
			// file.getName()�õ������ļ���ԭʼ���ƣ����������Ψһ�ģ���˿�����Ϊkey��realName�Ǵ����������ƣ��п��ܻ��ظ�
			map.put(file.getName(), realName);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}