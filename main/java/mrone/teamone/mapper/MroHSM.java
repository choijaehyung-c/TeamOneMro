package mrone.teamone.mapper;

import java.util.List;

import mrone.teamone.beans.ProductBean;

public interface MroHSM {
	public List<ProductBean> getRequestRegisterNewProductList();
	public boolean mroResponseNewProduct();
}
