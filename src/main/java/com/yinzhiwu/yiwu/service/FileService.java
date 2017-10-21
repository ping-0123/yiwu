package com.yinzhiwu.yiwu.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author ping
 * @Date 2017年10月19日 下午8:58:54
 *
 */
public interface FileService {
	
	/**
	 * 上传文件
	 * @param file
	 * @return file uri(name),文件的保存名, 通过{@link FileService#getFileUrl(String)} 获取访问文件的url
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public String upload(MultipartFile file) throws IllegalStateException, IOException;
	
	/**
	 * 
	 * @param uri
	 * @return 文件的访问url
	 */
	public String getFileUrl(String uri);
	
	
	/**
	 * 同 {@link #getFileUrl(String)}
	 * @param uri
	 * @return image url, 如果uri为空，则返回一个默认的图片url 
	 */
	public String getImageUrl(String uri);
	
	/**
	 * 从文件服务器上删除文件
	 * @param fileName 被删除的文件名
	 * @return 删除成功返回true, 否则false
	 */
	public boolean delete(String fileName);
	
	/**
	 * 生成访问文件服务器的口令
	 * @return 口令
	 */
	public String createAccessToken();
}
