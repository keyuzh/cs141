#include <malloc.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef double (*VirtualMethodPointer)(void *);
typedef VirtualMethodPointer *VtableType;

typedef struct Shape
{
    VtableType VPointer;
    char* name;
}Shape;

double Shape_area(Shape* _this)
{
    return 0.0;
}

void Shape_draw(Shape* _this)
{
    printf("Shape.draw() You should never see this.\n");
}

void Shape_printName(Shape* _this)
{
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
    return _this->base * _this->height;
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
    printf("    *  *    ");
    printf(" *        * ");
    printf("*          *");
    printf("*          *");
    printf(" *        * ");
    printf("    *  *    ");
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


int main(int argc, char* argv[])
{

    // printf("ABC");
    int arg1 = atoi(argv[1]);
    int arg2 = atoi(argv[2]);
    // printf("%d, %d\n", arg1, arg2);
    Shape* shapes[] = {
        (Shape*)Triangle_Triangle((Triangle*)malloc(sizeof(Triangle)), "FirstTriangle", arg1, arg2),
        (Shape*)Triangle_Triangle((Triangle*)malloc(sizeof(Triangle)), "SecondTriangle", arg1-1, arg2-1),
        (Shape*)Circle_Circle((Circle*)malloc(sizeof(Circle)), "FirstCircle", arg1),
        (Shape*)Circle_Circle((Circle*)malloc(sizeof(Circle)), "SecondCircle", arg1-1),
    };

    int i;
    for (i = 0; i < sizeof(shapes) / sizeof(*shapes); i++)
    {
        (shapes[i]->VPointer[2])(shapes[i]);
    }
    


    return 0;
}