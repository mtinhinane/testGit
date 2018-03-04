package td;

import java.util.*;

public class Conversion {
	public static void main(String []args){
		Scanner sc = new Scanner(System.in);
		double convert, toconvert;
		char rep = ' ';
		int c;
	System.out.println("CONVERTISSEUR DEGRES CELSIUS");
	System.out.println("------------------------------");
	

	do{
	
	do{
	System.out.println("choisissez le mode de converions");
	System.out.println("1 - Celsus - Fahrenheit");
	System.out.println("2 - Fahrenheit -Celsus");

	c = sc.nextInt();
	
	if (c != 1 && c!= 2) {
		System.out.println(" erreur veillez saisir 1 ou 2");
	}
	}while(c != 1 && c!= 2);
		
	
	System.out.println("introduire la variable à convertir");
	toconvert = sc.nextDouble();
	if (c==1){
	
	convert = ((9.0/5.0) * toconvert) + 32.0;
	System.out.println(toconvert +"°C correspond à : " + convert + "°F");
	}else {
		 convert = ((toconvert- 32) * 5) / 9;
		System.out.println(toconvert +"°F correspond à : " + convert + "°C1"
				+ "");
	}

	
		do{
		System.out.println("Voulez-vous recommencer  o/n?");
		rep = sc.nextLine().charAt(0);
		
		}while(rep!='o'&& rep!= 'n');
		
	} while (rep =='o');
}
}