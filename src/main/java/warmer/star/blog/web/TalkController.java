package warmer.star.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import warmer.star.blog.config.QiniuUploadService;
import warmer.star.blog.model.ELog;
import warmer.star.blog.model.ELogFile;
import warmer.star.blog.service.ElogService;
import warmer.star.blog.util.BaseQueryItem;
import warmer.star.blog.util.DateTimeHelper;
import warmer.star.blog.util.PageRecord;
import warmer.star.blog.util.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/")
public class TalkController extends BaseController {

	@Autowired
	private ElogService elogService;
	@Autowired
	private QiniuUploadService qiniuUploadService;

	@RequestMapping(value = "/talk", method = RequestMethod.GET)
	public String talk(Model model) {
		return "talk/index";
	}
	@RequestMapping("/talk/save")
	@ResponseBody
	public R save(HttpServletRequest req, HttpServletResponse response) {
		try {
			String content=req.getParameter("content");
			ELog log=new ELog();
			log.setContent(content);
			log.setCreateTime(DateTimeHelper.getNowDate());
			log.setUpdateTime(DateTimeHelper.getNowDate());
			elogService.addELog(log);
			if(log.getId()>0){
				List<MultipartFile> files = ((MultipartHttpServletRequest) req).getFiles("file");
				for (MultipartFile file : files) {
					String fileName = file.getOriginalFilename();
					String url = "http://" + qiniuUploadService.uploadImage(file, fileName);
					ELogFile eLogFile=new ELogFile();
					eLogFile.seteLogId(log.getId());
					eLogFile.setFileurl(url);
					eLogFile.setCreateTime(DateTimeHelper.getNowDate());
					eLogFile.setUpdateTime(DateTimeHelper.getNowDate());
					elogService.addELogFile(eLogFile);
				}
				return  R.success();
			}
		} catch (FileUploadException e) {
			log.error(e.getMessage());
		}
		return R.error("操作失败");
	}
	@RequestMapping(value = "/e", method = RequestMethod.GET)
	public String e(Model model) {
		return "talk/e";
	}
	@RequestMapping("/talk/geteloglist")
	@ResponseBody
	public R getEloglist(BaseQueryItem query) {
		PageHelper.startPage(query.getPageIndex(), query.getPageSize(), true);
		List<ELog> eLogs = elogService.getAll();
		PageInfo<ELog> pageInfo = new PageInfo<ELog>(eLogs);
		long total = pageInfo.getTotal();
		int pages = pageInfo.getPages();
		PageRecord<ELog> pageRecord = new PageRecord<ELog>();
		pageRecord.setRows(eLogs);
		pageRecord.setCurrentPage(query.getPageIndex());
		pageRecord.setCurrentPageSize(query.getPageSize());
		pageRecord.setTotalCount(total);
		pageRecord.setTotalPage(pages);
		return R.success().put("data", pageRecord);
	}
}