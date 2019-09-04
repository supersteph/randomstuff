//
//  main.cpp
//  money
//
//  Created by Awesomeness on 8/7/15.
//  Copyright (c) 2015 Swagmaster. All rights reserved.
//
/*
 ID: xiaoyun4
 PROG: money
 LANG: C++
 */

#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>
//#include <istream>

using namespace std;



long getnum(vector<int>  x, int k, int current){
    
    //cout<<current<<"\n";
    /*
    if(test[current]!=-1){
        return test[current];
    }
     */
    if(current==k){
        return 1;
    }
    
    int m = x[x.size()-1];
    if(x.size()==1){
        if((k-current)%m==0){
            return 1;
        } else{
            return 0;
        }
    }
    long all = 0;

    //cout<<m<<"\n";
    x.erase(x.end()-1);
    for(int i = 0;i+current<=k;i+=m){
        long s = getnum(x, k,i+current);
        //cout<<s <<" "<<current<<" "<<
        all+=s;
    
    }
    //test[current]= all;
    return all;
}



int getit(vector<int> all, int tobuild, int k){
    if(tobuild==k){
        return 1;
    }
    int allo = 0;
    for(int i = 0;i<all.size();i++){
        if(tobuild+all[i]>k){
            break;
        }
        allo += getit(all,tobuild+all[i] , k);
        
    }
    //test[current]= all;
    return allo;
    
    
    
    
}

bool search(vector<int> all, int k){
    if(k==5){
        
    }
    
    if(all[0]>k||all[all.size()-1]<k)
    {
        return false;
    }
    for(int i = 0; i<all.size();i++){
        if(all[i]==k){
            return true;
        }
        else  if(k<all[i]){
            return false;
        }
    }
    return false;
}

void print(vector<vector<long long> > & stuff){
    for(int i = 0; i<stuff.size();i++){
        for(int j =0;j<stuff.at(0).size();j++){
            cout<<stuff[i][j]<< " ";
        }
        cout<<"\n";
    }
    cout<<"\n";
}


long trytogetit(vector<int>& all, int k, vector<long> & ref){
    cout<<k<<" ";
    //print(ref);
    
    if(ref.at(k-1)!=-1){
        return ref[k];
    }
    if(k<all[0]){
        ref[k-1] = 0;
        return 0;
    }else if(k==all[0]){
        ref[k-1]=1;
        return 1;
    }
    int allo = 0;
    if(search(all,k)){
        allo+=1;
    }
    for(int i = 1;i<=all.size()/2;i++){
        if(i<all[0]){
            continue;
        }
        long first = trytogetit(all , i, ref);
        long second=trytogetit(all, k-i, ref);
        allo+= first*second;
    }
    //test[current]= all;
    ref[k-1]=allo;

    return allo;
    
    
    
    
}

void tryto(vector<int> all, vector<vector<long long> > & ref){
    //int allo = 0;
    for(int i =0; i<ref.size();i++){
        for(int j = 0; j<ref.at(0).size();j++){
            //cout<<i<< " "<<j<<"\n";
            //print(ref);
            if(i!=0)
            {
                ref[i][j]=ref.at(i-1).at(j);
            }else {
                ref[i][j]=0;
            }
        
            if(j+1-all.at(i)==0){
                ref[i][j]+=1;
            } else if(j+1-all[i]<0){
                continue;
            } else{
        
                ref[i][j]+=ref.at(i).at(j-all[i]);
                
                //ref[i][j]+=ref.at(i).at(j-all[j]);
            }
            
            //cout<<ref[i][j]<<"\n";
        }
        
    }
    
    //return allo[ref.size()-1][ref[0].size()];
    
    
    
    
}

void trytoto(vector<int> all, vector<vector<long> > & ref){
    //int allo = 0;
    for(int i =0; i<ref.size();i++){
        for(int j = 0; j<ref.at(0).size();j++){
            //cout<<i<< " "<<j<<"\n";
            //print(ref);
            if(i!=0)
            {
                ref[i][j]=ref.at(i-1).at(j);
            }else {
                ref[i][j]=0;
            }
            int k = j+1-all.at(i);
            while (k>=0) {
                if(k==0){
                    ref[i][j]+=1;
                } else if(k<0){
                    continue;
                } else{
                    
                    ref[i][j]+=ref.at(i).at(k);
                    
                    //ref[i][j]+=ref.at(i).at(j-all[j]);
                }
                k-=all.at(i);
            }
        }
        
            //cout<<ref[i][j]<<"\n";
    }
}
    
    //return allo[ref.size()-1][ref[0].size()];
    
    
    
    


int main(int argc, const char * argv[]) {
    // insert code here...
    //cout<<"yo \n";
    ofstream fout ("money.out");
    //fout<< "yo";
    //fout.close();
    
    ifstream fin ("money.in");
    //fout<<"yo"<<"\n";
    //fout.close();
    //cout<<"yo";
    
    int k;
    int m;
    fin >> k >> m;
    //cout << "yo";
    //getline(fin,)
    vector<int> all (k);
    //cout<<k<<m<<"\n";
    //fin.getline();
    vector<long> test(m);
    for(int i = 0;i<k;i++){
        fin>>all[i];
    }
    //vector<long> cool(k);
    
    std::sort(all.begin(),all.end());
    
    vector<vector<long long> > stuff (k);
    vector<long long> st (m);
    for(int i = 0; i<k;i++){
        stuff [i]=st;
    }
    //cout<< k<<" "<<m<<"\n";
    
    tryto(all, stuff);
    //cout<<stuff.at(k-1).at(m-1) << "\n";
    fout << stuff[k-1][m-1] << "\n";
    cout<< stuff[k-1][m-1]<<"\n";
    
    print(stuff);
    fout.close();
    
    
     
    return 0;
}
