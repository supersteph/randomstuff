  /*
 ID: xiaoyun4
 PROG: maze1
 LANG: C++
 */

#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>
#include <string>

using namespace std;

class place {
public:
    int first;
    int second;
};

vector<place> getstarts(vector<vector<bool> > map){
    vector<place> k;
    
    for(int i = 1; i<map.size();i+=2){
        if(k.size()==2){
            break;
        }
        if(map[i][0]){
            place s;
            s.first=i;
            s.second = 1;
            k.push_back(s);
            
        }
        if(map[i][map[i].size()-1]){
            place s;
            s.first=i;
            s.second = map[i].size()-2;
            k.push_back(s);
        }
    }
    for(int i = 1; i<map[0].size();i+=2){
        if(k.size()==2){
            break;
        }
        if(map[0][i]){
            place s;
            s.first = 1;
            s.second = i;
            k.push_back(s);
        }
        if(map[map.size()-1][i]){
            place s;
            s.first = map.size()-2;
            s.second = i;
            k.push_back(s);
        }
    }
    return k;
}

vector<place> step(vector<place>& stuff, vector<vector<bool> >& map){
    vector<place> other;
    for(int i = 0; i<stuff.size();i++){
        map[stuff[i].first][stuff[i].second] = false;
    }
    for(int i = 0; i<stuff.size();i++){
        place n = stuff[i];
        if(n.second!=map[0].size()-2 && map[n.first][n.second+1] && map[n.first][n.second+2]){
            
            place k;
            k.first=n.first;
            k.second = n.second+2;
            map[k.first][k.second] = false;
            other.push_back(k);
            
        }
        if(n.second!=1 && map[n.first][n.second-1] && map[n.first][n.second-2]){
            place k;
            k.first = n.first;
            k.second= n.second-2;
            map[k.first][k.second] = false;
            other.push_back(k);
        }
        if(n.first!=map.size()-2 && map[n.first+1][n.second] && map[n.first+2][n.second]){
            place k;
            k.first = n.first+2;
            k.second= n.second;
            map[k.first][k.second] = false;
            other.push_back(k);
            
        
        }
        if(n.first!=1 && map[n.first-1][n.second] && map[n.first-2][n.second]){
            place k;
            k.first = n.first-2;
            k.second= n.second;
            map[k.first][k.second] = false;
            other.push_back(k);
        }
        map[n.first][n.second]=false;
        
        
    }
    return other;
}



int flood(vector<vector<bool> >& map){
    vector<place> start = getstarts(map);
    int k = 1;
    
    while (start.size()!=0) {
        start = step(start, map);
        if(start.size()!=0){
            k++;
        }
    }
    return k;
}



int main(int argc, const char * argv[]) {
    // insert code here...
    //cout<<"yo \n";
    ofstream fout ("maze1.out");
    //fout<< "yo";
    //fout.close();
    
    ifstream fin ("maze1.in");
    int width, height;
    width = 0;
    height = 0;
    fin>>width>>height;
    string n;
    getline(fin,n);
    
    vector<vector<bool> > map;
    for(int i = 0; i<height*2+1;i++){
        string k;
        getline(fin, k);
        cout<<k<<"\n";
        vector<bool> n;
        for(int j = 0; j<2*width+1;j++){
            if(k[j]==' '|| k[j]=='\0'){
                n.push_back(true);
            }else{
                n.push_back(false);
            }
        }
        map.push_back(n);
    }
    
    int s = flood(map);
    fout<<s<<"\n";
    cout<<s<<"\n";

    
    
    fout.close();
     
    
    return 0;
}