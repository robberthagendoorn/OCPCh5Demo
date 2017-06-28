import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.io.Console;

public class View {

	private Locale locale; 
	private ResourceBundle actions; 
	private ResourceBundle messages; 	

	public Locale getLocale() {
		return locale;
	}	
		
	public void setLocale(Locale locale) {
		this.locale = locale;
		actions = ResourceBundle.getBundle("Actions", locale);
		messages = ResourceBundle.getBundle("Messages", locale);
	}

	public View() {
		Locale locale = Locale.getDefault();
		this.setLocale(locale);
	}

	private void reloadBundle(ResourceBundle resourceBundle, String name) {
		resourceBundle = ResourceBundle.getBundle(name, locale);
	}

	public void print(Object obj) {
		System.out.println(obj);
	}

	public void emptyLine() {
		print("");
	}
	
	public void displayActions() {
		emptyLine();
		Arrays.asList(Action.values()).stream()
			.forEach((a) -> print(a.ordinal() + ". " + actions.getString(a.toString())));
	}

	public Action getUserInput() {
		Console cons = System.console();
		emptyLine();
		print(messages.getString("welcome"));
		emptyLine();
		String choice = cons.readLine();
		Action action = null;
		try {
			int i = Integer.parseInt(choice);
			action = Action.getAction(i);
		} catch(Exception e) {}
		return action;
	}

	public void showDetails() {
		LocalDateTime departureTime = LocalDateTime.of(2017, 7, 3, 11, 0);
		ZonedDateTime departure = ZonedDateTime.of(departureTime, ZoneId.of("Europe/Amsterdam"));
		ZonedDateTime arrival = departure.plusHours(8).withZoneSameInstant(ZoneId.of("US/Eastern"));
		String pattern = (getLocale().getCountry().equals("NL")) ? "dd/MM/yyyy hh:mm z" : "MM/dd/yyyy hh:mm z";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		emptyLine();
		print(messages.getString("detailsDeparture") + departure.format(formatter) + messages.getString("detailsArrival") + arrival.format(formatter)); 
	}

	public void printErrorMessage() {
		print(messages.getString("wrongInput"));
	}

	public void showPrice(double euroPrice, double dollarPrice) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		double price = (getLocale().getCountry().equals("NL")) ? euroPrice : dollarPrice; 
		emptyLine();
		print(messages.getString("price") + nf.format(price));
	}
}
