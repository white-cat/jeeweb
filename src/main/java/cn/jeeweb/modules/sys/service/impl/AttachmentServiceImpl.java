package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.utils.FileUtil;
import cn.jeeweb.core.utils.IpUtils;
import cn.jeeweb.core.utils.PropertiesUtil;
import cn.jeeweb.core.utils.ServletUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.core.utils.upload.FileUploadUtils;
import cn.jeeweb.core.utils.upload.exception.FileNameLengthLimitExceededException;
import cn.jeeweb.core.utils.upload.exception.InvalidExtensionException;
import cn.jeeweb.modules.sys.entity.Attachment;
import cn.jeeweb.modules.sys.service.IAttachmentService;
import cn.jeeweb.modules.sys.utils.UserUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Service("attachmentService")
public class AttachmentServiceImpl extends CommonServiceImpl<Attachment> implements IAttachmentService {
	public static final String DEFAULT_CONFIG_FILE = "upload.properties";
	protected String configname = DEFAULT_CONFIG_FILE;
	private long maxSize = 0;
	private boolean needDatePathAndRandomName = true;
	private String[] allowedExtension;
	private String baseDir;

	@PostConstruct
	public void initAttachement() {
		PropertiesUtil propertiesUtil = new PropertiesUtil(configname);
		maxSize = propertiesUtil.getLong("upload.max.size");
		baseDir = propertiesUtil.getString("upload.base.dir");
		String extension = propertiesUtil.getString("upload.allowed.extension");
		allowedExtension = extension.split(",");
	}

	@Override
	public Attachment upload(HttpServletRequest request, MultipartFile file) throws FileSizeLimitExceededException,
			InvalidExtensionException, FileNameLengthLimitExceededException, IOException {
		String url = FileUploadUtils.upload(request, baseDir, file, allowedExtension, maxSize,
				needDatePathAndRandomName);
		String filename = file.getOriginalFilename();
		long size = file.getSize();
		String realFileName = StringUtils.getFileNameNoEx(filename);
		String fileext = StringUtils.getExtensionName(filename);
		// 保存上传的信息
		Attachment attachment = new Attachment();
		attachment.setFileext(fileext);
		attachment.setFilename(realFileName);
		attachment.setFilepath(url);
		attachment.setFilesize(size);
		attachment.setStatus("1");
		attachment.setUploadip(IpUtils.getIpAddr(request));
		attachment.setUploadtime(new Date());
		attachment.setUser(UserUtils.getUser());
		save(attachment);
		return attachment;
	}

	@Override
	public void batchDeleteById(List<?> ids) {
		for (Object object : ids) {
			deleteById((Serializable) object);
		}
	}

	@Override
	public void deleteById(Serializable id) {
		Attachment attachment = get(id);
		String basePath = ServletUtils.getRequest().getServletContext().getRealPath("/");
		String filePath = attachment.getFilepath();
		FileUtil.delFile(basePath + filePath);
		super.deleteById(id);
	}

}
