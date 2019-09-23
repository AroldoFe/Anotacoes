import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> tame = new ArrayList<String>();
		tame.add("Puta que pariu");
		
		if(tame.contains("Puta que pariu")) {
			tame.set(tame.indexOf("Puta que pariu"), "baby shark");
		}
		if(tame.contains("baby shark")) {
			System.out.println(tame.indexOf("baby shark"));
		}
	}

}
