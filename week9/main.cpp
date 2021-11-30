#include "foo.h"
int main(int argc, const char * argv[]) {
// insert code here...
cout << "START" << endl;
    for (int i = 0; i < 3; ++i) {
        try {
            cout << "Loop " << i << ": ";
            func2(i);
            cout << "endLoop";
        } catch (int j) {
            cout << "J1" << j << "J2";
        } catch (double d) {
            cout << "D2" << d << "D2_in_main";
        }
        cout << endl;
    }
return 0;

}