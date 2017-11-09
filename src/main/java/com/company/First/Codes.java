package com.company.First;

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
import java.util.Arrays;
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





    public static void nextPerm(int[] nums){
        if(nums.length<2){
            return;
        }
        if(nums.length==2){
            swap(nums, 0, 1);
            return;
        }

        //find the longest all descending sub array
        int flag = -1;
        for(int i = nums.length-2; i>=0; i--){
            if(nums[i]<nums[i+1]){
                flag = i;
                break;
            }
        }
        if(flag == -1){
            sort(nums, 0, nums.length-1);
            return;
        }

        //find the right bound to swap with flag
        int right = nums.length-1;
        for(int i = nums.length-1; i>flag; i--){
            if(nums[i]>nums[flag]){
                right = i;
                break;
            }
        }

        //swap flag and right, then sort nums array, from flag+1 index to the end
        swap(nums, flag, right);
        int i = flag+1;
        int j = nums.length-1;
        sort(nums, flag+1, nums.length-1);
    }

    private static void sort(int[] nums, int i, int j){
        while(i<j){
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private static void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;

    }


    public static void main(String[] args) throws IOException{
        int[] nums = {0};
        nextPerm(nums);
        System.out.println(Arrays.toString(nums));


        //ObjectMapper mapper = new ObjectMapper();
        //JsonNode inputJson = mapper.readTree(new File(o1+"/testII.json"));
    }




}