package cn.itcast.bos.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.service.regionService;
import cn.itcast.bos.service.staffService;
import cn.itcast.bos.utils.PinYin4jUtils;

public class regionAction extends ActionSupport implements ModelDriven<Region> {
    //使用logger4j作为日志记录器
	private Logger log=Logger.getLogger(regionAction.class);
	
	//模型驱动
	private Region region=new Region();
	public Region getModel() {
		// TODO Auto-generated method stub
		return region;
	}
	
	//属性驱动，接收上传的文件
	private File  upload;
	public void setUpload(File upload) {
		this.upload = upload;
	}

	@Resource(name="regionService")
	private regionService  regionServiceImpl;
	/*
	 * 保存或者更新region
	 */
	public String  saveOrUpdate() {
		regionServiceImpl.saveOrUpdate(region);
		return "saveOrUpdateSuccess";
	}
	
	/*
	 * 列表查询region数据
	 */
	//属性驱动
	private int page;
	private int rows;
	public String    pageQuery() {
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Region.class);
		detachedCriteria.add(Restrictions.isNotNull("id"));
		pageRequestBean.setDetachedCriteria(detachedCriteria);
		PageResponseBean pageResponseBean=regionServiceImpl.pageQuery(pageRequestBean);
		//将查询到的内容放入到域中
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		return "pageQuerySuccess";
	}
	
	
	/*
	 * 删除region
	 */
	public String deleteBatch() {
	    regionServiceImpl.deleteBatch(region);
		return "deleteBatchSuccess";
	}
	
	/*
	 * 仿照ajax的效果，一键导入ocupload区域数据
	 */
	@SuppressWarnings("resource")
	public  String  importXls() throws Exception{
		//1、进行excel解析，创建工作簿对象
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(upload));
		   //解析工作簿，为了避免表格中有单元格没有数据，出现空指针异常
		  //workbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);
	   
	   //2、获得sheet,这里取第一个sheet
		HSSFSheet sheet = workbook.getSheetAt(0);
		
	//3、遍历每一行
		for (Row row : sheet) {
			//进行解析，每一行数据，对应Region的区域信息
			if(row.getRowNum()==0) {   //第一行是表头，无需解析
				continue;
			}
			//从第二行起才开始解析
			Region region2 = new Region();
			region2.setProvince(row.getCell(1).getStringCellValue());
		    region2.setCity(row.getCell(2).getStringCellValue());
		    region2.setDistrict(row.getCell(3).getStringCellValue());
		    region2.setPostcode(row.getCell(4).getStringCellValue());

			// 使用pinyin4j 生成简码和城市编码
			// 连接省市区
			//String str = region2.getProvince() + region2.getCity() + region2.getDistrict();
			/*str = str.replaceAll("省", "").replaceAll("市", "").replaceAll("区", "");
			String[] arr = PinYin4jUtils.getHeadByString(str);
			StringBuffer sb = new StringBuffer();
			for (String headChar : arr) {
				sb.append(headChar);
			}
			region2.setShortcode(sb.toString()); // 简码
			// 生成城市编码
			region.setCitycode(PinYin4jUtils.hanziToPinyin(region2.getCity(), ""));*/
			try {
				regionServiceImpl.saveOrUpdate(region2);
			} catch (Exception e) {
				// 导入region失败，记录日志
				log.error("区域导入失败，编号：" + region2.getId(), e);
			}
			
		}
		// 返回json
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("result", "success");
				map.put("msg", "区域导入完成");

	    ActionContext.getContext().put("map", map);
		return "importSuccess";
	}
	

}
