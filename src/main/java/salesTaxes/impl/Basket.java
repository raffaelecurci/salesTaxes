package salesTaxes.impl;

import salesTaxes.exc.UnclosedBasketException;

public class Basket {
	private int basketNumber;
	private StringBuilder basketItems;
	private double total;
	private double basketSalesTaxes;
	// LinkedList<Item> itemBasketList;
	private boolean closed;

	/**
	 * Create a Basket instance without set his basket sequence number.
	 */
	public Basket() {
		basketItems = new StringBuilder();
		total = 0.0;
		basketSalesTaxes = 0.0;
		// itemBasketList=new LinkedList<Item>();
		closed = false;
	}

	/**
	 * Create a Basket instance setting the basket sequence number for this
	 * object.
	 * @param i the basket sequence number.
	 */
	public Basket(int i) {
		// TODO Auto-generated constructor stub
		basketItems = new StringBuilder();
		total = 0.0;
		basketSalesTaxes = 0.0;
		// itemBasketList=new LinkedList<Item>();
		closed = false;
		this.setBasketNumber(i);
	}

	/**
	 * This method finalize the basket adding his total items costs and Sales
	 * Taxes.
	 */
	public void closeBasket() {
		this.basketItems.append("Sales Taxes: " + formatDecimals(getBasketSalesTaxes()) + "\r\n" + "Total: "
				+ formatDecimals(getBasketTotal()) + "\r\n\r\n");
		closed = true;
	}
	
	//update total basket cost adding a the cost of an item taxs included
	private void addItemToTotal(double itemTotal) {
		this.total += itemTotal;
		this.total=(Math.round((this.total)*100)/ 100.0);
	}

	/**
	 * Set the basket serial number for the instance that call it.
	 * @param basketNumber an integer representing his sequence number*/
	public void setBasketNumber(int basketNumber) {
		this.basketNumber = basketNumber;
	}

	/*
	 * Add an item Sale Tax to the basket total Ssale Tax.
	 * param basketItemSaleTax: a double representing the Sale Tax amount to add*/
	private void addBasketItemSaleTax(double basketItemSaleTax) {
		this.basketSalesTaxes += basketItemSaleTax;
		this.basketSalesTaxes=(Math.round((this.basketSalesTaxes)*100)/ 100.0);
	}

	/**
	 * Add an Item object to the basket and update the total basket costs an taxes with focus on Sales Taxes for items in the basket
	 * @param item a fully configured item to add to the basket
	 * */
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		double itemTotalCost = (Math.round((item.getTotalTaxAmount() + item.getGoodValue())*100)/ 100.0) ;
		// itemBasketList.add(item);
		addBasketItemSaleTax(Math.round(item.getTotalTaxAmount()*100)/ 100.0);
		addItemToTotal(itemTotalCost);
		this.basketItems.append("1 " + item.getName() + ": " + formatDecimals(itemTotalCost) + "\r\n");
	}
	
	/**
	 * @return a double representing the basket total cost taxes included.
	 * */
	public double getBasketTotal() {
		// TODO Auto-generated method stub
		return this.total;
	}
	
	/**
	 * @return a String representation of all the items in the basket in the following form:<br>
	 * [quantity] [Item name]: [full item cost tax included]
	 * */
	public String getBasketItems() {
		// TODO Auto-generated method stub
		return basketItems.toString();
	}
	
	/**
	 * @return a double representing the total of Sales Taxes for the basket.
	 * */
	public double getBasketSalesTaxes() {
		// TODO Auto-generated method stub
		return basketSalesTaxes;
	}
	
	/**
	 * @return an integer representing the sequence number of the basket.
	 * */
	public int getBasketNumber() {
		// TODO Auto-generated method stub
		return this.basketNumber;
	}
	
	/**
	 * @return a human readable String representing the sequence number of the basket.
	 * */
	public String getBasketNumberString() {
		// TODO Auto-generated method stub
		return "Output " + this.getBasketNumber() + ":\r\n";
	}
	
	/**
	 * @param d a double to be formatted with two decimal digits.
	 * @return a String representing a double formatted to have two decimal digits.
	 * */
	public String formatDecimals(double d) {
		String num = "" + d;
		num = (num.substring(num.indexOf(".") + 1, num.length())).length() == 1 ? num + "0" : num;
		return num;
	}
	
	/**
	 * @return a human readable String representing all the basket items and costs with focus on total and Sale Taxes.
	 * @throws UnclosedBasketException if basket was not closed by method closeBasket()
	 * */
	public String toStringValue() throws UnclosedBasketException {
		if (closed)
			return getBasketNumberString() + getBasketItems();
		else
			throw new UnclosedBasketException();
	}
}
