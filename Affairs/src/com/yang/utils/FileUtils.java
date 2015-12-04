package com.yang.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;


/**
 * java文件类的相关操作
 * 
 * @author Yang
 * 
 */
public class FileUtils {
	/**
	 * 创建一个文件夹
	 * @return true for success,false for fail
	 */
	public static boolean createDir(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			return dir.mkdir();
		}
		return true;
	}

	/**
	 * 删除文件夹
	 */
	public static void deleteDir(String path) {

	}

	/**
	 * 更改文件夹名字
	 */
	public static void renameDir(String path, String newName) {

	}

	/**
	 * 查找文件夹
	 */
	public static File findDir(String path) {
		return null;
	}

	/**
	 * 创建一个空文件文件
	 * @return boolean 表示成功与否
	 */
	public static boolean createFile(String path, String name) {
		createDir(path);
		File f = new File(path, name);
		if (!f.exists()) {
			try {
				return f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
				L.d("createFile-文件创建失败",methodName, e);
			}
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 删除文件
	 */
	public static void deleteFile(String path, String name) {
		File file = new File(path + "/" + name);
		if (file.exists() && file.isFile())
			file.delete();
	}

	/**
	 * 更改文件名字
	 */
	public static void renameFile(String path, String name, String newName) {

	}

	/**
	 * 查找文件
	 */
	public static File findFile(String path, String name) {
		return null;
	}

	/**
	 * 向指定文件中写入一个字符串
	 * 
	 * @param path eg:D:/test.txt
	 */
	public static void writeText(String path, String content) {
		PrintStream p=null;
		try {
			FileOutputStream out = new FileOutputStream(path);
			p = new PrintStream(out);
			p.println(content);
			// StringBuffer sb=new StringBuffer();
			// sb.append("这是第"+i+"行:前面介绍的各种方法都不关用,为什么总是奇怪的问题 ");
			// out.write(sb.toString().getBytes("utf-8"));
			// 该方法可以设定使用何种编码，有效解决中文问题。
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
			L.d("文件未找到",methodName, e);
		}finally{
			if(p!=null){
				p.close();
			}
		}
	}

	/**
	 * 将指定txt文件中的数据读到string中
	 */
	public static String readText(String path) {
		File file = new File(path);
		if (!file.exists() || file.isDirectory()) {
			L.d("文件未找到");
			return null;
		}
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(file);
			byte[] buf = new byte[1024];
			int len;
			StringBuffer sb = new StringBuffer();
			while ((len=fis.read(buf)) != -1) {
				sb.append(new String(buf,0,len));
//				buf = new byte[1024];// 重新生成，避免和上次读取的数据重复
			}
			return sb.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
			L.d("文件未找到",methodName, e);
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
			L.d("io错误",methodName, e);
			return null;
		}finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
					L.d("FileInputStream未能关闭",methodName, e);
				}
			}
		}
	}
	/**
	 * 没有测试
	 * @param path
	 * @param bytes
	 */
	public static void  writeBytes(String path,byte[] bytes){
		FileOutputStream out=null;
		try {
			 out = new FileOutputStream(path);
			out.write(bytes);
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
			L.d("文件未找到",methodName, e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
			L.d("io异常",methodName, e);
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
					L.d("fileoutputstream无法关闭 ",methodName, e);
				}
			}
		}
		
	}
	
	
}
