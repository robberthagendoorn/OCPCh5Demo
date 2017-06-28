import java.util.*;

public class Main {
	public static void main(String... args) {
		View view = new View();
		Controller cont = new Controller(view);
		cont.start();
	}
}
		
