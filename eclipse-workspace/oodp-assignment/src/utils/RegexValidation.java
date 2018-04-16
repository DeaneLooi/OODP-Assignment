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
	
	public static final String tableGuest = "%-10s %-30s %-20s %-20s %-10s %-15s %-50s %-25s %-20s \n";
	//System.out.format(RegexValidation.tableGuest, "S/No.", "Name", "Email", "Country", "Gender", "Passport", "Address","Phone Number", "Credit Card Number");
	
	public static final String tableReservation = "%-20s %-15s %-15s %-30s %-30s %-15s %-15s %-15s \n";
	//System.out.format(RegexValidation.tableReservation, "Reservation Code", "Guest Passport", "Room Number", "Check In Date",	"Check Out Date", "Status", "No. of Adults", "No. of Children");
	public static final String tableService = "%-15s %-15s %-20s %-20s %-15s %-40s \n";
	//System.out.format(RegexValidation.tableService, "Service ID", "Room No.", "Reservation Code", "Item Menu","Status", "Remarks");
	public static final String tableItem = "%-10s %-20s %-10s %-40s\n";
	//System.out.format(RegexValidation.tableItem, "S/No.", "Menu Item", "Price", "Description");

	public static final String tableServiceBill = "%-10s %-30s %-10s\n";
	//	System.out.format(RegexValidation.tableServiceOrder, "S/No.", "Name", "Price ($)");


}
