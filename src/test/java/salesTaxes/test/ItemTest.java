package salesTaxes.test;

/**
 * <p>
 * Unit test for <b>Item</b>.
 * </p>
 * <p>
 * This class is intended for test purpose of Item Object.
 * </p>
 */

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import salesTaxes.exc.UnsettedTaxesException;
import salesTaxes.impl.Item;

public class ItemTest {
	@Test
	public void trycreateItem() {
		Item item = new Item();
		item.setName("chocolate bar");
		item.setBaseSaleTaxExemption(true);
		item.setImported(false);
		item.setGoodValue(0.85);
		item.calculateTaxes();
		String check = item.getName() + " " + item.getGoodValue() + " " + item.getImportTax() + " "	+ item.getBaseSaleTax();
		String itemCheck = "chocolate bar 0.85 0.0 0.0";
		assertThat(check, is(equalTo(itemCheck)));
	}

	@Test
	public void trycreateItemImplicit() {
		Item item = new Item("chocolate bar", true, false, 0.85);
		String check = item.getName() + " " + item.getGoodValue() + " " + item.getImportTax() + " "
				+ item.getBaseSaleTax();
		String itemCheck = "chocolate bar 0.85 0.0 0.0";
		assertThat(check, is(equalTo(itemCheck)));
	}
	
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnNoBaseSalesTax() {
		Item item = new Item();
//		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(15.0);
		item.getTotalTaxAmount();
	}
	
	
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnNoImportTax() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
//		item.setImported(true);
		item.setGoodValue(15.0);
		item.getTotalTaxAmount();
	}
	
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnNoBaseSalesTaxOnGetImportTax() {
		Item item = new Item();
//		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(15.0);
		item.getImportTax();
	}
	
	
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnNoImportTaxOnGetImportTax() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
//		item.setImported(true);
		item.setGoodValue(15.0);
		item.getImportTax();
	}
	
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnNoBaseSalesTaxOnGetBaseSaleTax() {
		Item item = new Item();
//		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(15.0);
		item.getBaseSaleTax();
	}
	
	
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnNoImportTaxOnGetBaseSaleTax() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
//		item.setImported(true);
		item.setGoodValue(15.0);
		item.getBaseSaleTax();
	}
	
	
	
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnGetTotalTaxAmountNoImportTax() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
//		item.setImported(true);
		item.setGoodValue(15.0);
		item.calculateTaxes();
	}
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnGetTotalTaxAmountNoBaseSalesTax() {
		Item item = new Item();
//		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(15.0);
		item.calculateTaxes();
	}
	
	/*@Test
	public void trycreateItemImplicit2() {
		Item item = new Item("bottle of perfume", false, false, 18.99);
		String check = item.getName() + " " + item.getGoodValue() + " " + item.getImportTax() + " "
				+ item.getBaseSaleTax();
		String itemCheck = "bottle of perfume 18.99 0.0 1.9";
		assertThat(check, is(equalTo(itemCheck)));
	}
	
	@Test
	public void trycreateItemImplicit3() {
		Item item = new Item("bottle of perfume", false, false, 18.99);
		String check = item.getName() + " " + (Math.round((item.getTotalTaxAmount() + item.getGoodValue())*100)/ 100.0) + " " + item.getImportTax() + " "
				+ item.getBaseSaleTax();
		String itemCheck = "bottle of perfume 20.89 0.0 1.9";
		assertThat(check, is(equalTo(itemCheck)));
	}
	
	@Test
	public void trycreateItemImplicit4() {
		Item item = new Item("imported box of choccolates", true, true, 11.25);
		String check = item.getName() + " " + (Math.round((item.getTotalTaxAmount() + item.getGoodValue())*100)/ 100.0) + " " + item.getImportTax() + " "
				+ item.getBaseSaleTax();
		String itemCheck = "imported box of choccolates 11.85 0.6 0.0";
		assertThat(check, is(equalTo(itemCheck)));
	}

	@Test
	public void tryDefineTax() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(100);
		item.calculateTaxes();
		String check = item.getGoodValue() + " " + item.getImportTax() + " " + item.getBaseSaleTax();
		String itemCheck = "100.0 5.0 10.0";
		assertThat(check, is(equalTo(itemCheck)));
	}

	@Test
	public void tryDefineItemThatRoundTo15SaleTax() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
		item.setImported(false);
		item.setGoodValue(14.99);
		item.calculateTaxes();
		String check = item.getGoodValue() + " " + item.getImportTax() + " " + item.getBaseSaleTax();
		String itemCheck = "14.99 0.0 1.5";
		assertThat(check, is(equalTo(itemCheck)));
	}

	@Test
	public void tryDefineItemThatRoundTo145SaleTax() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(14.33);
		item.calculateTaxes();
		String check = item.getGoodValue() + " " + item.getImportTax() + " " + item.getBaseSaleTax();
		String itemCheck = "14.33 0.7 1.45";
		assertThat(check, is(equalTo(itemCheck)));
	}

	@Test
	public void tryDefineItemThatRoundTo075ImportTax() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(15.0);
		item.calculateTaxes();
		String check = item.getGoodValue() + " " + item.getImportTax() + " " + item.getBaseSaleTax();
		String itemCheck = "15.0 0.75 1.5";
		assertThat(check, is(equalTo(itemCheck)));
	}

	@Test
	public void tryDefineTotalTaxAmount() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(14.33);
		item.calculateTaxes();
		String check = item.getGoodValue() + " " + item.getTotalTaxAmount();
		String itemCheck = "14.33 2.15";
		assertThat(check, is(equalTo(itemCheck)));
	}

	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnCaculateTaxes() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
//		item.setImported(true);
		item.setGoodValue(15.0);

		item.getBaseSaleTax();
		
	}
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnGetTotalTaxAmount() {
		Item item = new Item();
//		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(15.0);
		item.calculateTaxes();
		item.getTotalTaxAmount();
		
	}
	@Test
	public void tryDefineItemWhitoutRiseUnsettedTaxesExceptionOnCaculateTaxes() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(15.0);
		item.calculateTaxes();
		assertThat("OK", is(equalTo("OK")));
	}
	@Test
	public void tryDefineItemWhitoutRiseUnsettedTaxesExceptionOnGetTotalTaxAmount() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(15.0);
		item.getImportTax();
		item.getTotalTaxAmount();
		assertThat("OK", is(equalTo("OK")));
	}
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnBaseSaleTax() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
//		item.setImported(true);
		item.setGoodValue(15.0);
		item.getBaseSaleTax();
	}
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnBaseSaleTax1() {
		Item item = new Item();
//		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(15.0);
		item.getBaseSaleTax();
	}
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnGetImportTax() {
		Item item = new Item();
//		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(15.0);
		item.getImportTax();
	}
	
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemWhitoutRiseUnsettedTaxesExceptionOnGetTotalTaxAmount1() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
//		item.setImported(true);
		item.setGoodValue(15.0);
		item.getTotalTaxAmount();
	}
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemWhitoutRiseUnsettedTaxesExceptionOnGetTotalTaxAmount2() {
		Item item = new Item();
//		item.setBaseSaleTaxExemption(false);
		item.setImported(true);
		item.setGoodValue(15.0);
		item.getTotalTaxAmount();
	}
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemWhitoutRiseUnsettedTaxesExceptionOnGetTotalTaxAmount3() {
		Item item = new Item();
//		item.setBaseSaleTaxExemption(false);
//		item.setImported(true);
		item.setGoodValue(15.0);
		item.getTotalTaxAmount();
	}
	@Test(expected = UnsettedTaxesException.class)
	public void tryDefineItemAndRiseUnsettedTaxesExceptionOnGetImportTax2() {
		Item item = new Item();
		item.setBaseSaleTaxExemption(false);
//		item.setImported(true);
		item.setGoodValue(15.0);
		item.getImportTax();
	}*/
}
