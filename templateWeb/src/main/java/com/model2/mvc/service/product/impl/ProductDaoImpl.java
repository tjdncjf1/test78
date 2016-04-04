package com.model2.mvc.service.product.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	///Constructor
	public ProductDaoImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("::" + getClass() + ".setSqlSession() Call....");
		this.sqlSession = sqlSession;
	}
	
	public int addProduct(Product product) throws Exception {
		String[] splitManuDate = product.getManuDate().split("-");
		String manuDate = "";
		for(String str : splitManuDate) {
			manuDate += str;
		}
		product.setManuDate(manuDate);
		return sqlSession.insert("ProductMapper.addProduct", product);
	}

	public Product getProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}

	public int updateProduct(Product product) throws Exception {
		return sqlSession.update("ProductMapper.updateProduct", product);
	}
	
	public List<Product> getProductList(Search search) throws Exception {
		List<Product> list = sqlSession.selectList("ProductMapper.getProductList", search);
		for (Product product : list) {
			String tranCode = sqlSession.selectOne("PurchaseMapper.getTranCode", product.getProdNo());
			if(tranCode != null && tranCode != "") {
				product.setProTranCode(tranCode.trim());
			}
		}
//		return sqlSession.selectList("ProductMapper.getProductList", search);
		return list;
	}
	
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}
	
}