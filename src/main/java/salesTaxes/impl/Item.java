package salesTaxes.impl;

import salesTaxes.exc.UnsettedTaxesException;

/**
 * <p>
 * A class that define basic informations related to a salable product 
 * </p>
 */
public class Item {
	
	private String name;
	private long goodValue;//good value expressed in cents
	private long importTax;//good Import Tax value expressed in cents
	private long baseSaleTax;//good Base Sale Tax value expressed in cents
	private boolean baseSaleTaxExemption;// Base Sale Tax Exemption flag
	private boolean imported;// Import Tax flag
	private boolean baseSaleTaxExemption_check;// flag for check if baseSaleTaxExemption flag was set
	private boolean imported_check;// flag for check if imported flag was set 
	
	/**<p>Item constructor
	 * <br>return an unparameterized Item</p>
	 */
	public Item() {
		// TODO Auto-generated constructor stub
		baseSaleTaxExemption_check=false;
		imported_check=false;
	}
	
	//check if BaseSaleTaxExemption was verified
	private boolean isBaseSaleTaxExemption_check() {
		return baseSaleTaxExemption_check;
	}

	//check if Import tax was verified
	private boolean isImported_check() {
		return imported_check;
	}


	/**<p>Parameterized Item constructor<br>return a fully price and taxes configured Item</p>
	 * @param name a String representing the name of this good
	 * @param saleTaxExemption a boolean flag stating if good is Base Sale Tax exempt
	 * @param importedGood a boolean flag stating if good is Import Tax exempt
	 * @param goodValue a double representing the value this good
	 * 
	 */
	public Item(String name, boolean saleTaxExemption, boolean importedGood, double goodValue) {
		setName(name);
		setBaseSaleTaxExemption(saleTaxExemption);
		setImported(importedGood);
		setGoodValue(	goodValue	);
		baseSaleTaxExemption_check=true;
		imported_check=true;
		calculateTaxes();
	}

	/**<p>Getter method for item good Name</p>
	 * 
	 * @return a String representing the good name
	 */
	public String getName() {
		return name;
	}
	
	/**<p>Setter method for item good Name</p>
	 * @param name a String representing this good
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**<p>Getter method for item good value</p>
	 * 
	 * @return a double representing the good value
	 */
	public double getGoodValue() {
		
		return goodValue/100.0;
	}
	
	/**<p>Setter method for item good value</p>
	 * @param goodValue a double representing the value this good
	 */
	public void setGoodValue(double goodValue) {
		this.goodValue = Math.round(goodValue*100);
	}
	
	/**<p>Getter method for item good Import Tax</p>
	 * 
	 * @return a double representing the Import Tax value of this good.
	 * 	<ul><li>0.0 if the good not imported</li><li>a value bigger than 0 if the good was imported</li></ul>
	 * @throws UnsettedTaxesException if set taxes flags are not checked.
	 */
	public double getImportTax()throws UnsettedTaxesException {
		if(isBaseSaleTaxExemption_check() && isImported_check()){
			return importTax/100.0;
		}else
			throw new UnsettedTaxesException();
	}
	
	/**<p>Setter method for item good Import Tax</p>
	 * @param importTax a double representing the Import Tax value of this good
	 */
	private void setImportTax(long importTax) {
		this.importTax =this.isImported()?importTax:0;
	}

	/**<p>Getter method for item good Sale Tax</p>
	 * 
	 * @return a double representing the Sale Tax value of this good.
	 * 	<ul><li>0.0 if the good is exempt</li><li>a value bigger than 0 corresponding to 10% of the good value</li></ul>
	 * @throws UnsettedTaxesException if set taxes flags are not checked.
	 */
	public double getBaseSaleTax() throws UnsettedTaxesException{
		if(isBaseSaleTaxExemption_check() && isImported_check()){
			return baseSaleTax/100.0;
		}else{
			throw new UnsettedTaxesException();
		}
	}

	/*Setter method for item good Sale Tax
	 * @param baseSaleTax a double representing the Sale Tax value of this good
	 * This method must be called just after object creation.
	 */
	private void setBaseSaleTax(long baseSaleTax) {
		this.baseSaleTax = isBaseSaleTaxExempt()?0:baseSaleTax;
	}
	
	/*
	 * Check if good is exempt from Base Sale Tax
	 * @return true if good is exempt, false otherwise.
	 * */
	public boolean isBaseSaleTaxExempt() {
		return baseSaleTaxExemption;
	}
	
	/**
	 * <p>Define if good is exempt from Base Sale Tax</p>
	 * @param baseSaleTaxExemption true if good is exempt, false otherwise.
	 * This method must be called just after object creation.
	 * */
	public void setBaseSaleTaxExemption(boolean baseSaleTaxExemption) {
		baseSaleTaxExemption_check=true;
		this.baseSaleTaxExemption = baseSaleTaxExemption;
	}
	
	/**
	 * <p>Check if good is imported</p>
	 * @return true if good is imported, false otherwise.
	 * */
	public boolean isImported() {
		return imported;
	}
	
	/**
	 * <p>Define if good is imported</p>
	 * @param imported true if good is imported, false otherwise.
	 * This method must be called just after object creation.
	 * */
	public void setImported(boolean imported) {
		imported_check=true;
		this.imported = imported;
	}
	
	/*
	 * Calculate taxes not rounded to lowest currency coin related to the good considering if is imported good and is exempt from Base Sale Tax
	 * return an array of 2 long. First position is Base Sale Tax and second Import Tax.
	 * */
	private long[] calculateTaxesRaw(){
		
		return new long[]{((goodValue*10)/100),((goodValue*5)/100)};
	}
	
	/**
	 * <p>Calculate and set taxes rounded to lowest currency coin related to the good considering if is imported good and is exempt from Base Sale Tax.
	 * <br><b>Use this method always before call any method that return tax amount</b></p>
	 * @throws UnsettedTaxesException if set taxes flags are not checked.
	 * */
	public void calculateTaxes() throws UnsettedTaxesException{
		long[] rawTaxes=calculateTaxesRaw();
		if(isBaseSaleTaxExemption_check() && isImported_check()){
			setBaseSaleTax(
					rawTaxes[0]%5<=2 && Integer.parseInt((""+rawTaxes[0]).substring((""+rawTaxes[0]).length()-1))<=5?
						rawTaxes[0]-(rawTaxes[0]%5)
						:
						rawTaxes[0]+(5-rawTaxes[0]%5)
					);
			setImportTax(
					rawTaxes[1]%5<=2 && Integer.parseInt((""+rawTaxes[1]).substring((""+rawTaxes[1]).length()-1))<=5?
							rawTaxes[1]-(rawTaxes[1]%5)
							:
							rawTaxes[1]+(5-rawTaxes[1]%5)
					);
		}else
			throw new UnsettedTaxesException();
	}
	
	/**
	 * Calculate sum of taxes applicable to the good considering based on the Item taxes configuration
	 * @return a double representing the sum of Base Sale Tax and Import Tax.
	 * @throws UnsettedTaxesException if set taxes flags are not checked.
	 * */
	public double getTotalTaxAmount()throws UnsettedTaxesException{
		if(isBaseSaleTaxExemption_check() && isImported_check()){
			return Math.round((getBaseSaleTax()+getImportTax())*100)/ 100.0;
		}else
			throw new UnsettedTaxesException();
	} 
}
