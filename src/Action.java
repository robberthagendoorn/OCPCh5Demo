import java.util.*;

public enum Action {
	BOOK, GET_DETAILS, CHANGE_LANGUAGE;

	public static Action getAction(int num) { 
		Optional<Action> action = Arrays.asList(Action.values()).stream()
			.filter(a -> a.ordinal() == num)
		  	.findFirst();
		return action.get();
	}
}
