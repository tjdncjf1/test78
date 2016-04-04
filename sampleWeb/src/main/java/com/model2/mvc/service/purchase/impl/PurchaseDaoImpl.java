package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	///Constructor
	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("::" + getClass() + ".setSqlSession() Call....");
		this.sqlSession = sqlSession;
	}
	
	public int addPurchase(Purchase purchase) throws Exception {
		return sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}

	public Purchase getPurchase(int tranNo) throws Exception {
		Purchase purchase = sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
		
		if (purchase.getDivyDate() != null) {
			String[] splitDivyDate = purchase.getDivyDate().split("-");
			String divyDate = "";
			for(String str : splitDivyDate) {
				divyDate += str;
			}
			purchase.setDivyDate(divyDate);
		}
		
		purchase.setPaymentOption(purchase.getPaymentOption().trim());
		
		return purchase;
	}

	public int updatePurchase(Purchase purchase) throws Exception {
		return sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	public int updateTranCode(Purchase purchase) throws Exception {
		return sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}
	
	public List<Purchase> getPurchaseList(Search search, String buyerId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
//		map.put("search", search);
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		map.put("buyerId", buyerId);
		
		List<Purchase> list = sqlSession.selectList("PurchaseMapper.getPurchaseList", map); 
		for (Purchase purchase : list) {
			Product product = sqlSession.selectOne("ProductMapper.getProduct", purchase.getPurchaseProd().getProdNo());
			User user = sqlSession.selectOne("UserMapper.getUser", purchase.getBuyer().getUserId());
			purchase.setPurchaseProd(product);
			purchase.setBuyer(user);
			if(purchase.getTranCode() != null) { 
				purchase.setTranCode(purchase.getTranCode().trim());
			}
		}
		
		
		return list;
	}
	
	public int getTotalCount(String buyerId) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", buyerId);
	}

	
}