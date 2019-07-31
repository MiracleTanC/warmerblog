package warmer.star.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import warmer.star.blog.dto.MusicQueryItem;
import warmer.star.blog.model.Music;
import warmer.star.blog.service.MusicService;
import warmer.star.blog.util.DateTimeHelper;
import warmer.star.blog.util.PageRecord;
import warmer.star.blog.util.R;

import java.util.List;

@Controller
@RequestMapping("/")
public class MusicController extends BaseController {

	@Autowired
	private MusicService musicService;
	@RequestMapping("/music")
	@PreAuthorize("hasRole('ADMIN')")
	public String index(MusicQueryItem queryItem) {
		return "music/index";
	}
	@RequestMapping("/getmusiclist")
	@ResponseBody
	public R getmusiclist(MusicQueryItem query){
		PageHelper.startPage(query.getPageIndex(),query.getPageSize(),true);
		List<Music> musicList = musicService.getMusicList(query);
		PageInfo<Music> pageInfo = new PageInfo<Music>(musicList);
		long total= pageInfo.getTotal();
		int pages = pageInfo.getPages();
		PageRecord<Music> pageRecord = new PageRecord<Music>();
		pageRecord.setRows(musicList);
		pageRecord.setCurrentPage(query.getPageIndex());
		pageRecord.setCurrentPageSize(query.getPageSize());
		pageRecord.setTotalCount(total);
		pageRecord.setTotalPage(pages);
		return R.success().put("data", pageRecord);
	}
	@RequestMapping("/music/edit/{musicId}")
	public String edit(@PathVariable("musicId")int musicId,Model model) {
		Music MusicItem=new Music();
		if(musicId!=0)
		{
			MusicItem=musicService.getById(musicId);
			model.addAttribute("musicModel",MusicItem);
		}
		return "music/edit";
	}
	@RequestMapping("/music/saveMusic")
	@ResponseBody
	public R saveMusic(Music submitItem) {
		
		boolean result=false; 
		try {
			if(submitItem.getId()==0)
			{
				submitItem.setCreateOn(DateTimeHelper.getNowDate());
				submitItem.setUpdateOn(DateTimeHelper.getNowDate());;
				result=musicService.saveMusic(submitItem);
			}
			else {
				submitItem.setUpdateOn(DateTimeHelper.getNowDate());
				result=musicService.updateMusic(submitItem);
			}
			
		} catch (Exception e) {
			log.error("操作失败:{0}", e);
			log.error(e.getMessage());
			result=false;
		}
		if(result)
		{
			return R.success("操作成功");
		}
		return R.error("操作失败");
	}
	@RequestMapping("/music/batchsavemusic")
	@ResponseBody
	public R batchsaveMusic(@RequestBody List<Music> submitItem) {
		
		boolean result=false; 
		try {
			for (Music music : submitItem) {
				music.setCreateOn(DateTimeHelper.getNowDate());
				music.setUpdateOn(DateTimeHelper.getNowDate());
				music.setSortCode(0);
				result=musicService.saveMusic(music);
			}
			
		} catch (Exception e) {
			log.error("操作失败:{0}", e);
			log.error(e.getMessage());
			result=false;
		}
		if(result)
		{
			return R.success("操作成功");
		}
		return R.error("操作失败");
	}
	@RequestMapping("/music/deleteMusic")
	@ResponseBody
	public R deleteMusic(int musicId) {
		boolean result = false;
		try {
			result = musicService.deleteMusic(musicId);
		} catch (Exception e) {
			log.error("操作失败:{0}", e);
			log.error(e.getMessage());
			result = false;
		}
		if (result) {
			return R.success("操作成功");
		}
		return R.error("操作失败");
	}

}