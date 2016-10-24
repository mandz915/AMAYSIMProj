package com.amysim.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

	Map<String, Promo> promoMap = new HashMap<>();
	Map<String, Coupon> couponMap = new HashMap<>();
	Map<String, Product> productMap = new HashMap<>();
	Map<String, Integer> itemMap = new HashMap<String, Integer>();
	List<Object> freebeeItem = new ArrayList<>();
	List<String> productArrangent = new ArrayList<>();
	String couponApplied;
	double total = 0.00;
	
	public void initialize() {
		initializeProduct();
		initializePromo();	
		initializeCoupon();
	}

	public void initializeProduct() {		
		setProducts("ult_small","Unlimited 1GB",24.90);
		setProducts("ult_medium","Unlimited 2GB",29.90);
		setProducts("ult_large","Unlimited 5GB",44.90);
		setProducts("1gb","1 GB Data-pack",9.90);
	}

	public void initializePromo() {
		setPromos("ult_small","PayXforY",3,2,0.0,"",0);
		setPromos("ult_medium","FreebieItem",1,0,0.0,"1gb",1);
		setPromos("ult_large","PriceforMore",3,0,39.90,"",0);
	}
	
	public void initializeCoupon(){
		setCoupon("I<3AMAYSIM","10% Discount",0.9);
	}
	
	public void setCoupon(String couponCode, String couponDesc, double multiplier) {
		
		Coupon coupon = new Coupon();
		coupon.setCouponCode(couponCode);
		coupon.setCouponCode(couponDesc);
		coupon.setCouponMultiplier(multiplier);
		couponMap.put(couponCode, coupon);
		
	}

	public void setProducts(String product_code, String product_name, double price){
		Product product = new Product();
		product.setProduct_code(product_code);
		product.setProduct_name(product_name);
		product.setPrice(price);
		productMap.put(product_code, product);
		productArrangent.add(product_code);
	}
	
	public void setPromos(String product_code, String promo_code,int itemCount,int chargeItemPromo, double promoPrice, String freebieProduct, int freebieProductCount){
		Promo promo = new Promo();
		promo.setPromo_code(promo_code);
		promo.setProduct_code(product_code);
		promo.setItemCount(itemCount);
		promo.setPromoItem_Count(chargeItemPromo);
		promo.setPromoPrice(promoPrice);
		promo.setFreebieProductCode(freebieProduct);
		promo.setFreebieProductCount(freebieProductCount);
		promoMap.put(product_code, promo);
	}

	public void computeOrder() {
		double itemTotal;
		Promo promoProduct;
		Product productSelected;		
		for(String itemCode : itemMap.keySet()){
			promoProduct = promoMap.get(itemCode);
			productSelected = productMap.get(itemCode);
			itemTotal = promoItemTotal(promoProduct, productSelected, itemMap.get(itemCode));
			System.out.println( "Product code " + itemCode + "," + itemTotal);		
			total = total + itemTotal;			
		}
		
		if(couponMap.containsKey(couponApplied)){
			Coupon x = couponMap.get(couponApplied);
			total = total * x.getCouponMultiplier();
		}

		System.out.println("Total " + total);
		
	}

	public void add(String item_code) {
		
		if(itemMap.containsKey(item_code)){
			itemMap.put(item_code, (itemMap.get(item_code) + 1));
		}
		else{
			itemMap.put(item_code, 1);
		}
		
		System.out.println("item count :"  + item_code + ","+ itemMap.get(item_code));
	}
	
	public void add(String item_code, String couponCode) {
		
		if(itemMap.containsKey(item_code)){
			itemMap.put(item_code, (itemMap.get(item_code) + 1));
		}
		else{
			itemMap.put(item_code, 1);
		}
		couponApplied = couponCode;
		

	}
	
	public double promoItemTotal(Promo promo, Product productSelected, int count){
		double itemPromoTotal = 0;
		
		if(promo == null){
			itemPromoTotal = count * productSelected.getPrice();
		} else if(promo.getPromo_code().matches("PayXforY")){
			int x = count / promo.getItemCount();
			double promoItemPrice = productSelected.getPrice() * x * promo.getPromoItem_Count();
			double remainingItemPrice =  productSelected.getPrice() * (count % promo.getItemCount());
			itemPromoTotal = promoItemPrice+ remainingItemPrice;
		} else if (promo.getPromo_code().matches("PriceforMore")){
			if (count > promo.getItemCount()){
				itemPromoTotal = count * promo.getPromoPrice();
			}
			else {
				itemPromoTotal = count * productSelected.getPrice();
			}
		} else if (promo.getPromo_code().matches("FreebieItem")){
			freebeeItem.add(promo.getFreebieProductCode());
			freebeeItem.add(count* promo.getFreebieProductCount());
			
			itemPromoTotal = count * productSelected.getPrice();
		}		
		
		return itemPromoTotal;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Map> items(){
		List<Map> itemList = new ArrayList<>();
		
		boolean freebieExistInCart = false;
		for(String arrProductCode : productArrangent){
			Map<String, Object> itemNameCount = new HashMap<>();
			
			if (itemMap.containsKey(arrProductCode)){
				if (arrProductCode.matches((String) freebeeItem.get(0))){
					freebieExistInCart = true;
					int itemCount = itemMap.get(arrProductCode) + (int)freebeeItem.get(1);
					itemNameCount.put(productMap.get(arrProductCode).getProduct_name(), itemCount);
					itemList.add(itemNameCount);
				} else {
					itemNameCount.put(productMap.get(arrProductCode).getProduct_name(),itemMap.get(arrProductCode));
					itemList.add(itemNameCount);
				}
			}			
		}
		if(!freebieExistInCart){
			Map<Object, Object> freebieNameCount = new HashMap<>();
			freebieNameCount.put(productMap.get(freebeeItem.get(0)).getProduct_name(), freebeeItem.get(1));
			itemList.add(freebieNameCount);
		}
		
		return itemList;
	}
	
	
}
