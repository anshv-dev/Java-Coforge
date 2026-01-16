//package pac1;
//
//import java.util.regex.Pattern;
//import java.util.regex.Matcher;
//public class TC_Regular_Expression {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String input="1234422";
//		String regex="\\d+";
//        Pattern p=Pattern.compile(regex);
//	    Matcher m=p.matcher(input);
//	    boolean result=m.matches();
//	    if(result==true) {
//	    	System.out.println("Valid Number");
//	    }else {
//	    	System.out.println("Invalid Number");
//	    }
//	    
//	    String input1="Shop, Map,Hopping,Chopping";
//	    Pattern pattern=Pattern.compile("hop");
//	    Matcher match=pattern.matcher(input1);
//	    System.out.println(match.matches());
//	    while(match.find()) {
//	    	System.out.println(match.group()+":"+match.start()+":"+match.end());
//	    }
//	    
//	    String phonemnumer ="861";
//		String regex1="\\d{3}";
// 
//		Pattern p1=Pattern.compile(regex1);
//		Matcher m1=p1.matcher(phonemnumer);
//		boolean result1=m1.matches();
//		if(result1==true)
//		{
//			System.out.println("Valid phone number");
//		}
//		
//		else
//		{
//			System.out.println("Invalid phone Number");
//		}
//		
//		
//		String input2 ="Exo1";
//		String regex2="^[A-Z][a-z]+\\d$";
//		 
//		Pattern p2=Pattern.compile(regex2);
//		Matcher m2=p2.matcher(input2);
//		boolean result2=m2.matches();
//		if(result2==true)
//		{
//		System.out.println("matching");
//		}
//		 
//		else
//		{
//		System.out.println("not matching");
//		}
//		
//		String email="test.user@gmail.com";
//		String emailregex2="^[A-Za-z0-9._%+-@^[A-Za-z0-9._%+-]+\\.[A-Za-z][{2,}$]";
//	    
//		Pattern emailp2=Pattern.compile(emailregex2);
//		Matcher emailm2=emailp2.matcher(email);
//		boolean emailresult2=emailm2.matches();
//		if(emailresult2==true) {
//			System.out.println("email matching");
//		}else {
//			System.out.println("email not matching");
//		}
//	}
//}

package pac1;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
 
public class TC_Regular_Expression {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input="12345";
		String regex="\\d+";
   Pattern p= Pattern.compile(regex);
		   Matcher m= p.matcher(input);
		   boolean result =m.matches();
		   if(result==true) {
			   System.out.println("Valid Number");
		   }else {
			   System.out.println("Invalid Number");
		   }

		   String email ="test.user@gmail.com";
		   String emailregex2="^[A-Za-z0-9._%+-]+@[A-Za-z0-9._%+-]+\\.[A-Za-z]{2,}$";
		   Pattern emailp2=Pattern.compile(emailregex2);
		   Matcher emailm2=emailp2.matcher(email);
		   boolean emailresult2=emailm2.matches();
		   if(emailresult2==true)
		   {
		   System.out.println("email matching");
		   }
		   else
		   {
		   System.out.println("email not matching");
		   }

		   String phonemnumer ="8612345439";
			String regex1="\\d{10}";
	Pattern p1=Pattern.compile(regex1);
	Matcher m1=p1.matcher(phonemnumer);
	boolean result1=m1.matches();
	if(result1==true)
	{
		System.out.println("Valid phone number");
	}
	else
	{
		System.out.println("Invalid phone Number");
	}

		   String input1="Shop, Mop,Hopping,Chopping";
		   Pattern pattern = Pattern.compile("hop");
		   Matcher match=pattern.matcher(input1);
		   System.out.println(match.matches());
		   while(match.find()) {
			   System.out.println(match.group()+":"+match.start()+":"+match.end());
		   }
		   
		   
		   String email1 ="test.user@gmail.com";
		   String emailregex21="^[A-Za-z0-9._%+-]+@[A-Za-z0-9._%+-]+\\.[A-Za-z]{2,}$";
		    
		   Pattern emailp21=Pattern.compile(emailregex21);
		   Matcher emailm21=emailp21.matcher(email1);
		   boolean emailresult21=emailm21.matches();
		   if(emailresult21==true)
		   {
		   System.out.println("email matching");
		   }
		    
		   else
		   {
		   System.out.println("email not matching");
		   }
	}
 
}
