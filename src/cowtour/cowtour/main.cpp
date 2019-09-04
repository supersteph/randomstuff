/*
 ID: xiaoyun4
 PROG: cowtour
 LANG: C++
 */

#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>
//#include <istream>
#include <string>
#include "math.h"
#include <iomanip>
using namespace std;
/*
 The strategy is that I am first going to do Bellman-Ford, I am going to take the size of the array 
 which means going to run through this n times. Because at most a certain point would be n points away
 from the current point you are at to run through everything so for each one of the iterations it goes
 through every element in the 2d array, so then let's say I'm at an element [x][y] that means that
 point x [x][j] distance away from y, so then I got to the [y] column of the 2d array then I go through
 the entire column and then I i make the column [x] equal to [i] that is my algorithim for figuring
 out the shortest path to it. I do this to find the shortest one, like from point i to point k may
 be slower than from point i to point j to point k, it also allows me to figure out all the places
 that I haven't been to from that point.
 
 After I figure out the shortest path to everything I got through each element of the 2d array
 that is infinity, I want to find the points that can't be connected and then I find the longest distance
 that I can go from each point, So i find the farthest distance from point I and then I find the farthest
 distance from point j, this means that if I connect these two points, the farthest will be the edges and
 the diameter would be the two farthest points plus the distance between I and J. I then compare it with
 the maximum diamter of the field I vs the diiamter of field J and I find the max between the max
 diamter and the connected points
 
 
 */
class object {
public:
    int first;
    int second;
    object(){
        first = 0;
        second = 0;
    }
};

object newobj(int first, int second){
    object n;
    n.first = first;
    n.second = second;
    return n;
}

int connect(vector<object> x){
    return 0;
}

double getdis(object n, object k){
     return sqrt((n.first-k.first)*(n.first-k.first)+(n.second-k.second)*(n.second-k.second));
}

void map(vector<vector<double> >& x){
    //if (k >= m.size()) std::cerr << "error" << endl;
    
    for(int k = 0; k<x.size();k++){
        
        for(int i = k; i<x[k].size();i++){
            //good(k);
            //good(i);
            
            if(x[k][i] != -1){
                //std::cout<< k << " " << i << "\n";
                x[i][k] = x[k][i];
                
                for(int j = i; j < x[i].size(); j++){
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


int main(int argc, const char * argv[]) {
    // insert code here...
    //cout<<"yo \n";
    ofstream fout ("cowtour.out");
    //fout<< "yo";
    //fout.cose();
    
    ifstream fin ("cowtour.in");
    int k;
    fin>>k;
    vector<object> coord;
    vector<vector<double> > stuff(k, vector<double>(k, -1));
    /*
    object n;
    n.first = 1;
    n.second = 1;
    object so;
    so.first=2;
    so.second =2;
    cout<<getdis(n, so)<<"\n";
     */
    
    
    
    for(int i = 0; i<k;i++){
        int x, y;
        object obj;
        fin>>x>>y;
        obj.first = x;
        obj.second = y;
        coord.push_back(obj);
    }
    
    
    for(int i = 0; i<k;i++){
        string s;
        fin>>s;
        for(int j = i; j<k;j++){
            if(i==j){
                stuff[i][j] = 0;
            }
            if(s[j]!='0'){
                stuff[i][j] = getdis(coord[i],coord[j]);
                stuff[j][i] = getdis(coord[i],coord[j]);
            }
        }
    }
    
    for(int i = 0;i<k-1;i++){
        map(stuff);
    }
    
    vector<double> all;
    
    for(int i = 0;i<k;i++){
        double max = 0.0;
        for(int j = 0; j<k;j++){
            if(stuff[i][j]>max){
                max = stuff[i][j];
            }
        }
        all.push_back(max);
    }
    
    vector<double>diameter(k,-1);
    for(int i = 0; i<all.size();i++){
        double max = 0;
        for(int j = 0; j<all.size();j++){
            if(stuff[i][j]!=0){
                if(all[j]>max){
                    max = all[j];
                }
            }
        }
        diameter[i] = max;
    }
    double min = -1.0;
    for(int i = 0; i<stuff.size();i++){
        for(int j = i; j<stuff.size();j++){
            if(stuff[i][j]==-1){
                double maxi = all[i];
                double maxj = all[j];
                if(min == -1.0){
                    min = max(maxj+maxi+getdis(coord[i], coord[j]), max(diameter[i],diameter[j]));
                    
                }else if( min > maxj+maxi+getdis(coord[i], coord[j])){
                    
                    min = max(maxj+maxi+getdis(coord[i], coord[j]), max(diameter[i],diameter[j]));
                }
            }
        }
    }
    
    object n;
    n.first = 0;
    n.second = 0;
    object so;
    so.first=1;
    so.second =0;
    fout<<setprecision(6)<< fixed <<min<<"\n";
    cout<<setprecision(6)<< fixed <<min<<"\n";
    
    
    
    fout.close();
    
    return 0;
}
