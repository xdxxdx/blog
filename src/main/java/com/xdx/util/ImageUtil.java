package com.xdx.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.mortennobel.imagescaling.DimensionConstrain;
import com.mortennobel.imagescaling.ResampleOp;

public class ImageUtil {
	public final static int SCALE = 10;
	public final static int WIDTH = 750;// L
	public final static int HEIGHT = 750;//
	public final static int WIDTH1 = 750;
	public final static int WIDTH2 = 300;
	public final static int WIDTH3 = 150;
	public final static int WIDTH4 = 70;
	public final static String GIF = "gif";
	public final static String PNG = "png";
	public final static String JPG = "jpg";
	private final static int BUFFER_SIZE = 16 * 1024;

	// 测试
	public static void main(String args[]) {
		// InputStream in=getInputStreamByPath("E:/pic/1487926813064.png");
		InputStream in = getInputStreamByUrl("http://www.wonyen.com/uploadFiles/CommodityDisplayPhoto/1501038982929.jpg");
		try {
			InputStream compressIn = getThumbnail(in, "png", WIDTH4, WIDTH4);
			File toFile = new File("E:/pic/test.png");
			BufferedOutputStream out = null;
			try {
				out = new BufferedOutputStream(new FileOutputStream(toFile),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int len = 0;
				try {
					while ((len = compressIn.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (null != compressIn) {
					try {
						compressIn.close();
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 生成图片文件
	public static void generatePic(InputStream in, String path) {
		try {
			File toFile = new File(path);
			BufferedOutputStream out = null;
			try {
				out = new BufferedOutputStream(new FileOutputStream(toFile),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int len = 0;
				try {
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (null != in) {
					try {
						in.close();
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static byte[] getImageByUrl(URI uri) {
		File file = new File(uri);
		if (file.exists() && file.isFile()) {
			return file2byte(file);
		}
		return null;
	}

	// 根据url来获取图片输入流
	public static InputStream getInputStreamByUrl(String url) {
		InputStream inStream = null;
		HttpURLConnection conn = null;
		try {
			URL userFaceUrl = new URL(url);
			conn = (HttpURLConnection) userFaceUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			inStream = conn.getInputStream();// 通过输入流获取图片数据
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inStream != null)
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return inStream;
	}

	/**
	 * 根据路径path获取文件字节码
	 * 
	 * @param path
	 * @return
	 */
	public static byte[] getImageByPath(String path) {
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			return file2byte(file);
		}
		return null;
	}

	// 根据path获取输入流
	public static InputStream getInputStreamByPath(String path) {
		File file = new File(path);
		FileInputStream fis = null;
		if (file.exists() && file.isFile()) {
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return fis;
	}

	/**
	 * 将文件转换为字节流
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] file2byte(File file) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	/**
	 * 按默认比例缩放图片。
	 *
	 * @param src
	 *            源图片文件流
	 * @param fileSuffix
	 *            后缀名
	 * @return 缩略图文件流
	 */
	public static InputStream getThumbnailByDefaultScale(InputStream src,
			String fileSuffix) throws Exception {

		return getThumbnailByScale(src, fileSuffix, SCALE);

	}

	/**
	 * 按比例缩放图片。
	 *
	 * @param src
	 *            源图片文件流
	 * @param fileSuffix
	 *            后缀名
	 * @param scale
	 *            图片缩放比例
	 * @return 缩略图文件流
	 */
	public static InputStream getThumbnailByScale(InputStream src,
			String fileSuffix, int scale) throws Exception {

		if (GIF.equals(fileSuffix.toLowerCase())) {
			return src;
		}
		InputStream is = null;
		try {
			BufferedImage image = ImageIO.read(src);
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			int w = width / scale;
			int h = height / scale;

			ResampleOp rsop = new ResampleOp(
					DimensionConstrain.createMaxDimension(w, h, true));
			BufferedImage to = rsop.filter(image, null);

			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(to, fileSuffix, imOut);

			is = new ByteArrayInputStream(bs.toByteArray());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return is;
	}

	/**
	 * 按默认宽度高度缩放。
	 *
	 * @param src
	 *            源图片文件流
	 * @param fileSuffix
	 *            后缀名
	 * @return 缩略图文件流
	 */
	public static InputStream getThumbnail(InputStream src, String fileSuffix)
			throws Exception {

		return compressImage(src, fileSuffix, WIDTH, HEIGHT);

	}

	/**
	 * 指定宽度高度缩放。<br>
	 * 图片的实际宽高不足时返回null
	 * 
	 * @param src
	 *            源图片文件流
	 * @param fileSuffix
	 *            后缀名
	 * @param _width
	 *            缩略图宽度
	 * @param _height
	 *            缩略图高度
	 * @return 缩略图文件流
	 */
	public static InputStream getThumbnail(InputStream src, String fileSuffix,
			int _width, int _height) throws Exception {

		if (GIF.equals(fileSuffix.toLowerCase())) {
			return src;
		}
		InputStream is = null;
		try {
			BufferedImage bi2 = ImageIO.read(src);
			// 原图宽高
			int width = bi2.getWidth(null);
			int height = bi2.getHeight(null);
			if (width < _width || height < _height) {
				return null;
			}
			// 缩放后宽高
			int newWidth = 0;
			int newHeight = 0;
			if (width <= _width && height <= _height) {
				_width = width;
				_height = height;
			}
			// 计算按原图的横向纵向最大比例方向缩放
			// 横向图片的场合
			if (width / _width > height / _height) {
				newWidth = _width;
				newHeight = _width * height / width;
			} else {
				newHeight = _height;
				newWidth = _height * width / height;
			}

			ResampleOp rsop = new ResampleOp(
					DimensionConstrain.createMaxDimension(newWidth, newHeight,
							true));
			BufferedImage to = rsop.filter(bi2, null);

			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(to, fileSuffix, imOut);

			is = new ByteArrayInputStream(bs.toByteArray());

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			// 关于原来的流
			if (src != null) {
				src.close();
			}
		}
		return is;

	}

	/**
	 * 根据原图压缩为系统允许的最大的尺寸的图片。
	 *
	 * @param src
	 *            源图片文件流
	 * @param fileSuffix
	 *            后缀名
	 * @return 缩略图文件流
	 */
	public static InputStream compressMaxImage(InputStream src,
			String fileSuffix, int maxWidth) throws Exception {

		return compressImage(src, fileSuffix, maxWidth);

	}

	/**
	 * 固定图片宽高缩放（按原图的横向纵向最大比例方向缩放）。
	 *
	 * @param src
	 *            源图片文件流
	 * @param fileSuffix
	 *            后缀名
	 * @param _width
	 *            缩略图宽度
	 * @return 缩略图文件流
	 */
	public static InputStream compressImage(InputStream src, String fileSuffix,
			int _width, int _height) throws Exception {

		if (GIF.equals(fileSuffix.toLowerCase())) {
			return src;
		}
		InputStream is = null;
		try {
			BufferedImage bi2 = ImageIO.read(src);
			// 原图宽高
			int width = bi2.getWidth(null);
			int height = bi2.getHeight(null);
			// 缩放后宽高
			int newWidth = 0;
			int newHeight = 0;
			if (width <= _width && height <= _height) {
				_width = width;
				_height = height;
			}
			// 计算按原图的横向纵向最大比例方向缩放
			// 横向图片的场合
			if (width / _width > height / _height) {
				newWidth = _width;
				newHeight = _width * height / width;
			} else {
				newHeight = _height;
				newWidth = _height * width / height;
			}

			ResampleOp rsop = new ResampleOp(
					DimensionConstrain.createMaxDimension(newWidth, newHeight,
							true));
			BufferedImage to = rsop.filter(bi2, null);

			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(to, fileSuffix, imOut);

			is = new ByteArrayInputStream(bs.toByteArray());
			// 关于原来的流
			if (src != null) {
				src.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return is;

	}

	/**
	 * 固定图片宽度缩放。
	 *
	 * @param src
	 *            源图片文件流
	 * @param fileSuffix
	 *            后缀名
	 * @param _width
	 *            缩略图宽度
	 * @return 缩略图文件流
	 */
	public static InputStream compressImage(InputStream src, String fileSuffix,
			int _width) throws Exception {

		if (GIF.equals(fileSuffix.toLowerCase())) {
			return src;
		}
		InputStream is = null;
		try {
			BufferedImage bi2 = ImageIO.read(src);
			// 原图宽高
			int width = bi2.getWidth(null);
			int height = bi2.getHeight(null);
			// 缩放后宽高
			int newWidth = 0;
			int newHeight = 0;
			if (width < _width) {
				_width = width;
			}
			newWidth = _width;
			// 计算按原图的横向纵向最大比例方向缩放
			// 横向图片的场合
			newHeight = height * _width / width;

			ResampleOp rsop = new ResampleOp(
					DimensionConstrain.createMaxDimension(newWidth, newHeight,
							true));
			BufferedImage to = rsop.filter(bi2, null);

			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(to, fileSuffix, imOut);

			is = new ByteArrayInputStream(bs.toByteArray());
			// 关于原来的流
			if (src != null) {
				src.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return is;

	}

}
