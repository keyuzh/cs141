#include <malloc.h>
#include <stdio.h>
#include <stdlib.h>

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
    _this->name = newName;
    _this->height = h;
    _this->base = b;
    return _this;
}


int main(int argc, char* argv[])
{
    printf("%d\n", argc);
    printf("%d, %d", atoi(argv[1]), atoi(argv[2]));
    Shape* shapes[] = {};

    Shape* s = Shape_Shape((Shape*)malloc(sizeof(Shape)), "Test");

    Shape_draw(s);

    Triangle* firstTriange = Triangle_Triangle(
        (Triangle*)malloc(sizeof(Triangle)), "FirstTriangle", 5, 5
    );

    Triangle_printName(firstTriange);
    Triangle_draw(firstTriange);

    return 0;
}