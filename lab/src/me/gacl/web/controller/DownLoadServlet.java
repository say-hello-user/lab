package me.gacl.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �õ�Ҫ���ص��ļ���
		String fileName = request.getParameter("filename");
		fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
		// �ϴ����ļ����Ǳ�����/WEB-INF/uploadĿ¼�µ���Ŀ¼����
		String fileSaveRootPath = this.getServletContext().getRealPath(
				"/WEB-INF/upload");
		// ͨ���ļ����ҳ��ļ�������Ŀ¼
		String path = fileSaveRootPath;
		// �õ�Ҫ���ص��ļ�
		File file = new File(path + "\\" + fileName);
		// System.out.println(path + "\\" + fileName);
		// ����ļ�������
		if (!file.exists()) {
			request.setAttribute("message", "��Ҫ���ص���Դ�ѱ�ɾ������");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
			return;
		}
		// �����ļ���
		String realname = fileName.substring(fileName.indexOf("_") + 1);
		// ������Ӧͷ��������������ظ��ļ�
		response.setHeader("content-disposition", "attachment;filename="
				+ URLEncoder.encode(realname, "UTF-8"));
		// ��ȡҪ���ص��ļ������浽�ļ�������
		FileInputStream in = new FileInputStream(path + "\\" + fileName);
		// ���������
		OutputStream out = response.getOutputStream();
		// ����������
		byte buffer[] = new byte[1024];
		int len = 0;
		// ѭ�����������е����ݶ�ȡ������������
		while ((len = in.read(buffer)) > 0) {
			// ��������������ݵ��������ʵ���ļ�����
			out.write(buffer, 0, len);
		}
		// �ر��ļ�������
		in.close();
		// �ر������
		out.close();
	}

	/**
	 * @Method: findFileSavePathByFileName
	 * @Description: ͨ���ļ����ʹ洢�ϴ��ļ���Ŀ¼�ҳ�Ҫ���ص��ļ�������·��
	 * @Anthor:�ֽ���
	 * @param filename
	 *            Ҫ���ص��ļ���
	 * @param saveRootPath
	 *            �ϴ��ļ�����ĸ�Ŀ¼��Ҳ����/WEB-INF/uploadĿ¼
	 * @return Ҫ���ص��ļ��Ĵ洢Ŀ¼
	 */
	public String findFileSavePathByFileName(String filename,
			String saveRootPath) {
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		String dir = saveRootPath + "\\" + dir1 + "\\" + dir2; // upload\2\3
																// upload\3\5
		File file = new File(dir);
		if (!file.exists()) {
			// ����Ŀ¼
			file.mkdirs();
		}
		return dir;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}