package com.hugo.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.hugo.common.CommonConstants;
import com.mortennobel.imagescaling.ResampleOp;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;


public class FileUtil {
	
	protected static Log log = LogFactory.getLog(FileUtil.class);
	
	/**
	 * 缓存大小
	 */
	public static final int BUFFER_SIZE = 16 * 1024;
	
	/**
	 * 根目录
	 */

	
	/**
	 * 日期目录
	 * @return
	 */
	public static String getDatePath() {
		String now = DateUtil.today(DateUtil.DEFAULT_DATE_FORMAT);
		return File.separator + now.split("-")[0] +"-" + now.split("-")[1] + "-" + now.split("-")[2] + File.separator;
	}
	
	/**
	 * 返回文件在服务器上的路径
	 * @param request
	 * @return
	 */
	public static String getFilePath(HttpServletRequest request, String dir) {
		//服务器文件地址
        String basePath = System.getProperty("hugo.root");
        basePath = StringUtils.isBlank(basePath)?request.getSession().getServletContext().getRealPath(""):basePath;
        basePath+=File.separator+ CommonConstants.UPLOAD_ROOT;
        if(StringUtils.isNoneBlank(dir))basePath+=File.separator+dir;
		//日期文件夹
		String datePath = getDatePath();
		// 判断文件是否存在
		File filePath = new File(basePath + datePath);
		//不存在创建
		if (!filePath.exists()) {   
			filePath.mkdirs();   
		} 
		return filePath.getPath();
	}
	
	/**
	 * spring3 MVC 上传文件
	 * @param request
	 * @param upload
	 * @return
	 */
	public static String uploadFile(HttpServletRequest request, MultipartFile upload) {
		InputStream is = null;
		OutputStream os = null;
		try {
			//文件目录
			String path = getFilePath(request, null);
			//文件名称
			//String fileName = getRandName(upload.getOriginalFilename());
            String fileName = upload.getOriginalFilename();
			//第一种：拷贝图片
            //FileCopyUtils.copy(upload.getBytes(), new File(filePath, fileName)); 
            
            //第二种：以流的方式写入
            is = new BufferedInputStream(upload.getInputStream(), BUFFER_SIZE);
            // 建立一个上传文件的输出流
            os = new FileOutputStream(path + File.separator + fileName);
			int len = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((len = is.read(buffer)) > 0) {
				// 将文件写入服务器
				os.write(buffer, 0, len);
			}
            return path + File.separator + fileName;
        } catch (Exception e) {
        	e.printStackTrace();
        	log.error("图片上传失败：", e);
        	return null;
        } finally {
        	if(null != os) {
        		try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if(null != is) {
        		try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
	}
	
	/**
	 * 普通的上传文件
	 * @param request
	 * @param srcFile
	 * @param fileName
	 * @return
	 */
	public static String uploadFile(HttpServletRequest request, File srcFile, String fileName) {
		InputStream is = null;
		OutputStream os = null;
		try {
			//文件目录
			String path = getFilePath(request, null);
			//以流的方式写入
            is = new BufferedInputStream(new FileInputStream(srcFile), BUFFER_SIZE);
            // 建立一个上传文件的输出流
			os = new FileOutputStream(path + File.separator + fileName);
			int len = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((len = is.read(buffer)) > 0) {
				// 将文件写入服务器
				os.write(buffer, 0, len);
			}
			
			return path + File.separator + fileName;
        } catch (Exception e) {
        	e.printStackTrace();
        	log.error("图片上传失败：", e);
        	return null;
        } finally {
        	if(null != os) {
        		try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if(null != is) {
        		try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
	}

	/**
	 * 等比例缩放图片
	 * @param srcFile 原图片
	 * @param width 宽
	 * @param height 高
	 * @return 服务器上图片的路径
	 */
	public static String scaleImage(HttpServletRequest request, File srcFile, int width, int height) {
		try {
			//文件目录
			String path = getFilePath(request, null);
			//获取文件名
			String fileName = getRandName(srcFile.getName());
			//获取文件后缀
			String fileType = getFileType(srcFile.getName());
			
			BufferedImage inputBufImage = ImageIO.read(srcFile);
			ResampleOp resampleOp = new ResampleOp(width, height);
			BufferedImage rescaledTomato = resampleOp.filter(inputBufImage, null);
			ImageIO.write(rescaledTomato, fileType, new File(path + File.separator + fileName));
			
			return path + File.separator + fileName;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("图片上传失败：", e);
        	return null;
		}
	}
	
	/**
	 * 文件拷贝
	 * @param src
	 * @param dst
	 */
	public static void copyFile(File src, File dst) {
		InputStream is = null;
		OutputStream out = null;
		try {
			is = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = is.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("图片拷贝失败：", e);
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("111:"+getFileName("111.jpg"));
	}
	
	
	/**
	 * 获取文件名后缀
	 * @param fileName
	 * @return
	 */
	public static String getFileType(String fileName){
		if(null != fileName && !"".equals(fileName)) {
			int index = 0;
			if((index = fileName.lastIndexOf(".")) != -1) {
				return fileName.substring(index + 1).toLowerCase();
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public static int i = 0;
	
	/**
	 * 获取随机文件名
	 * @param fileName
	 * @return
	 */
	public static String getRandName(String fileName){
		String type = "jpg";
		if(StringUtils.isNotBlank(fileName)) {
			type = getFileType(fileName);
		}
		//确保图片名字的唯一性
		if(i > 10000){
			i = 0;
		}
		return System.currentTimeMillis()+(i++)+"."+type;
	}

	/**
	 * 获取文件名
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		if(null != fileName && !"".equals(fileName)) {
			int index = 0;
			if((index = fileName.lastIndexOf(".")) != -1) {
				return fileName.substring(0, index);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * 获取文件大小
	 * @param size
	 * @return
	 */
	public static String getFileSize(long size) {
		DecimalFormat df = new DecimalFormat("#0.0");
		if (size < 1024) {
			return df.format((double) size) + "B";
		} else if (size < 1048576) {
			return df.format((double) size / 1024) + "K";
		} else if (size < 1073741824) {
			return df.format((double) size / 1048576) + "M";
		} else {
			return df.format((double) size / 1073741824) + "G";
		}
	}
	
	/**
	 * 获取图片的像素
	 * @param file
	 * @return
	 */
	public static String getImageWH(File file) {
		try {
			Image src = ImageIO.read(file);
			int w = src.getWidth(null);
			int h = src.getHeight(null);
			return w + " x " + h;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取图片的像素
	 * @param is
	 * @return
	 */
	public static String getImageWH(InputStream is) {
		try {
			Image src = ImageIO.read(is);
			int w = src.getWidth(null);
			int h = src.getHeight(null);
			return w + " x " + h;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

    public static void mkDirs(String path){
        File file = new File(path);
        if(!file.exists())file.mkdirs();
    }

    public static String getContentType(String fileName) {
        Path path = Paths.get(fileName);
        String contentType = "image/jpeg";
        try {
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentType;
    }


}
