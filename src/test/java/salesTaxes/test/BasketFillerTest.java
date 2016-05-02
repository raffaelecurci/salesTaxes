package salesTaxes.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <p>
 * Unit test for <b>BasketFiller</b>.
 * </p>
 * <p>
 * This class is intended for test purpose of BasketFiller Object.
 * </p>
 */

import org.junit.Test;

import salesTaxes.exc.UnclosedBasketException;
import salesTaxes.impl.Basket;
import salesTaxes.impl.BasketFiller;

public class BasketFillerTest {
	@Test
	public void trycreateBasketFiller() throws Exception, UnclosedBasketException {
		BasketFiller bf = new BasketFiller( "test.txt","itemCategory.txt");
		Basket tmpBasket;
		String output="";

		while ((tmpBasket = bf.getBasket()) != null) {
			output+=tmpBasket.toStringValue();
		}
		
		String check=	"Output 1:\r\n"+
						"1 book: 12.49\r\n"+
						"1 music CD: 16.49\r\n"+
						"1 chocolate bar: 0.85\r\n"+
						"Sales Taxes: 1.50\r\n"+
						"Total: 29.83\r\n"+
						"\r\n"+
						"Output 2:\r\n"+
						"1 imported box of chocolates: 10.50\r\n"+
						"1 imported bottle of perfume: 54.65\r\n"+
						"Sales Taxes: 7.65\r\n"+
						"Total: 65.15\r\n"+
						"\r\n"+
						"Output 3:\r\n"+
						"1 imported bottle of perfume: 32.19\r\n"+
						"1 bottle of perfume: 20.89\r\n"+
						"1 packet of headache pills: 9.75\r\n"+
						"1 imported box of chocolates: 11.85\r\n"+
						"Sales Taxes: 6.70\r\n"+
						"Total: 74.68\r\n"+
						"\r\n";

		assertThat(check, is(equalTo(output)));
	}
}
