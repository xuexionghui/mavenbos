package cn.itcast.bos.domain;

import java.util.List;

@SuppressWarnings("all")
public class PageResponseBean  {
	private long total; // 总记录数
	private List rows; // 当前页数据记录
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
