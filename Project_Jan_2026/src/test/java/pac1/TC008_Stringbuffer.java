package pac1;

public class TC008_Stringbuffer {
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("Hello");
		System.out.println(sb.reverse().insert(3, "----"));
		
		System.out.println(sb.append("   JAVA"));
		
	}
 
}
