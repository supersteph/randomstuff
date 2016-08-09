//
//  main.cpp
//  zerosum
//
//  Created by Awesomeness on 7/31/15.
//  Copyright (c) 2015 Swagmaster. All rights reserved.
//
`

#include <iostream>
#include <fstream>
#include <vector>
#include "math.h"
#include <string>
using namespace std;
void addone(vector<int>& stuff, int size){
    for(int i =stuff.size()-1; i>=0; i--){
        if(stuff.at(i) < 2){
            stuff.at(i)++;
            break;
        } else {
            stuff.at(i)=0;
        }
    }
}

void print(const vector<int>& stuff){
    for(int i = 0; i<stuff.size(); i++){
        cout<<stuff[i];
    }
    cout<<"\n";
}

bool work(const vector<int>& totest){
    int sum =1;
    //cout<<totest.size();
    //print(totest);
    for(int i = 0;i<totest.size();i++){
    
        if(totest[i]==0){
            //print(totest);
            //cout<<i<<"\n";
            int store=0;
            if(i!=0){
                if(totest[i-1]==1){
                    sum-=i+1;
                    store = i+1;
                }
                else if(totest[i-1]==2){
                    sum+=i+1;
                    store = -i-1;
                }
            } else {
                sum-=1;
                store = 1;
            }
            while(i<totest.size()&&totest[i]==0){
                store*=10;
                if(store>=0){
                    store+=i+2;
                }else {
                    store-=i+2;
                }
                
                
                i++;
            }
            
            i--;
            
            

            
            sum+=store;
        } else if(totest[i]==1){
            sum+=(i+2);
        } else if(totest[i]==2){
            sum-=(i+2);
        }
        
    }
    if(sum==0){
        return true;
    }
    return false;
}
vector<vector<int> > getstuff(int k){
    //cout<<"hello world";
    vector<vector<int> > all(0);
    vector<int> start(k-1);
    //cout<<"hello world";
    for(int i= 0; i<pow(3.0, k-1);i++){
    
        //print(start);
        //cout <<"hello world";
        addone(start, k);
        //cout<<"yo \n";
        if(work(start)){
            all.push_back(start);
            
            
        }
        //cout<<"yo \n";
    
    }
    

    return all;
}
int main(int argc, const char * argv[]) {
    // insert code here...
    ofstream fout ("zerosum.out");
    ifstream fin ("zerosum.in");
    
    
    
    int num;
    fin >> num;
    //cout<<num;
    vector<vector<int> > stuff = getstuff(num);
    
    vector<string> toprint(0);
    for(int i = 0;i<stuff.size();i++){
        vector<int>x=stuff[i];
        fout<<"1";
        for(int i = 1; i<num;i++){
            if(x[i-1]==0){
                fout<< " ";
            }else if(x[i-1]==1){
                fout<< "+";
            } else if(x[i-1]==2){
                fout << "-";
            }
            fout<<i+1;
        }
        fout<<"\n";
    }
    /*
    sort(toprint.begin(), toprint.end());
    for(int i = 0; i<toprint.size();i++){
        fout<<toprint[i];
    }
     */
    
    fout.close();
    fin.close();
    
    return 0;
}
