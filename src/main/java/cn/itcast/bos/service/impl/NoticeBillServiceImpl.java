package cn.itcast.bos.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.junlaninfo.service.CustomerService;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.domain.qp.NoticeBill;
import cn.itcast.bos.domain.qp.WorkBill;
import cn.itcast.bos.service.NoticeBillService;

/**
*  @author xuexionghui E-mail:413669152@qq.com
*  @version 创建时间：2018年10月29日 上午11:02:35 
*  
**/

public class NoticeBillServiceImpl implements NoticeBillService {
	
	@Resource(name="noticeBillDao")
	private GenericDAO<NoticeBill>    noticeBillDaoImpl;
	
	@Resource(name="")
	private CustomerService  customerServiceImpl;
	
	@Resource(name="decidedzoneDao")
	private  GenericDAO<DecidedZone>    decidedzoneDaoImpl;
   
	@Resource(name="workBillDao")
	private GenericDAO<WorkBill>       workBillDaoImpl;
	@Resource(name="subareaDao")
	private  GenericDAO<Subarea>       subareaDaoImpl;
 	/*
	 * 保存通知单
	 */
	@Override
	public void save(NoticeBill noticeBill) {
		//将业务通知单保存到数据库中
		noticeBillDaoImpl.save(noticeBill);
		
		
		//自动分单      使用当前取件地址，去查询crm系统     定区编码
		String decidedzoneId= customerServiceImpl.findDecidedzoneIdByCustomerAddress(noticeBill.getPickaddress());
		if(decidedzoneId==null) {  //去crm中没有找到取件地址
			String[]  addressArray = noticeBill.getPickaddress().split(" ");
			if (addressArray.length>=2) {
				String addresskey = addressArray[1];   //取第二个元素
				List<Subarea> subareas=subareaDaoImpl.findByNamedQuery("Subarea.findByAddresskey",addressArray);
				if(subareas.size()==1&&subareas.get(0).getAddresskey()!=null) {
					//自动分单
					DecidedZone decidedZone = subareas.get(0).getDecidedZone();
					//通知单
					noticeBill.setStaff(decidedZone.getStaff());
					noticeBill.setOrdertype("自动");
					
					// 工单信息
					WorkBill workBill = new WorkBill();
					workBill.setNoticeBill(noticeBill);
					workBill.setStaff(decidedZone.getStaff());
					workBill.setType("新");
					workBill.setPickstate("新单");
					workBill.setBuildtime(new Timestamp(System.currentTimeMillis()));
					workBill.setAttachbilltimes(0);
					workBill.setRemark(noticeBill.getRemark());
					workBillDaoImpl.save(workBill);
					
					
					
				}else {
					// 人工调度
					noticeBill.setOrdertype("人工");
				}
			}else {    //地址中长度不大于2
				// 人工调度
				noticeBill.setOrdertype("人工");
			}
		}else {   //从crm查询到的decidedZoneId不为空                 自动分单成功
		   DecidedZone decidedZone=	decidedzoneDaoImpl.findById(decidedzoneId);
		   
		   //创建工单
		   WorkBill workBill = new WorkBill();
		   workBill.setNoticeBill(noticeBill);
		   workBill.setStaff(decidedZone.getStaff());   //从定区中取出取派员设置到工单中
		   workBill.setType("新");
		   workBill.setPickstate("新单");
		   workBill.setBuildtime(new Timestamp(System.currentTimeMillis()));
		   workBill.setAttachbilltimes(0);
		   workBill.setRemark(noticeBill.getRemark());
		   workBillDaoImpl.save(workBill);
		}
		
	}
	
	

}
