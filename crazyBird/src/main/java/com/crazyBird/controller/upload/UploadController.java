package com.crazyBird.controller.upload;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.crazyBird.exception.UploadException;
import com.crazyBird.controller.upload.model.MultiPicUploadModel;
import com.crazyBird.controller.upload.param.UploadPicParam;



/**
 * 图片上传
 * @author zzc
 *
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
	@Autowired
	private UploadProcess uploadProcess;
	/**
	 * 图片上传通用接口 支持批量
	 * @param request
	 * @param uploadParam
	 * @param files
	 * @return
	 * @throws UploadException
	 * @throws IOException
	 */
	@RequestMapping(value = "/pic", method=RequestMethod.POST)
	@ResponseBody
	public MultiPicUploadModel doUploadPic(HttpServletRequest request, UploadPicParam uploadParam,
			@RequestParam(value = "files", required = false) MultipartFile[] files) throws UploadException, IOException {
		return uploadProcess.doUploadPic(files, uploadParam);
	}
	/**
	 * 图片上传单张
	 * @param request
	 * @param uploadParam
	 * @param files
	 * @return
	 * @throws UploadException
	 * @throws IOException
	 */
	@RequestMapping(value = "/avatar", method=RequestMethod.POST)
	@ResponseBody
	public MultiPicUploadModel doUploadAvr(HttpServletRequest request, UploadPicParam uploadParam,
			@RequestParam(value = "file", required = false) MultipartFile file) throws UploadException, IOException {
		return uploadProcess.doUploadAvr(file, uploadParam);
	}
	
}
