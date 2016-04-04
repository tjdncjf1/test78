package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	@Test
	public void testAddPurchase() throws Exception {
		
		Product product = new Product();
		product.setProdNo(10021);
		
		User user = new User();
		user.setUserId("user01");
		
		Purchase purchase = new Purchase();
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("testName");
		purchase.setReceiverPhone("testName");
		purchase.setDivyAddr("서울");
		purchase.setDivyRequest("1111");
		purchase.setDivyDate("12-05-27");
		
		purchaseService.addPurchase(purchase);
		
//		purchase = purchaseService.getPurchase(10041);

		//==> console 확인
//		System.out.println(purchase);
		
		//==> API 확인
//		Assert.assertEquals(10041, purchase.getTranNo());
//		Assert.assertEquals(10021, purchase.getPurchaseProd().getProdNo());
//		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
//		Assert.assertEquals("1", purchase.getPaymentOption().trim());
//		Assert.assertEquals("서울", purchase.getDivyAddr());
//		Assert.assertEquals("12-05-27 00:00:00.0", purchase.getDivyDate());
	}
	
	//@Test
	public void testGetPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		purchase = purchaseService.getPurchase(10041);

		//==> console 확인
		System.out.println(purchase);
		
		//==> API 확인
		Assert.assertEquals(10041, purchase.getTranNo());
		Assert.assertEquals(10021, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals("1", purchase.getPaymentOption().trim());
		Assert.assertEquals("서울", purchase.getDivyAddr());
		Assert.assertEquals("12-05-27 00:00:00.0", purchase.getDivyDate());

		Assert.assertNotNull(purchaseService.getPurchase(10028));
		Assert.assertNotNull(purchaseService.getPurchase(10029));
	}
	
	//@Test
	 public void testUpdatePurchase() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(10041);
		Assert.assertNotNull(purchase);
		
		purchase.setPaymentOption("2");
		purchase.setReceiverName("testName22");
//		purchase.setReceiverPhone("testName");
		purchase.setDivyAddr("서울22");
//		purchase.setDivyRequest("1111");
		purchase.setDivyDate("55-12-27");
		
		purchaseService.updatePurchase(purchase);
		
		purchase = purchaseService.getPurchase(10041);
		Assert.assertNotNull(purchase);
		
//		//==> console 확인
		System.out.println(purchase);
			
		//==> API 확인
		Assert.assertEquals(10041, purchase.getTranNo());
		Assert.assertEquals(10021, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals("2", purchase.getPaymentOption().trim());
		Assert.assertEquals("testName22", purchase.getReceiverName().trim());
		Assert.assertEquals("서울22", purchase.getDivyAddr());
		Assert.assertEquals("1955-12-27 00:00:00.0", purchase.getDivyDate());
	 }
	 
	 //==>  주석을 풀고 실행하면....
	 //@Test
	 public void testGetPurchaseListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = purchaseService.getPurchaseList(search, "user01");
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 }
}