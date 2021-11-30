#include <iostream>

using namespace std;

void func1(int i) {
cout << "G1";
try {
    throw 3.0 * i;
} catch (double d) {
    cout << "D1" << int(d) << "D1_in_func";
    if (d < 3.0)
        throw ++i;
}
cout << "G2";

}

void func2(int i) {
cout << "F1";
try {
    switch (i) {
        case 0: break;
        case 1: func1(i); throw 6.0;
        case 2: func1(i); throw 'z';
        case 3: func1(i); break;
    }
} catch (char c) {
    cout << "C1" << c << "C2";
} catch (int j) {
    cout << "J1" << j << "J2";
    throw j;
}
cout << "F2";

}