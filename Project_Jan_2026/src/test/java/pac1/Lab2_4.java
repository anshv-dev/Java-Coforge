// Interface
package pac1;
interface Shape {
    double area();
}
 
// Implementing interface
class Rectangle implements Shape {
 
    private double length;
    private double breadth;
 
    public Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }
 
    @Override
    public double area() {
        return length * breadth;
    }
}
 
// Base class
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}
 
// Derived class
class Dog extends Animal {
 
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}
 
// Main class
public class Lab2_4 {
 
    public static void main(String[] args) {
 
        // Interface reference
        Shape s = new Rectangle(10, 5);
        System.out.println("Area of Rectangle: " + s.area());
 
        // Parent reference to child object (Polymorphism)
        Animal a = new Dog();
        a.sound();
    }
}