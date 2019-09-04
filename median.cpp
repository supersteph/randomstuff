#include<iostream>  
  
using namespace std; 

void swapay(int arr[], int a, int b)  
{  
    int t = arr[a];  
    arr[a] = arr[b];  
    arr[b] = t;  
}  


int partition (int arr[], int low, int high)  
{  
    int pivot = arr[high]; // pivot  
    int i = (low - 1); // Index of smaller element  
  
    for (int j = low; j <= high - 1; j++)  
    {  
        // If current element is smaller than the pivot  
        if (arr[j] < pivot)  
        {  
            i++; // increment index of smaller element  
            swapay(arr, i, j);  
        }  
    }  
    swapay(arr, i+1, high);  
    return (i + 1);  
}  
  

int findmax(int arr[], int end){
	int max = INT_MIN;
	for (int i = 0; i < end; i++){
		if (arr[i] > max){
			max = arr[i];
		}
	}
	return max;
}

int findmin(int arr[], int start, int size){
	int mins = INT_MAX;
	for (int i = start; i < size; i++){
		if (arr[i] < mins){
			mins = arr[i];
		}
	}
	return mins;
}

int quickSort(int arr[], int low, int high, int n)
{
    if (low < high)
    {
        int pi = partition(arr, low, high);

        if(abs(pi-(n-pi-1))<=1){
        	if (abs(pi-(n-pi-1))==0){
        		return arr[pi];
        	}
            else if (pi > n-pi-1){
        		return (findmax(arr, pi)+arr[pi])/2;
        	}
        	else{
        		return (findmin(arr, pi+1, n))/2;

        	}
        }
        else if (pi > n-pi-1){
        	return quickSort(arr, low, pi - 1, n);
        }
        else {
        	return quickSort(arr, pi + 1, high, n);
        }
    }
}


int main() 
{
	int foo [5] = { 16, 2, 77, 40, 12071 }; 
	cout << quickSort(foo, 0, 4, 5);
	cout << "\n";
	return 0; 
} 