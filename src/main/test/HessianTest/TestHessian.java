package HessianTest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junlaninfo.domain.Customer;
import com.junlaninfo.service.CustomerService;

/**
*  @author xuexionghui E-mail:413669152@qq.com
*  @version 创建时间：2018年10月26日 下午5:29:22 
*  
**/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class TestHessian {
	
	@Resource(name="customerService")
	private CustomerService  customerServiceImpl;
	
	/*
	 * 测试查询没有关联定区的用户
	 */
	@Test
	public void  demo1() {
		List<Customer> customers = customerServiceImpl.findCustomerNoConnectDecidedzone();
		System.out.println(customers.size());
		System.out.println(customers);
	}
	

}
