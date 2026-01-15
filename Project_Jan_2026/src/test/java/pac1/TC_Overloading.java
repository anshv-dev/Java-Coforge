package pac1;
class car{
	int noofcylinders;
	int noofvalves;
	int enginepower;
	boolean ispoersteering;
    
	car(){
		noofcylinders=3;
		noofvalves=4;
		enginepower=46;
		ispoersteering=false;
	}
	car(boolean ispoersteering){
		this.ispoersteering=ispoersteering;
	}
	
	car(int noofcylinders,int noofvalves,int enginepower){
		this.noofcylinders=noofcylinders;
		this.noofvalves=noofvalves;
		this.enginepower=enginepower;
		this.ispoersteering=true;
	}
	
}
public class TC_Overloading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		car obj1=new car();
		System.out.println(obj1.noofcylinders);
		
		car obj2=new car(true);
		System.out.println("ispoersteering:"+obj2.ispoersteering);
		
		car obj3=new car(4,50,4);
		System.out.println("noofcylinders:"+obj3.noofcylinders);
		System.out.println("noofcylinders:"+obj3.noofvalves);
		System.out.println("noofcylinders:"+obj3.enginepower);
		System.out.println("ispoersteering:"+obj3.ispoersteering);
	}

}
