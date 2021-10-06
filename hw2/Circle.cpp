
// A for each concrete class define the following:
//     1) a C struc for the data in each class instance plus the Vpointer
//     2) member functions
//       a) add _this formal parameter
//       b) prefix methods with _this->
//     3) vtable
//     4) constructor
//       a) must call the parent constructor
//       b) must set the vpointer

// B Write the main




// General C includes and definitions:

    #include <malloc.h>
    #include <iostream>
    using namespace std;

    // my methods all return type double and they only take the this parameter

    typedef double (*VirtualMethodPointer)(void *);

    typedef VirtualMethodPointer * VTableType;


// Start of Class Circle

    #define PI 3.14159

    struct Circle
    {
        VTableType VPointer;
        double radius;
    };



    double Circle_area(Circle * _this)
    {
        return PI * _this->radius * _this->radius;
    }

    double Circle_circumference(Circle * _this)
    {
        return 2.0 * PI * _this->radius;
    }

    VirtualMethodPointer Circle_VTable [] =
    {
        (VirtualMethodPointer)Circle_area, // VTable[0] - the first virtual method
        (VirtualMethodPointer)Circle_circumference // VTable[1] - the second virtual method
    };

    Circle * Circle_Circle(Circle * _this, double newRadius)
    {
        _this->VPointer = Circle_VTable;
        _this->radius = newRadius;
        // Constructors usually return void, but it makes the example simpler
        return _this;
    }

// End of Class Circle

// Start of Class ACircle

    struct ACircle
        // extends Circle
    {
        VTableType VPointer;
        double radius;
        double aData;
    };

    double ACircle_area(ACircle * _this)
    {
        return PI * _this->radius * _this->radius / _this->aData;
    }

    VirtualMethodPointer ACircle_VTable [] =
    {
        (VirtualMethodPointer)ACircle_area, // Over-ride area
        (VirtualMethodPointer)Circle_circumference // inherit Circle::circumference
    };

    ACircle * ACircle_ACircle(ACircle * _this, double newRadius, double aValue)
    {
        Circle_Circle((Circle *)_this, newRadius); // call parents constructor
        _this->VPointer = ACircle_VTable; // set the vpointer AFTER parent
        _this->aData = aValue;
        return _this;
    }

// End of Class ACircle

int main()
{
    Circle * a [] = {
        Circle_Circle((Circle *)malloc(sizeof(Circle)), 1.0),
        Circle_Circle((Circle *)malloc(sizeof(Circle)), 10.0),
        Circle_Circle((Circle *)malloc(sizeof(Circle)), 100.0),
        (Circle *)ACircle_ACircle((ACircle *)malloc(sizeof(ACircle)),
            1000.0, 2.0),
        (Circle *)ACircle_ACircle((ACircle *)malloc(sizeof(ACircle)),
            10000.0, PI)
        };
    for (int i=0; i<sizeof(a)/sizeof(*a); i++)
    {
        cout << "Area = " << (a[i]->VPointer[0])(a[i]) << endl;
        cout << "Circumference = " << (a[i]->VPointer[1])(a[i]) << endl;
    }
}
