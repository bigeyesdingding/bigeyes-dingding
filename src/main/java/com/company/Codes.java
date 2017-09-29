import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        return result;


    }
    public static void test() throws IOException{
        String content = new String(Files.readAllBytes(Paths.get("textI.txt")));
        System.out.print(content);

    }


    public static void main(String[] args) throws IOException{
        //combine(1,0);
        Path o1 = Paths.get("/src/test/others/shipping_rate.json");
        if(Files.exists(o1)) {
            System.out.println(o1.toString());
        }
        float val = 2.64532543245f;
        float p = Math.round(val*100f)/100f;
        System.out.println(p);

        float vl = 27.81f;
        System.out.println(52.19f+vl);


        //ObjectMapper mapper = new ObjectMapper();
        //JsonNode inputJson = mapper.readTree(new File(o1+"/testII.json"));
    }
}