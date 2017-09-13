import java.util.ArrayList;
import java.util.List;

class Codes {
    public static  List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(n<=0){
            return result;
        }

        if(n < k){
            return result;
        }

        List<Integer> current = new ArrayList<>();

        helper(1, current, result, n, k);
        return result;

    }


    public static void helper(int level, List<Integer> current, List<List<Integer>> result, int n, int k ){
        //base case: level == n-1;
        if(level > n){
            if(current.size()==k){
                System.out.println(current);
                result.add(new ArrayList<Integer> (current));
            }
            return;
        }
        //recursion rules
        if(current.size()>k){
            return;
        }

        //case one: add
        current.add(level);
        helper(level+1,current,result,n, k);
        current.remove(current.size()-1);

        //case two: not add
        helper(level+1,current,result,n, k);
    }


    public static  List<List<Integer>> combineII(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();


    }


    public static void main(String[] args){
        combine(1,0);
    }

}