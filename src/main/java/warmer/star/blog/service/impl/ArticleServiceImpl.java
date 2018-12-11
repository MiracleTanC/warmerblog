package warmer.star.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmer.star.blog.dto.ArticleItem;
import warmer.star.blog.dto.ArticleQueryItem;
import warmer.star.blog.dto.ArticleSubmitItem;
import warmer.star.blog.mapper.ArticleMapper;
import warmer.star.blog.model.ArticleDetail;
import warmer.star.blog.model.ArticleFile;
import warmer.star.blog.service.ArticleService;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

	@Override
	public List<ArticleItem> getArticleList(ArticleQueryItem queryItem) {
		List<ArticleItem> list=articleMapper.getArticleList(queryItem);
		int i=1;
		for (ArticleItem articleItem : list) {
			int num=(queryItem.getPageIndex()-1)*queryItem.getPageSize()+i;
			articleItem.setSerialNumber(num);
			i++;
		}
		return list;
	}

	@Override
	public ArticleItem getById(int articleId) {
		return articleMapper.getById(articleId);
	}

	@Override
	public ArticleDetail getContentById(int articleId) {
		return articleMapper.getContentById(articleId);
	}


	@Override
	public int saveArticle(ArticleSubmitItem submitItem) {
		return articleMapper.saveArticle(submitItem);
	}

	@Override
	public void saveContent(ArticleDetail submitItem) {
		articleMapper.saveContent(submitItem);
	}

	@Override
	public void saveImage(List<ArticleFile> fileList) {
		articleMapper.saveImage(fileList);
	}

	@Override
	public boolean updateArticle(ArticleSubmitItem submitItem) {
		return articleMapper.updateArticle(submitItem);
	}

	@Override
	public boolean deleteArticle(int articleId) {
		return articleMapper.deleteArticle(articleId);
	}

	@Override
	public boolean deleteImage(int articleId) {
		return articleMapper.deleteImage(articleId);
	}

	@Override
	public boolean deleteContent(int articleId) {
		return articleMapper.deleteContent(articleId);
	}

	@Override
	public List<ArticleItem> getRecommendArticleList() {
		// TODO Auto-generated method stub
		return articleMapper.getRecommendArticleList();
	}

	@Override
	public List<ArticleItem> getTopReadArticleList() {
		// TODO Auto-generated method stub
		return articleMapper.getTopReadArticleList();
	}

  
}
