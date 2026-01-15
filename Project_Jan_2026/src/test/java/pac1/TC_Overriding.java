package pac1;
 
class animal
{
	void sound()
	{
		System.out.println("animal makes a sound");
	}
}
class cat extends animal
{
	@Override
	void sound()
	{
		System.out.println("cat makes sound");
	}
	
}
 
 
 
public class TC_Overriding {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			animal a=new cat();
			a.sound();		
		
		
	}
}