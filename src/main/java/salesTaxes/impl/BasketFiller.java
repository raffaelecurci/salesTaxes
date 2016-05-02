package salesTaxes.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BasketFiller {
	BufferedReader is_itemInput;
	BufferedReader is_itemCategory;
	private StringBuffer text;
	
	/**<p>BasketFiller creates the InputStreams for reading input and Sales tax applicable category from two input file</p>
	 * @param itemInput items basket file
	 * @param itemCategory items sales tax definition definition file
	 * @throws IOException in case of missing input
	 */
	public BasketFiller(String itemInput, String itemCategory) throws IOException {
		// TODO Auto-generated constructor stub
		is_itemInput=new BufferedReader(new FileReader(getFile(itemInput)));
		is_itemCategory=new BufferedReader(new FileReader(getFile(itemCategory)));
		text=new StringBuffer();
		String line;
		
		while((line=is_itemCategory.readLine())!=null){//get items that need sales tax applicable in contructor
			if(line.contains("BaseSaleTaxApplicable")){
				text.append(line+"\r\n");
			}
		}
		is_itemCategory.close();
		
	}
	
	//check if sales tax is applicable by check presence of a mach in a text composed of only applicable sales tax items: text
	private boolean isSalesTaxesApplicable(String match){
		return text.toString().contains(match)?true:false;
	}
	/**
	 * Creates a basket based on reads of the input file
	 * @throws Exception in case of missing or bad formatted file
	 * @return a Basket object full of element and already closed*/
	public Basket getBasket() throws Exception {// item line must contain just one 'at' occurrence
		String line;
		Basket basket=null;
		String name;
		boolean _break = false,imported,salesTax;
		double goodValue;
		if(is_itemInput!=null){
			line = is_itemInput.readLine();
			if (line != null) {//get basket number and create basket
				basket=new Basket(Integer.parseInt(line.substring(line.indexOf(" ")+1,line.length()-1)));
				while (!_break) {
					line = is_itemInput.readLine();
					if (line != null && !line.equals("")) {
						name=line.substring(line.indexOf(" ")+1,line.indexOf(" at "));
						imported=line.contains("imported")?true:false;//check imported and saletax
						salesTax=isSalesTaxesApplicable(name);
						goodValue=Double.parseDouble(line.substring(line.indexOf(" at ")+4,line.length()));
						basket.addItem(new Item(name,!salesTax,imported,goodValue));// create item in basket
						if (line.equals("")) {
							_break = true;
							basket.closeBasket();
						}
					}else{
						_break = true;
						basket.closeBasket();
					}
				}
			}
			if (line == null){
				is_itemInput.close();
				is_itemInput=null;
			}
		}		
		return basket;
	}
	// create a file object from resource
	private File getFile(String file){
		ClassLoader classLoader = getClass().getClassLoader();
		return new File(classLoader.getResource(file).getFile());
	}
}
