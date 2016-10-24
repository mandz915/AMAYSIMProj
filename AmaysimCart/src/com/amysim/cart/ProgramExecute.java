package com.amysim.cart;

public class ProgramExecute {

	public static void main(String[] args) {
		
		ShoppingCart cart = new ShoppingCart();
		cart.initialize();
		cart.add("ult_large");
		cart.add("ult_large");
		cart.add("ult_medium");
		cart.add("ult_medium");
		cart.add("ult_small");
		cart.add("ult_small");
		cart.add("ult_small","I<3AMAYSIM");
		//shoppingCart.add("1gb");		
		
		cart.computeOrder();
		System.out.println(cart.total);
		System.out.println(cart.items());


	}

}
