#include <iostream>

#include "vector.h"

using std::cout;
using std::endl;

int main() // I’ll start it for you
{
    Vector<int> intVec{1,3,5,7,9};
    Vector<double> doubleVec{1.5,2.5,3.5,4.5};
    Vector<int> iv(intVec);
    Vector<double> dv(doubleVec);
    cout << "intVec" << intVec << endl; 
    // "intVec(1, 3, 5, 7, 9)" 
    cout << "iv" << iv << endl; 
    // "iv(1, 3, 5, 7, 9)"
    cout << "doubleVec" << doubleVec << endl; 
    // "doubleVec(1.5, 2.5, 3.5, 4.5)" 
    cout << "dv" << dv << endl; 
    // "dv(1.5, 2.5, 3.5, 4.5)" 

    // add at least one test case for each method defined in Vector

    // Vector(int n)
    Vector<int> a(10);
    Vector<double> b(10);

    // Vector(std::initializer_list)
    Vector<int> c{1,2,3,4,5,6};
    Vector<double> d{0.1, 0.2, 0.3, 0.4};
    
    // size()
    std::cout << "size:" << std::endl;
    std::cout << a.size() << std::endl;
    std::cout << b.size() << std::endl;
    std::cout << c.size() << std::endl;
    std::cout << d.size() << std::endl;

    // operator[]
    std::cout << c[1] << " " << d[2] << std::endl;
    try
    {
        int temp = a[20];
    }
    catch(const std::out_of_range& e)
    {
        std::cout << "catched" << std::endl;
    }

    // operator * 
    Vector<int> v1{1,2};
    Vector<int> v2{3,4,5};
    std::cout << v1 * v2 << std::endl;
    std::cout << v2 * v1 << std::endl;
    Vector<int> v3{};
    std::cout << v2 * v3 << std::endl;

    // operator + 
    std::cout << v1 + v2 << std::endl;
    std::cout << v2 + v1 << std::endl;
    
    // operator =
    v3 = v2;
    std::cout << v2 << std::endl;
    std::cout << v3 << std::endl;

    // operator ==
    if (v2 == v3)
    {
        std::cout << "v2 == v3" << std::endl;
    }
    else
    {
        std::cout << "v2 != v3" << std::endl;
    }
    // operator !=
    if (v1 != v3)
    {
        std::cout << "v1 != v3" << std::endl;
    }
    else
    {
        std::cout << "v1 == v3" << std::endl;
    }
    
    // operator * with int
    Vector<double> v4{0.1, 0.2, 0.3, 0.4};
    std::cout << 20 * v2 << std::endl;
    std::cout << 20 * v4 << std::endl;

    // operator + with int
    std::cout << 20 + v2 << std::endl;
    std::cout << 20 + v4 << std::endl;




    return 0;
}
