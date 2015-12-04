package com.yang.utils;

import java.io.File;
import java.io.IOException;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;

/**
 * SD卡相关的辅助类
 * 
 * @author zhy
 * 
 */
public class SDCardUtils
{
	private SDCardUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 判断SDCard是否可用
	 * 
	 * @return eg.true
	 */
	public static boolean isSDCardEnable()
	{
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);

	}

	/**
	 * 获取SD卡路径
	 * 
	 * @return eg./storage/emulated/0/
	 */
	public static String getSDCardPath()
	{
//		return Environment.getExternalStorageDirectory().getAbsolutePath()
//				;
		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator;
	}

	/**
	 * 获取SD卡的剩余容量 单位byte
	 * 
	 * @return eg.116384004797
	 */
	public static long getSDCardAllSize()
	{
		if (isSDCardEnable())
		{
			StatFs stat = new StatFs(getSDCardPath());
			// 获取空闲的数据块的数量
			long availableBlocks = (long) stat.getAvailableBlocks() - 4;
			// 获取单个数据块的大小（byte）
			long freeBlocks = stat.getAvailableBlocks();
			return freeBlocks * availableBlocks;
		}
		return 0;
	}

	/**
	 * 获取指定路径所在空间的剩余可用容量字节数，单位byte
	 * 
	 * @param filePath
	 * @return 容量字节 SDCard可用空间，内部存储可用空间
	 */
	public static long getFreeBytes(String filePath)
	{
		// 如果是sd卡的下的路径，则获取sd卡可用容量
		if (filePath.startsWith(getSDCardPath()))
		{
			filePath = getSDCardPath();
		} else
		{// 如果是内部存储的路径，则获取内存存储的可用容量
			filePath = Environment.getDataDirectory().getAbsolutePath();
		}
		StatFs stat = new StatFs(filePath);
		long availableBlocks = (long) stat.getAvailableBlocks() - 4;
		return stat.getBlockSize() * availableBlocks;
	}

	/**
	 * 获取系统存储路径
	 * 
	 * @return eg.system
	 */
	public static String getRootDirectoryPath()
	{
		return Environment.getRootDirectory().getAbsolutePath();
	}
	/**
	 * 在sd卡中创建一个目录，测试成功
	 * @param folder 文件夹的名字，eg:info
	 */
	public static boolean createFolder(String folder){
		if(isSDCardEnable()){
			return FileUtils.createDir(getSDCardPath()+folder);
		}else{
			return false;
		}
	}
	/**
	 * 在sd卡中创建一个空文件,测试成功
	 * @param folder 文件夹的名字，eg:info
	 * @throws IOException 
	 */
	public static boolean  createFile(String folder,String fileName) {
		if(isSDCardEnable()){
			return FileUtils.createFile(getSDCardPath()+folder,fileName);
		}else{
			return false;
		}
	}
	/**
	 * 适用于txt
	 * 将content写入sd卡中的指定文件之下
	 * 测试成功
	 * @param folder 文件夹名
	 * @param fileName 文件名
	 * @param content  文件内容
	 */
	public static void writeText(String folder,String fileName,String content){
		String path=getSDCardPath()+folder+File.separator+fileName;
		FileUtils.writeText(path, content);
	}
	/**
	 * 适用于txt
	 * 测试成功
	 * @param folder
	 * @param fileName
	 * @return 文档的内容
	 */
	public static String readText(String folder,String fileName){
		String path=getSDCardPath()+folder+File.separator+fileName;
		String content=FileUtils.readText(path);
		return content;
	}
	public static void writeImage(String folder,String fileName,Bitmap bitmap){
		
	}
}
