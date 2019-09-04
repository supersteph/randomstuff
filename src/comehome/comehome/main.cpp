/*
 ID: xiaoyun4
 PROG: comehome
 LANG: C++
 */


/*
I think the strategy is that I am going to run through this process 52 times because at most it takes 52 iterations for me to run through everything so for each one of the iterations it goes through every element in the 2d array, so then let's say I'm at an element [x][y] that means that distance x is something away from y, so then I got to the [y] column of the 2d array then I go through the entire column and then I i make the column k[x] equal to k[i] that is my algorithim for figuring out the shortest path to it.
 After I figure out the shortest path to everything I got through each element and I
 
 
 
 */
#include <iostream>
#include <fstream>
#include <cstring>
#include <sstream>
#include <vector>

using namespace std;

void good(int n){
    if(n<0){
        cout<<"bad" <<"\n";
    }
}

void map(vector<vector<int> >& x){
    //if (k >= m.size()) std::cerr << "error" << endl;
    
    for(int k = 0; k<x.size();k++){
        
        for(int i = 0; i<x[k].size();i++){
            //good(k);
            //good(i);
            
            if(x[k][i] != -1){
                //std::cout<< k << " " << i << "\n";
                x[i][k] = x[k][i];
                
                for(int j = 0; j < x[i].size(); j++){
                    if(x[i][j]!=-1){
                        // make the current row of the jth column equal to the minimum between what it is currently and the distance it is between the other column and the other row
                        //x[k][j] is the one i want to change
                        if(x[k][j]!=-1){
                            
                            x[k][j] = min(x[i][k]+x[i][j], x[k][j]);
                            x[j][k] = min(x[i][k]+x[i][j], x[k][j]);
                        }
                        else if(x[i][j]!=-1){
                            x[k][j] = x[i][k]+x[i][j];
                            x[j][k] = x[i][k]+x[i][j];
                            
                        }
                        
                    }
                    
                }
            }
        }
    }
}

int main(){
    ifstream fin("comehome.in");
    ofstream fout("comehome.out");
    int n;
    fin>>n;
    vector<vector<int> > k(52, vector<int>(52, -1));
    vector<int> cows;
    //  cout<<"yo";
    for(int i = 0; i< n;i++){
        char first;
        char second;
        int steps;
        fin>>first>>second>>steps;
        if(isupper(first)&&isupper(second)){
            if(first != 'Z'){
                cows.push_back(first-'A');
            }
            if(second != 'Z'){
                cows.push_back(second-'A');
            }
            if (k[second-'A'+26][first-'A'+26]<steps&&k[second-'A'+26][first-'A'+26]!=-1) {
                continue;
            }
            k[second-'A'+26][first-'A'+26] = steps;
            k[first-'A'+26][second-'A'+26] = steps;

        }
        else if(isupper(second)){
            if(second!='Z'){
                cows.push_back(second-'A');
            }
            //cout << a << ":" << b << endl;
            if (k[first-'a'][second- 'A'+ 26]<steps&&k[first-'a'][second- 'A'+ 26]!=-1) {
                continue;
            }
            k[first-'a'][second- 'A'+ 26]=steps;
            k[second-'A'+26][first-'a']= steps;
        }
        else if(isupper(first)){
            if(first != 'Z'){
                cows.push_back(first-'A');
            }
            if(k[first-'A'+26][second-'a']< steps && k[first-'A'+26][second-'a'] != -1){
                continue;
            }
            k[first-'A'+26][second-'a']= steps;
            k[second-'a'][first-'A'+26]= steps;
            
        }
        else {
            if(k[tolower(first)-'a'][tolower(second)-'a']<steps&&k[tolower(first)-'a'][tolower(second)-'a']!=-1){
                continue;
            }
            k[tolower(first)-'a'][tolower(second)-'a']= steps;
            k[tolower(second)-'a'][tolower(first)-'a']= steps;
        }
    }
    
    vector<bool> s(52);
    
    for(int i = 0; i< 52;i++){
        map(k);
    }
    
    int lol = k[cows[0]+26][51];
    char lo = 'A' + cows[0];
    for(int i = 0; i<cows.size();i++){
        if (k[cows[i]+26][51]==-1) {
            continue;
        }
        if(k[cows[i]+26][51]<lol){
            lol = k[cows[i]+26][51];
            lo = 'A' + cows[i];
        }
    }
    fout << lo << " " << lol <<"\n";
    cout << lo << " " << lol <<"\n";
    return 0;
}