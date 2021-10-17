#ifndef VECTOR_H
#define VECTOR_H

#include <algorithm>
#include <iostream>
#include <initializer_list>
#include <stdexcept>

using namespace std;

template <typename T> // Assume Vector only takes in int or double for T
class Vector {
private:
    int sz;     // the number of elements in this Vector
    T* buf;     // the base of the array of Ts, you must allocate it
public:
    Vector(int n) // Vector v1(10); -- create a 10 element Vector
    {
        buf = new T[n];
        sz = n;
    }
    Vector(std::initializer_list<T> L) // Vector v1{T1, T2, T3};
    {
        buf = new T[L.size()];
        unsigned int i = 0;
        for (auto j : L)
        {
            buf[i] = j;
            i++;
        }
        sz = L.size();
    }
    ~Vector() // destructor called automatically when a Vector dies 
    {
        delete buf;
    }
/* Destructor should free memory used. your program should have no leak/lost/still-reachable/errors(suppressed or not), besides 72704 bytes in one still-reachable block (a g++/valgrind bug on some versions). */
    Vector(const Vector & v) // Vector v2(v1); deep-copy
    {
        buf = new T[v.size()];
        for (unsigned int i = 0; i < v.size(); i++)
        {
            buf[i] = v.buf[i];
        }
        sz = v.size();
    }
    int size() const // v1.size() returns 10 for v1 example above
    {
        return sz;
    }
    T & operator [] (const int i) // T x = V[i]; 
    {
        if (i < sz)
        {
            return buf[i];
        }
        else
        {
            throw std::out_of_range{"out of range"};
        }
    }
/*Access out-of-bound index should throw an error to be caught in outside scope */
    T operator * (const Vector & v) const
    {
        T result = 0;
        for (unsigned int i = 0; i < std::min(sz, v.size()); i++)
        {
            result += buf[i] * v.buf[i];
        }
        
        return result;
    }
// T x = V1 * V2; dot product
// e.g. [1, 2] * [3, 4, 5] = 1 * 3 + 2 * 4 + 0 = 11
// Assume an empty Vector will cause the product to be 0.
    Vector operator + (const Vector & v) const 
// V3 = V1 + V2; [1, 2, 3] + [4, 5, 6, 7] = [5, 7, 9, 7]
    {
        Vector<T> result(std::max(v.size(), sz));
        for (unsigned int i = 0; i < std::min(v.size(), sz); i++)
        {
            result[i] = buf[i] + v.buf[i];
        }
        if (sz < v.size())
        {
            // this is shorter
            for (unsigned int i = sz; i < v.size(); i++)
            {
                result[i] = v.buf[i];
            }
        }
        else
        {
            // v is shorter
            for (unsigned int i = v.size(); i < sz; i++)
            {
                result[i] = buf[i];
            }
        }
        return result;
    }
    const Vector & operator = (const Vector & v) // V1 = V2;
    {
        if (this != &v)
        {
            T* newBuf = new T[v.size()];
            for (unsigned int i = 0; i < v.size(); i++)
            {
                newBuf[i] = v.buf[i];
            }
            delete buf;
            buf = newBuf;
            sz = v.size();
        }
        return *this;
    }
    bool operator == (const Vector & v) const // if (V1 == V2)...
    {
        if (sz != v.size())
        {
            return false;
        }
        for (unsigned int i = 0; i < sz; i++)
        {
            if (buf[i] != v.buf[i])
            {
                return false;
            }
        }
        return true;
    }
    bool operator != (const Vector & v) const // if (V1 != V2)...
    {
        return !(*this == v);
    }
    friend Vector operator * (const int n, const Vector & v)
    // V1 = 20 * V2; -- each element of V1 is element of V2 * 20
    {
        Vector<T> result(v.size());
        for (unsigned int i = 0; i < v.size(); i++)
        {
            result[i] = v.buf[i] * n;
        }
        return result;
    }
    friend Vector operator + (const int n, const Vector & v)
    // V1 = 20 + V2; -- each element of V1 is element of V2 + 20
    {
        Vector<T> result(v.size());
        for (unsigned int i = 0; i < v.size(); i++)
        {
            result[i] = v.buf[i] + n;
        }
        return result;
    }
    friend ostream& operator << (ostream & o, const Vector & v)
    // cout << V2; -- prints the vector in this format 
// (v0, v1, v2, ... vn-1);
    {
        o << "(";
        for (unsigned int i = 0; i < v.size(); i++)
        {
            if (i != 0)
            {
                o << ", ";
            }
            o << v.buf[i];
        }
        o << ");";
        
        return o;
    }
};

#endif