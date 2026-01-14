package pac1;

public class Lab8 {
    enum Gender{
    	Male,Female
    }
    static class Person {
        String FirstName;
        String Lastname;
        Gender gender;
        String phoneNumber;
        public Person(String fn,String ln,Gender g,String ph) {
        	this.FirstName=fn;
        	this.Lastname=ln;
        	this.gender=g;
        	this.phoneNumber=ph;
        }
        
    	public void display() {
    		System.out.println(gender);
    	}
    	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Person Obj=new Person("Ansh","Verma",Gender.Male,"8279596843");
		 Obj.display();
	}

}
