package isketch.tests;

public class noobTest {
	public static void main (String args[]){
		char[] nomChars = {'j', 'e', 'r', 'o', 'm', 'e'};
		String nomString = "jerome";
		System.out.println("char[] = "+nomChars);
		System.out.println("char[] new String = "+new String(nomChars));
		System.out.println("String = "+nomString);
		
		if(nomString.equals(new String(nomChars))) System.out.println("marche !");
		else System.out.println("marche pas !");
	}
}
