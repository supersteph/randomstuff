/*
 ID: xiaoyun4
 PROG: fracdec
 LANG: C++
 */

#include <iostream>
#include <fstream>
#include <cstring>
#include <sstream>
#include <vector>

using namespace std;

string convertInt(int number)
{
    stringstream ss;//create a stringstream
    ss << number;//add number to the stream
    return ss.str();//return a string with the contents of the stream
}

string output(string answer){
    string n;
    if(answer.length() <= 76){
        n +=answer;
        n+="\n";
    }
    else{
        for(int i = 0; i < answer.length(); i += 76){
            n += answer.substr(i, 76);
            n+="\n";
        }
    }
    return n;
}




int main(){
    ifstream fin("fracdec.in");
    ofstream fout("fracdec.out");
    int n, d;
    fin >> n >> d;
    vector<int> x;
    x.resize(d);
    for(int i = 0; i<x.size();i++){
        x[i] = -1;
    }
    
    bool cont = true;
    int b = n % d;
    int r = b % d;
    x[r] = 0;
    string fraction = "";
    while(cont){
        fraction += convertInt(r * 10 / d);
        r = r * 10 % d;
        if(r == 0){
            fout<<output(convertInt(n/d)+"."+fraction);
            cout<<(convertInt(n/d))<<"."<< fraction;
            return 0;
            
        }
        if(x[r] != -1){
            
            cont = false;
        }
        else {
            x[r] = fraction.length();
        }
    }
    fraction = fraction.substr(0, x[r]) + "(" + fraction.substr(x[r], fraction.length()) + ")";
    //if(fraction.substr(fraction.length() - 3, 3) == "(0)") fraction = fraction.substr(0, fraction.length() - 3);

    fout<<output(convertInt(n / d) + "." + fraction);
    cout<<output(convertInt(n / d) + "." + fraction);
}