package utility;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertTrue;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.CurrencyValidator;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.awt.List;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class StringUtility {
	
	
	public static void validateDateFormat(String date,String format){
		if (date==null)
			Assert.fail("The date is null");

		
		try{
			SimpleDateFormat simpledateformat=new SimpleDateFormat(format);
			simpledateformat.setLenient(false);

			simpledateformat.parse(date);

		}
		catch (ParseException e){
			System.out.println("VALIDATION POINT :   The Date " + date + "is not in the expected format "  + format);
			Assert.fail("VALIDATION POINT :   The Date " + date + "is not in the expected format "  + format);

		}
	}

	
	public static void ValidateDecimalPlaces(String num, int numberofDecimalPlaces) {
		
		int i = num.lastIndexOf('.');
		if(i != -1 && num.substring(i + 1).length() == numberofDecimalPlaces) {
		    System.out.println("VALIDATION POINT : The number " + num + " has two digits after dot");
		}
		
		else {
			System.out.println("VALIDATION POINT : The number " + num + " does not has " +numberofDecimalPlaces+  " digits after dot");
			Assert.fail("The number " + num + " does not has " + numberofDecimalPlaces +  " digits after dot");

		}
	}
	
	
	public static void ValidateUSCurrency(String CurrencyData ) {
		
		Currency currency = Currency.getInstance(Locale.US);
		String symbol = currency.getSymbol();
	    System.out.println(symbol);
	
		if(CurrencyData.startsWith(symbol)){
		    System.out.println("Valid Currency ");
		}else{
		    System.out.println("InValid Currency");
		}
		
		BigDecimalValidator validator = CurrencyValidator.getInstance();
	    BigDecimal amount = validator.validate(CurrencyData, Locale.US);
	    System.out.println(amount);
	    assertNotNull(amount);
		
	}
	
	public static void ValidateWordAppearenceOrderInList(String Sentence) {
		

 
		
	}
}
