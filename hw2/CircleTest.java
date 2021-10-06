class Circle
{
    static final double PI = 3.14159;
    double radius;
    Circle(double newRadius)
    {
        radius = newRadius;
    }
    double area()
    {
        return PI * radius * radius;
    }
    double circumference()
    {
        return 2.0 * PI * radius;
    }
}

class ACircle
    extends Circle
{
    double aData;
    ACircle(double newRadius, double aValue)
    {
        super(newRadius);
        aData = aValue;
    }
    double area()
    {
        return PI * radius * radius / aData;
    }
}

class BCircle
    extends Circle
{
    double bData;
    BCircle(double newRadius, double bValue)
    {
        super(newRadius);
        bData = bValue;
    }
    double circumference()
    {
        return 2.0 * PI * radius;
    }
}

class CCircle
    extends Circle
{
    double cData;
    CCircle(double newRadius, double cValue)
    {
        super(newRadius);
        cData = cValue;
    }
    double area()
    {
        return PI * radius * radius;
    }
    double circumference()
    {
        return 2.0 * PI * radius;
    }
}

class AACircle
    extends ACircle
{
    double aaData;
    AACircle(double newRadius, double aValue, double aaValue)
    {
        super(newRadius, aValue);
        aaData = aaValue;
    }
    double circumference()
    {
        return 2.0 * PI * radius;
    }
}

public class CircleTest
{
    static void print(String s)
    {
        System.out.println(s);
    }

    public static void main(String [] args)
    {
        Circle a[] = {new Circle(1.0), new ACircle(1000.0, 2.0),
                      new BCircle(10.0, 3.0), new CCircle(100.0, 4.0),
                      new AACircle(10000.0, 2.0, 3.0)};
        for (int i=0; i<a.length; i++)
        {
            print("Area = " + a[i].area());
            print("Circumference = " + a[i].circumference());
        }
    }
}
