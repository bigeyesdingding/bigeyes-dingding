package com.company.First;


public class TimeLineArragement {


   /* Example 1 : Consider the following 3 activities sorted by
    by finish time.
            start[]  =  {10, 12, 20};
    finish[] =  {20, 25, 30};
    A person can perform at most two activities. The
    maximum set of activities that can be executed
    is {0, 2} [ These are indexes in start[] and
    finish[] ]

    Example 2 : Consider the following 6 activities
    sorted by by finish time.
            start[]  =  {1, 3, 0, 5, 8, 5};
    finish[] =  {2, 4, 6, 7, 9, 9};
    A person can perform at most four activities. The
    maximum set of activities that can be executed
    is {0, 1, 3, 4} [ These are indexes in start[] and
    finish[] ]*/

   public static int findActivity(int[] start, int[] finish){
       if(start == null || start.length == 0){
           return 0;
       }

       //using a 1D array to store the number of activities can be down from index 0 to index i activity, while index i
       //must be included
       int[] count = new int[start.length];
       count[0] = 1;

       int globalMax = 1;

       for(int i = 1; i<start.length; i++){
           count[i] = 1;
           for(int j = i-1; j>=0;j--){
               if(start[i]>=finish[j]){
                   count[i] = Math.max(count[i], count[j] + 1);
               }
           }
           globalMax = Math.max(globalMax,count[i]);
       }
       return globalMax;
   }

   public static void greedy(int[] start, int[] finish, int n){
       System.out.println("The following activities are selected: n");

       //since the finish array is sorted, so the first one should always be picked
       int  slow = 0;
       int count = 1;
       System.out.print(slow+" ");
       for(int fast = 1; fast<n; fast++){
           if(start[fast]>=finish[slow]){
               System.out.print(fast+" ");
               slow = fast;
               count++;
           }
       }
       System.out.println("Total numbers of selected: "+ count);
   }

    public static void main(String[] args){
        int[] start =  {1, 3, 0, 5, 8, 5};
        int[] finish =  {2, 4, 6, 7, 9, 9};
        //int[] start =  {10, 12, 20};
        //int[] finish =  {20, 25, 30};
        //int result = 0;
        //System.out.println(findActivity(start,finish));

        double a =129;
        Double b = 129.00000000000001;
        System.out.println(Double.compare(b,a));

        boolean c =true;
        boolean d = true;
        String e= "false";

        greedy(start, finish, 6);

    }




}
