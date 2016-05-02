package salesTaxes.test;

/**
 * <p>
 * Unit test for <b>Basket</b>.
 * </p>
 * <p>
 * This class is intended for test purpose of Basket Object.
 * </p>
 */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import salesTaxes.exc.UnclosedBasketException;
import salesTaxes.impl.Basket;
import salesTaxes.impl.Item;

public class BasketTest {
	@Test
	public void trycreateBasket() {
		Basket basket = new Basket(1);
		basket.addItem(new Item("book", true, false, 12.49));// item name, saleTaxExemption, importTax, goodValue
		basket.addItem(new Item("music CD", false, false, 14.99));
		basket.addItem(new Item("chocolate bar", true, false, 0.85));
		basket.closeBasket();
		

		String check = 	"Output 1:\r\n" + 
						"1 book: 12.49\r\n" + 
						"1 music CD: 16.49\r\n" + 
						"1 chocolate bar: 0.85\r\n"+
						"Sales Taxes: 1.50\r\n" + 
						"Total: 29.83\r\n\r\n";
		
		
		
		String basketCheck=basket.getBasketNumberString()+basket.getBasketItems();
		assertThat(check, is(equalTo(basketCheck)));
	}
	@Test
	public void trycreateBasketAndTestFormat() {
		Basket basket = new Basket();
		basket.setBasketNumber(1);
		basket.addItem(new Item("book", true, false, 12));// item name, saleTaxExemption, importTax, goodValue
		basket.addItem(new Item("music CD", false, false, 14.99));
		basket.addItem(new Item("chocolate bar", true, false, 0.85));
		basket.closeBasket();
		
		String check = 	"Output 1:\r\n" + 
						"1 book: 12.00\r\n" + 
						"1 music CD: 16.49\r\n" + 
						"1 chocolate bar: 0.85\r\n"+
						"Sales Taxes: 1.50\r\n" + 
						"Total: 29.34\r\n\r\n";
		
		
		
		String basketCheck="";
		try {
			basketCheck = basket.toStringValue();
		} catch (UnclosedBasketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertThat(check, is(equalTo(basketCheck)));
	}
	
	@Test(expected = UnclosedBasketException.class)
	public void trycreateBasketAndRiseUnclosedBasketException() throws UnclosedBasketException {
		Basket basket = new Basket();
		basket.setBasketNumber(1);
		basket.addItem(new Item("book", true, false, 12));// item name, saleTaxExemption, importTax, goodValue
		basket.addItem(new Item("music CD", false, false, 14.99));
		basket.addItem(new Item("chocolate bar", true, false, 0.85));
		
		basket.toStringValue();
	}
}
