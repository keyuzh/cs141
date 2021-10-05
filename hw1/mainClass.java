class Shape
{
    String name;

    public Shape(String newName)
    {
        name = newName;
    }

    public double area()
    {
        // virtual, will be overridden in derived classes
        return 0.0;
    }

    public void draw()
    {
        System.out.println("Shape.draw() You should never see this.");
        // note if you copy&paste from the Internet, double quotes are Unicode, not ASCII
    }

    public void printName()
    {
        // virtual, will be overridden in derived classes
        return;
    }
}
           
class Triangle
    extends Shape
{
    private int myHeight, myBase;

    public Triangle(String name, int h, int b)
    {
        super(name);
        myHeight = h;
        myBase = b;
    }

    public double area()
    {
        return myHeight * myBase * 0.5;
    }	

    public void draw()
    {
        System.out.println("*    ");
        System.out.println("**   ");
        System.out.println("* *  ");
        System.out.println("*  * ");
        System.out.println("*****");
    }

    public void printName()
    {
        if (this.area() % 1 != 0)
        {
            // area is #.5
            System.out.println(String.format("%s(%d, %d) : %.1f", name, myHeight, myBase, this.area()));
        }
        else
        {
            // area is whole number
            System.out.println(String.format("%s(%d, %d) : %.0f", name, myHeight, myBase, this.area()));

        }
    }
}

class Circle
    extends Shape
{
    private int radius;

    public Circle(String name, int r)
    {
        super(name);
        radius = r;
    }

    public double area()
    {
        return Math.PI * radius * radius;
    }	

    public void draw()
    {
        System.out.println("    *  *    ");
        System.out.println(" *        * ");
        System.out.println("*          *");
        System.out.println("*          *");
        System.out.println(" *        * ");
        System.out.println("    *  *    ");
    }

    public void printName()
    {
        System.out.println(String.format("%s(%d) : %.2f", name, radius, this.area()));
    }
}

class Square
    extends Shape
{
    protected int height;

    public Square(String name, int h)
    {
        super(name);
        height = h;
    }

    public double area()
    {
        return height * height;
    }	

    public void draw()
    {
        System.out.println("***");
        System.out.println("* *");
        System.out.println("***");
    }

    public void printName()
    {
        System.out.println(String.format("%s(%d) : %.0f", name, height, this.area()));
    }
}

class Rectangle
    extends Square
{
    private int width;

    public Rectangle(String name, int h, int w)
    {
        super(name, h);
        width = w;
    }

    public double area()
    {
        return height * width;
    }	

    public void draw()
    {
        System.out.println("***************");
        System.out.println("*             *");
        System.out.println("*             *");
        System.out.println("***************");
    }

    public void printName()
    {
        System.out.println(String.format("%s(%d, %d) : %.0f", name, height, width, this.area()));
    }

}

class Picture
{
    // linked list structure to store all shapes
    private LinkedList shapes = new LinkedList();

    public void add(Shape sh)
    {
        shapes.add(sh);
    }

    public void drawAll()
    {
        for (int i = 0; i < shapes.size(); i++)
        {
            shapes.get(i).draw();
        }
    }

    public double totalArea()
    {
        double total = 0.0;
        for (int i = 0; i < shapes.size(); i++)
        {
            total += shapes.get(i).area();
        }
        return total;
    }

    public void printShapes()
    {
        for (int i = 0; i < shapes.size(); i++)
        {
            shapes.get(i).printName();
        }
    }
}

class LinkedList
{
    private ListNode head;
    private int sz;

    public LinkedList()
    {
        this.head = null;
        this.sz = 0;
    }

    public void add(Shape x)
    {
        if (this.head == null)
        {
            this.head = new ListNode(x, null);
        }
        else
        {
            ListNode ptr = this.head;
            while (ptr.next != null)
            {
                ptr = ptr.next;
            }
            ptr.next = new ListNode(x, null);
        }
        this.sz++;
    }
    
    public int size()
    {
        return this.sz;
    }
    
    public Shape get(int index) 
    {
        ListNode h = head;
        for (int i = 0; i < index; i++)
        {
            h = h.next;
        }
        return h.info;
    }
}

class ListNode {
    Shape info;
    ListNode next;

    public ListNode(Shape newValue, ListNode newNext)
    {
        this.info = newValue;
        this.next = newNext;
    }

    public ListNode(Shape newValue)
    {
        this.info = newValue;
        this.next = null;
    }
}


public class mainClass
{
    static void println(double d)
    {
        System.out.println(String.format("Total : %.2f", d));
    }

    public static void main(String[] args)
    {
        // parse argument inputs
        int arg1 = Integer.parseInt(args[0]);
        int arg2 = Integer.parseInt(args[1]);

        Picture p = new Picture();
        p.add(new Triangle("FirstTriangle", arg1, arg2));
        p.add(new Triangle("SecondTriangle", arg1 - 1, arg2 - 1));
        p.add(new Circle("FirstCircle", arg1));
        p.add(new Circle("SecondCircle", arg1 - 1));
        p.add(new Square("FirstSquare", arg1));
        p.add(new Square("SecondSquare", arg1 - 1));
        p.add(new Rectangle("FirstRectangle", arg1, arg2));
        p.add(new Rectangle("SecondRectangle", arg1 - 1, arg2 - 1));

        p.printShapes(); // this could print out the first output shown below, for your benefit
        println(p.totalArea());
        p.drawAll();
    }
}
