#include <malloc.h>
#include <math.h>
#include <stdio.h>

typedef double (*VirtualMethodPointer)(void *);
typedef VirtualMethodPointer *VtableType;

typedef struct Shape
{
    VtableType VPointer;
    char* name;
}Shape;

double Shape_area(Shape* _this)
{
    // virtual
    return 0.0;
}

void Shape_draw(Shape* _this)
{
    // virtual
    printf("Shape.draw() You should never see this.\n");
}

void Shape_printName(Shape* _this)
{
    // virtual
    return;
}

VirtualMethodPointer Shape_VTable [] =
{
    (VirtualMethodPointer) Shape_area,
    (VirtualMethodPointer) Shape_draw,
    (VirtualMethodPointer) Shape_printName,
};

Shape* Shape_Shape(Shape* _this, char* newName)
{
    _this->VPointer = Shape_VTable;
    _this->name = newName;
    return _this;
}

typedef struct Triangle
    // extends Shape
{
    VtableType VPointer;
    char* name;
    int height;
    int base;
}Triangle;

double Triangle_area(Triangle* _this)
{
    return _this->base * _this->height * 0.5;
}

void Triangle_draw(Triangle* _this)
{
    printf("*    \n");
    printf("**   \n");
    printf("* *  \n");
    printf("*  * \n");
    printf("*****\n");
}

void Triangle_printName(Triangle* _this)
{
    printf("%s(%d, %d) : %.1f\n", _this->name, _this->height, _this->base, Triangle_area(_this));
}

VirtualMethodPointer Triangle_VTable [] =
{
    (VirtualMethodPointer) Triangle_area,
    (VirtualMethodPointer) Triangle_draw,
    (VirtualMethodPointer) Triangle_printName,
};

Triangle* Triangle_Triangle(Triangle* _this, char* newName, int h, int b)
{
    Shape_Shape((Shape*)_this, newName);
    _this->VPointer = Triangle_VTable;
    _this->height = h;
    _this->base = b;
    return _this;
}
// end class Triangle

typedef struct Circle
    // extends Shape
{
    VtableType VPointer;
    char* name;
    int radius;
}Circle;

double Circle_area(Circle* _this)
{
    return M_PI * _this->radius * _this->radius;
}

void Circle_draw(Circle* _this)
{
    printf("    *  *    \n");
    printf(" *        * \n");
    printf("*          *\n");
    printf("*          *\n");
    printf(" *        * \n");
    printf("    *  *    \n");
}

void Circle_printName(Circle* _this)
{
    printf("%s(%d) : %.2f\n", _this->name, _this->radius, Circle_area(_this));
}

VirtualMethodPointer Circle_VTable [] =
{
    (VirtualMethodPointer) Circle_area,
    (VirtualMethodPointer) Circle_draw,
    (VirtualMethodPointer) Circle_printName,
};

Circle* Circle_Circle(Circle* _this, char* newName, int r)
{
    Shape_Shape((Shape*)_this, newName);
    _this->VPointer = Circle_VTable;
    _this->radius = r;
    return _this;
}
// end class Circle

typedef struct Square 
    // extends Shape
{
    VtableType VPointer;
    char* name;
    int height;
}Square;

double Square_area(Square* _this)
{
    return _this->height * _this->height;
}

void Square_draw(Square* _this)
{
    printf("***\n");
    printf("* *\n");
    printf("***\n");
}

void Square_printName(Square* _this)
{
    printf("%s(%d) : %.0f\n", _this->name, _this->height, Square_area(_this));
}

VirtualMethodPointer Square_VTable [] =
{
    (VirtualMethodPointer) Square_area,
    (VirtualMethodPointer) Square_draw,
    (VirtualMethodPointer) Square_printName,
};

Square* Square_Square(Square* _this, char* newName, int h)
{
    Shape_Shape((Shape*)_this, newName);
    _this->VPointer = Square_VTable;
    _this->height = h;
    return _this;
}
// end class Square 

typedef struct Rectangle 
    // extends Square
{
    VtableType VPointer;
    char* name;
    int height;
    int width;
}Rectangle;

double Rectangle_area(Rectangle* _this)
{
    return _this->height * _this->width;
}

void Rectangle_draw(Rectangle* _this)
{
    printf("***************\n");
    printf("*             *\n");
    printf("*             *\n");
    printf("***************\n");
}

void Rectangle_printName(Rectangle* _this)
{
    printf("%s(%d, %d) : %.0f\n", _this->name, _this->height, _this->width, Rectangle_area(_this));
}

VirtualMethodPointer Rectangle_VTable [] =
{
    (VirtualMethodPointer) Rectangle_area,
    (VirtualMethodPointer) Rectangle_draw,
    (VirtualMethodPointer) Rectangle_printName,
};

Rectangle* Rectangle_Rectangle(Rectangle* _this, char* newName, int h, int w)
{
    Square_Square((Square*)_this, newName, h);
    _this->VPointer = Rectangle_VTable;
    _this->width = w;
    return _this;
}
// end class Rectangle 

int main(int argc, char* argv[])
{
    int arg1 = atoi(argv[1]);
    int arg2 = atoi(argv[2]);

    Shape* shapes[] = {
        (Shape*)Triangle_Triangle((Triangle*)malloc(sizeof(Triangle)), "FirstTriangle", arg1, arg2),
        (Shape*)Triangle_Triangle((Triangle*)malloc(sizeof(Triangle)), "SecondTriangle", arg1-1, arg2-1),
        (Shape*)Circle_Circle((Circle*)malloc(sizeof(Circle)), "FirstCircle", arg1),
        (Shape*)Circle_Circle((Circle*)malloc(sizeof(Circle)), "SecondCircle", arg1-1),
        (Shape*)Square_Square((Square*)malloc(sizeof(Square)), "FirstSquare", arg1),
        (Shape*)Square_Square((Square*)malloc(sizeof(Square)), "SecondSquare", arg1-1),
        (Shape*)Rectangle_Rectangle((Rectangle*)malloc(sizeof(Rectangle)), "FirstRectangle", arg1, arg2),
        (Shape*)Rectangle_Rectangle((Rectangle*)malloc(sizeof(Rectangle)), "SecondRectangle", arg1-1, arg2-1),
    };

    // printShapes()
    int i;
    for (i = 0; i < sizeof(shapes) / sizeof(*shapes); i++)
    {
        (shapes[i]->VPointer[2])(shapes[i]);
    }

    // totalArea()
    double total = 0.0;
    for (i = 0; i < sizeof(shapes) / sizeof(*shapes); i++)
    {
        total += (shapes[i]->VPointer[0])(shapes[i]);
    }
    printf("Total : %.2f\n", total);

    // drawAll()
    for (i = 0; i < sizeof(shapes) / sizeof(*shapes); i++)
    {
        (shapes[i]->VPointer[1])(shapes[i]);
    }
    
    return 0;
}
