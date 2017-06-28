package cn.jeeweb.modules.sys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUploadBase;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;

import cn.jeeweb.core.common.controller.BaseController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.PropertyPreFilterable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.MessageUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.core.utils.upload.exception.FileNameLengthLimitExceededException;
import cn.jeeweb.core.utils.upload.exception.InvalidExtensionException;
import cn.jeeweb.modules.sys.entity.Attachment;
import cn.jeeweb.modules.sys.service.IAttachmentService;

@Controller
@RequestMapping("${admin.url.prefix}/sys/attachment")
@RequiresPathPermission("sys:attachment")
public class AttachmentController extends BaseController {

	@Autowired
	private IAttachmentService attachmentService;

	@RequiresMethodPermissions("list")
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
		return display("list");
	}

	/**
	 * 根据页码和每页记录数，以及查询条件动态加载数据
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "ajaxList", method = RequestMethod.GET)
	private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Attachment.class);
		propertyPreFilterable.addQueryProperty("id");
		SerializeFilter filter = propertyPreFilterable.constructFilter(Attachment.class);
		PageJson<Attachment> pagejson = new PageJson<Attachment>(attachmentService.list(queryable, detachedCriteria));
		String content = JSON.toJSONString(pagejson, filter);
		StringUtils.printJson(response, content);
	}

	/**
	 * 
	 * @title: ajaxUpload
	 * @description: 文件上传
	 * @param request
	 * @param response
	 * @param files
	 * @return
	 * @return: AjaxUploadResponse
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson upload(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");
		AjaxJson ajaxJson = new AjaxJson();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		if (multipartResolver.isMultipart(request)) { // 判断request是否有文件上传
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> ite = multiRequest.getFileNames();
			while (ite.hasNext()) {
				MultipartFile file = multiRequest.getFile(ite.next());
				try {
					Attachment attachment = attachmentService.upload(request, file);
					attachmentList.add(attachment);
					continue;
				} catch (IOException e) {
					ajaxJson.fail(MessageUtils.getMessage("upload.server.error"));
					continue;
				} catch (InvalidExtensionException e) {
					ajaxJson.fail(MessageUtils.getMessage("upload.server.error"));
					continue;
				} catch (FileUploadBase.FileSizeLimitExceededException e) {
					ajaxJson.fail(MessageUtils.getMessage("upload.server.error"));
					continue;
				} catch (FileNameLengthLimitExceededException e) {
					ajaxJson.fail(MessageUtils.getMessage("upload.server.error"));
					continue;
				}
			}
			ajaxJson.setData(attachmentList);
		}
		return ajaxJson;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson delete(@RequestParam("id") String[] ids) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("删除成功");
		try {
			List<String> idList = java.util.Arrays.asList(ids);
			attachmentService.batchDeleteById(idList);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("删除失败");
		}
		return ajaxJson;
	}

	/**
	 * 
	 * @title: ajaxUpload
	 * @description: 文件上传
	 * @param request
	 * @param response
	 * @param files
	 * @return
	 * @return: AjaxUploadResponse
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson list(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");
		AjaxJson ajaxJson = new AjaxJson();
		String saveType = request.getParameter("saveType");
		if (saveType.equals("id")) {
			String id = request.getParameter("id");
			List<Attachment> list = attachmentService.listByCriterion(Restrictions.in("id", id.split(",")));
			ajaxJson.setData(list);
		} else {
			String filepath = request.getParameter("url");
			List<Attachment> list = attachmentService.listByCriterion(Restrictions.in("filepath", filepath.split(",")));
			ajaxJson.setData(list);
		}

		return ajaxJson;
	}
}
