package cn.itcast.bos.domain.qp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.domain.user.User;

/**
 * 业务通知单 NoticeBill entity. @author MyEclipse Persistence Tools
 */

public class NoticeBill implements java.io.Serializable {

	// Fields

	private String id; // 编号 （默认 assigned 将 hbm生成策略改为 uuid）
	private User user; // 业务受理人
	private Staff staff; // 取派员
	private String customerId; // 客户编号
	private String customerName; // 客户姓名
	private String delegater; // 联系人
	private String telephone; // 手机号
	private String pickaddress; // 取件地址
	private String arrivecity; // 到达城市
	private String product; // 货物名称
	private Date pickdate; // 取件日期
	private Integer num; // 数量
	private Double weight; // 重量
	private String volume; // 体积
	private String remark; // 备注

	private String ordertype; // 分单类型
	private Set workBills = new HashSet(0);// 关联工单

	// Constructors

	/** default constructor */
	public NoticeBill() {
	}

	/** minimal constructor */
	public NoticeBill(String id) {
		this.id = id;
	}

	/** full constructor */
	public NoticeBill(String id, User user, Staff staff, String customerId, String customerName, String delegater, String telephone, String pickaddress, String arrivecity, String product, Date pickdate, Integer num, Double weight, String volume, String remark, String ordertype, Set workBills) {
		this.id = id;
		this.user = user;
		this.staff = staff;
		this.setCustomerId(customerId);
		this.customerName = customerName;
		this.delegater = delegater;
		this.telephone = telephone;
		this.pickaddress = pickaddress;
		this.arrivecity = arrivecity;
		this.product = product;
		this.pickdate = pickdate;
		this.num = num;
		this.weight = weight;
		this.volume = volume;
		this.remark = remark;
		this.ordertype = ordertype;
		this.workBills = workBills;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDelegater() {
		return this.delegater;
	}

	public void setDelegater(String delegater) {
		this.delegater = delegater;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPickaddress() {
		return this.pickaddress;
	}

	public void setPickaddress(String pickaddress) {
		this.pickaddress = pickaddress;
	}

	public String getArrivecity() {
		return this.arrivecity;
	}

	public void setArrivecity(String arrivecity) {
		this.arrivecity = arrivecity;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Date getPickdate() {
		return this.pickdate;
	}

	public void setPickdate(Date pickdate) {
		this.pickdate = pickdate;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrdertype() {
		return this.ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public Set getWorkBills() {
		return this.workBills;
	}

	public void setWorkBills(Set workBills) {
		this.workBills = workBills;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}