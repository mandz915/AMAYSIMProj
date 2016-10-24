package com.amysim.cart;

public class Promo {
	
	private String promo_type;
	private String promo_code;
	private String product_code;
	private int itemCount;
	private int promoItem_Count;
	private double promoPrice;
	private String freebieProductCode;
	private int freebieProductCount;
	
	public String getPromo_type() {
		return promo_type;
	}
	public void setPromo_type(String promo_type) {
		this.promo_type = promo_type;
	}
	public String getPromo_code() {
		return promo_code;
	}
	public void setPromo_code(String promo_code) {
		this.promo_code = promo_code;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	public double getPromoPrice() {
		return promoPrice;
	}
	public void setPromoPrice(double promoPrice) {
		this.promoPrice = promoPrice;
	}

	public int getPromoItem_Count() {
		return promoItem_Count;
	}
	public void setPromoItem_Count(int promoItem_Count) {
		this.promoItem_Count = promoItem_Count;
	}
	public int getFreebieProductCount() {
		return freebieProductCount;
	}
	public void setFreebieProductCount(int freebieProductCount) {
		this.freebieProductCount = freebieProductCount;
	}
	public String getFreebieProductCode() {
		return freebieProductCode;
	}
	public void setFreebieProductCode(String freebieProductCode) {
		this.freebieProductCode = freebieProductCode;
	}


}
