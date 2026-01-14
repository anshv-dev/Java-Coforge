package pac1;

class Box{
	double width = 10;
	double height = 20;
	double depth = 10;
	
	double calvolume() {
		return width * height * depth ;
	}
}

public class TC003 {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Box obj = new Box();
		//obj.calvolume();
		System.out.println(obj.calvolume());
	}

}
