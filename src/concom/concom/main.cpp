/*
  ID: xiaoyun4
  PROG: concom
  LANG: C++
  */

#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>
//#include <istream>

using namespace std;

void dostuff(vector<vector<int> >& s, vector<vector<vector<int> > >& add, int p){
    for(int a = 0; a<p;a++){
        for(int b = 0; b<p;b++){
            for(int c = 0; c<p;c++){
                if(s[b][c]>50) continue;
                for(int d = 0; d<p;d++){        
                    if(s[b][d]>=50&&add[b][c][d]==0){
                        add[b][c][d]=1;
                        s[b][c]+=s[d][c];
                        if (s[b][c]>100)s[b][c] = 100;
                    }
                    
                }
            }
        }
    }
    
    
}

int main(int argc, const char * argv[]) {
    // insert code here...
    //cout<<"yo \n";
    ofstream fout ("concom.out");
    //fout<< "yo";
    //fout.close();
    
    ifstream fin ("concom.in");
    
    
    //fout<<"yo"<<"\n";
    //fout.close();
    //cout<<"yo";
    
    int k;
    fin>>k;
    //cout<<k << "\n";
    vector<vector<int> > n;
    vector<vector<vector<int> > > add;
    
    add.resize(100);
    
    
    
    
    for(int i = 0; i< 100;i++){
        vector<int> m;
        m.resize(100);
        n.push_back(m);
        add[i].resize(100);
        for(int j =0; j<100;j++){
            add[i][j].resize(100);
        }
    }
    int q = 0;
    
    for(int i = 0; i<k;i++){
        int a,b,c;
        fin>>a>>b>>c;
        //cout<<a<<" "<<b<<"\n";
        n[a-1][b-1]=c;
        
        if(a>q){
            q=a;
        }
        if(b>q){
            q=b;
        }
    }
    
    dostuff(n, add, q);
    
    for(int i = 0; i<q;i++){
        for(int j = 0; j<q;j++){
            //cout<<n[i][j];
            if(n[i][j]>=50&&i!=j){
                if(n[i][j]!=100 || k==1) n[i][j] = n[j][i] = 0;
                fout<<i+1<<" "<<j+1<<"\n";
            }
        }
    }
    
    
    fout.close();
    return 0;
}
