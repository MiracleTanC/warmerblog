package warmer.star.blog.util;

public class BaseQueryItem {

	 /**

     * 默认每页显示数

     */
    public  final int PAGE_SIZE = 10;

    /**

     * 默认页数

     */
    public final int PAGE_INDEX = 1;

    /**

     * 页数

     */
    private int pageIndex;

    /**

     * 每页显示数

     */
    private int pageSize;


	public int getPageIndex() {
		if(pageIndex==0)
		{
			return this.PAGE_INDEX;
		}
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		if(pageSize==0)
		{
			return this.PAGE_SIZE;
		}
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
