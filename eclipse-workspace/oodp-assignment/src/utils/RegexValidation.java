package utils;


/**
 * 
 * 	Contains all regular expression for validation of input
 *
 */
public class RegexValidation {

	public static final String phoneNumberRegex = "((?:\\+|00)[17](?: |\\-)?|(?:\\+|00)[1-9]\\d{0,2}(?: |\\-)?|(?:\\+|00)1\\-\\d{3}(?: |\\-)?)?(0\\d|\\([0-9]{3}\\)|[1-9]{0,3})(?:((?: |\\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\\-)[0-9]{3}(?: |\\-)[0-9]{4})|([0-9]{7}))";
	
	public static final String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public static final String dateRegex = "(\\d\\d\\/\\d\\d\\/\\d\\d\\d\\d)";
	
	public static final String creditCardRegex = "^4[0-9]{12}(?:[0-9]{3}){0,2}$";

	public static final String whiteSpaceRegex = "[\\s:\\-]";

}
