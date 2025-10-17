// Demonstrates S (Single Responsibility), O (Open/Closed), and L (Liskov Substitution) principles

// S: Single Responsibility Principle
// Each class should have only one reason to change.
class ReportPrinter {
    public void print(String report) {
        System.out.println("Printing report: " + report);
    }
}

class ReportSaver {
    public void save(String report) {
        System.out.println("Saving report: " + report);
    }
}

// O: Open/Closed Principle
// Classes should be open for extension, but closed for modification.
interface Shape {
    double area();
}

class Rectangle implements Shape {
    private double width;
    private double height;
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public double area() {
        return width * height;
    }
}

class Circle implements Shape {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    public double area() {
        return Math.PI * radius * radius;
    }
}

// L: Liskov Substitution Principle
// Subtypes must be substitutable for their base types.
class Bird {
    public void fly() {
        System.out.println("Bird is flying");
    }
}

class Sparrow extends Bird {
    public void fly() {
        System.out.println("Sparrow is flying");
    }
}

class Main {
    public static void main(String[] args) {
        // S: Single Responsibility
        ReportPrinter printer = new ReportPrinter();
        ReportSaver saver = new ReportSaver();
        printer.print("Lab 1 Report");
        saver.save("Lab 1 Report");

        // O: Open/Closed
        Shape rect = new Rectangle(3, 4);
        Shape circ = new Circle(5);
        System.out.println("Rectangle area: " + rect.area());
        System.out.println("Circle area: " + circ.area());

        // L: Liskov Substitution
        Bird bird = new Sparrow();
        bird.fly();
    }
}
