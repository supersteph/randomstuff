public class Solution {

    

    

    public ArrayList<Integer> fix(int[] nums){

        ArrayList<Integer> c = new ArrayList<Integer>();

        Integer num = null;

        int count = 0;

        

        for(int i = 0; i< nums.length;i++){

            if(num==null){

                num = new Integer(nums[0]);

                count ++;



            }

            else if(num == nums[i]){

                count++;

            }

            else{

                num = nums[i];

                count = 1;

                

            }

            if(count<=3){

                c.add(nums[i]);

            }

        }

        return c;

    }

    public List<List<Integer>> threeSum(int[] nums) {

        ArrayList<Integer> n = fix(nums);

        Collections.sort(n);

        HashSet<Integer> mySet = new HashSet<Integer>();

        for(int i = 0; i<n.size();i++){

            mySet.add(n.get(i));

        }

        System.out.println(mySet);

        ArrayList<List<Integer>> s = new ArrayList<List<Integer>>();

        outerloop:

        for(int i =0; i<n.size()-2;i++){

            for(int j = i+1; j<n.size()-1;j++){

                if(i+j>0){

                    break outerloop;

                }

                if(mySet.contains(-n.get(i)-n.get(j))){

                    ArrayList<Integer> lmao = new ArrayList<Integer>();

                    lmao.add(n.get(i));

                    lmao.add(n.get(j));

                    lmao.add(-n.get(i)-n.get(j));

                    //System.out.println("hello");

                    s.add(lmao);

                    

                }

                

                

            }

        }

        return s;

        

    }

}
