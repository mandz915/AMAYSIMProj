package com.amysim.cart;

public class Coupon {
	
	private String couponCode;
	private String couponDescription;
	private double couponMultiplier;
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCouponDescription() {
		return couponDescription;
	}
	public void setCouponDescription(String couponDescription) {
		this.couponDescription = couponDescription;
	}
	public double getCouponMultiplier() {
		return couponMultiplier;
	}
	public void setCouponMultiplier(double multiplier) {
		this.couponMultiplier = multiplier;
	}

}
