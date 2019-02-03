package evol.common;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
public class PageResult<T>  implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //总记录数
    private long total;
    //总页数
    private int pageTotal;
    //结果集
    private List<T> list;
    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;


    public PageResult() {
    }

    /**
     * @param list
     * @param pageNum
     * @param pageSize
     * @param pageTotal
     * @param recordTotal
     */
    public PageResult(List<T> list, int pageNum, int pageSize, int pageTotal, int recordTotal) {
        if (list == null)
            throw new NullPointerException("list");
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pageTotal = pageTotal;
        this.total = recordTotal;
        this.list = list;
    }

    public PageResult(List<T> list) {
        this.pageSize = list.size();

        this.pageNum = 1;
        this.list = list;
        this.total = list.size();
        //判断页面边界
        judgePageBoudary();
    }

    public PageResult(List<T> list, int pageNum, int pageSize, int recordTotal) {
        this.pageSize = pageSize;

        this.pageNum = pageNum;
        this.list = list;
        this.total = recordTotal;
        //判断页面边界
        judgePageBoudary();
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = pageNum == 1;
        isLastPage = pageNum == pageTotal;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotal() {
        return total;
    }


    public int getPageTotal() {
        return pageTotal;
    }

    public List<T> getList() {
        return list;
    }


    public boolean isIsFirstPage() {
        return isFirstPage;
    }


    public boolean isIsLastPage() {
        return isLastPage;
    }

    @Override
    public String toString() {
        final StringBuilder strBuf = new StringBuilder("PageResult{");
        strBuf.append("pageNum=").append(pageNum);
        strBuf.append(", pageSize=").append(pageSize);
        strBuf.append(", total=").append(total);
        strBuf.append(", pageTotal=").append(pageTotal);
        strBuf.append(", list=").append(list);
        strBuf.append(", isFirstPage=").append(isFirstPage);
        strBuf.append(", isLastPage=").append(isLastPage);
        strBuf.append(", navigatepageNums=");
        strBuf.append('}');
        return strBuf.toString();
    }
}
