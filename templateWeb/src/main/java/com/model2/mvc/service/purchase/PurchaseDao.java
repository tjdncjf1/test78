package com.model2.mvc.service.purchase;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;


public interface PurchaseDao {
	
	// INSERT
	public int addPurchase(Purchase purchase) throws Exception;

	// SELECT ONE
	public Purchase getPurchase(int tranNo) throws Exception;

	// UPDATE
	public int updatePurchase(Purchase purchase) throws Exception;

	public int updateTranCode(Purchase purchase) throws Exception;

	// SELECT LIST
	public List<Purchase> getPurchaseList(Search search, String buyerId) throws Exception;
	
	public int getTotalCount(String buyerId) throws Exception ;

}