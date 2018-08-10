package warmer.star.blog.util;

import java.util.List;


public class PageRecord<T> {
	public Integer getCurrentPageSize() {
		return currentPageSize;
	}

	public void setCurrentPageSize(Integer currentPageSize) {
		this.currentPageSize = currentPageSize;
	}

	public List<T> rows;
	public Integer currentPage;
	public Integer totalPage;
	public Integer currentPageSize;
	public long totalCount;
	
	public List<T> getRows() {
		return this.rows;
	}
	
	public void setRows(List<T> list) {
		this.rows = list;
	}
	
	public Integer getCurrentPage() {
		return this.currentPage;
	}
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public Integer getTotalPage() {
		return this.totalPage;
	}
	
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	public long getTotalCount() {
		return this.totalCount;
	}
	
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
}
