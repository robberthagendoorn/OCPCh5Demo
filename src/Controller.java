import java.util.*;

public class Controller {

	private View view;
		
	public Controller(View View) {
		this.view = View;
	}

	public void start() {
		view.displayActions();
		Action action = view.getUserInput();
		performAction(action);
		start();
	}

	private void performAction(Action action) {
		if (action == null) {
			view.printErrorMessage();
		} else {
			switch (action) {
				case BOOK:
					bookFlight();
					break;
				case GET_DETAILS:
					view.showDetails();
					break;
				case CHANGE_LANGUAGE:
					changeLanguage();
					break;
			}
		}
	}

	private void changeLanguage() {
		Locale locale = (view.getLocale().getCountry().equals("NL")) ? 
			new Locale("en", "US") : new Locale("nl", "NL");
		view.setLocale(locale);
	}

	private void bookFlight() {
		double priceEuro = 1022.00;
		double priceDollar = 1160.00; 
		view.showPrice(priceEuro, priceDollar);
	}
}

