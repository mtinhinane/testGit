package td;
import java.util.*;

public class Trouvtab {
	private static Scanner sc;

	public static void main(String [] args){
		char tab[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
		char rep;
		int pl =0;
		boolean b = false;
		int i;
		do{
		System.out.println(" saisisser une lettre svp");
		sc = new Scanner(System.in);
		
		char l = sc.nextLine().charAt(0);
		
		
		for( i=0; i<tab.length; i++){
			if(l ==tab[i]) {
				b= true;
				pl=i;
			}
		}
				
			if (b==true) System.out.println("le lettre "+ l+ "se trouve à la postion "+pl);
			else System.out.println(" votre lettre ne se trouve pas dans le tableau");
		
		
		System.out.println("Voulez-vous recommencer  o/n?");
		 rep= sc.nextLine().charAt(0);
		}while(rep =='o');
		
	}
	

}
