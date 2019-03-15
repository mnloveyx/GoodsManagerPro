package com.amuse.util;

import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;



/**
 *@description	文件、IO流工具类
 *
 */

public class IOUtils {
	public final static int		BUFFER_LENGTH	= 1024 * 20;	// 20K
	public final static String	DOT				= ".";			//
	public final static String	SPRIT			= "/";			//
	
//	public final static String 	PDF_TYPE  		= ".pdf";		// pdf 文件类型
//	public final static String 	TEXT_TYPE  		= ".txt";		// txt 文件类型
//	public final static String 	ZIP_TYPE  		= ".zip";		// zip 文件类型
	
//	public final static String 	TEMP_FILE_TYPE  = ".~temp";		// 临时文件类型
	
	public static class NestedIOException extends RuntimeException {
		private static final long serialVersionUID = -5699685710695040493L;
		
		public NestedIOException(String message, Exception e) {
			super(message, e);
		}
		
		public NestedIOException(Exception e) {
			super(e);
		}
	}
	
	/**
     * 创建目录，如果是文件，则创建父目录
     * @param file
     * @return
     */
    public static String mkdirs(String file) {
        return mkdirs(new File(file));
    }
	
	/**
	 * 创建目录，如果是文件，则创建父目录
	 * @param file
	 * @return
	 */
	public static String mkdirs(File file) {
	    if (file.isDirectory()) {
	        file.mkdirs();
	    } else {
	        file.getParentFile().mkdirs();
	    }
	    return file.getAbsolutePath();
	}
	
	/**
	 * 返回文件格式 <b> .xxx </b>
	 * @param fileName
	 * @return
	 */
	public static String getFileType(String fileName) {
		return fileName.substring(fileName.lastIndexOf(DOT));
	}
	
	/**
	 * 输入流写入输出流
	 * @param in
	 * @param out
	 * @param bufferLength
	 * @throws IOException
	 */
	public static void write(InputStream in, OutputStream out, int bufferLength) throws IOException {
		byte[] buffer = new byte[bufferLength];
		int len;
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		out.flush();
	}
	
	/**
	 * 写入图片文件字节，并关闭输出流 (该方法会自动关闭输出流，若不需要关闭，请使用 <pre>javax.imageio.ImageIO#write</pre> 方法)
	 * 
	 * @param im
	 * @param formatName
	 * @param output
	 * 
	 * @see javax.imageio.ImageIO#write
	 */
	public static void write(RenderedImage im, String formatName, OutputStream output) {
		try {
			ImageIO.write(im, formatName, output);
			output.flush();
		} catch (IOException e) {
			throw new NestedIOException("write image stream error.", e);
		} finally {
			close(output);
		}
	}
	/**
	 * 读取字符（自动关闭InputStream in）
	 * @param in
	 * @return
	 */
	public static String readAsString(InputStream in, Charset charset) {
		BufferedReader reader = null;
		try {
			StringBuilder result = new StringBuilder();
			reader = new BufferedReader(newInputStreamReader(in, charset));
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} catch (IOException e) {
			throw new NestedIOException("write image stream error.", e);
		} finally {
			close(reader);
		}
	}
	
	/**
	 * @param in
	 * @return
	 */
	public static String readAsUTF8String(InputStream in) {
		
		return readAsString(in, StandardCharsets.UTF_8);
	}
	
	public static Reader newInputStreamReader(InputStream in, Charset charset) throws IOException {
		
		return charset == null ? new InputStreamReader(in) : new InputStreamReader(in, charset);
	}
	
	/**
	 * 关闭，错误时抛出异常
	 * @param stream
	 * @throws NestedIOException
	 */
	public static void close(Closeable... stream) throws NestedIOException {
		if (stream == null || stream.length == 0) return;
		try {
			for (Closeable c : stream) {
				if (c!=null)c.close();
			}
		} catch (IOException e) {
			throw new NestedIOException("close stream error.", e);
		}
	}
	
	/**
	 * 关闭，错误时忽略异常
	 * @param stream
	 */
	public static void closeQuietly(Closeable... stream) {
	    if (stream == null || stream.length == 0) return;
	    try {
	        for (Closeable c : stream) {
	            if (c!=null)c.close();
	        }
	    } catch (IOException ignore) {
	    }
	}
	
}
