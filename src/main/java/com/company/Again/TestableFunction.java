package com.company.Again;

import java.lang.String;
import java.util.List;

public class TestableFunction {
    public Object solution(Object obj){
        return obj;
    }

    public static void test(List<Object> testcases){
        TestableFunction p = new TestableFunction();
        for(Object obj: testcases){
            System.out.println(p.solution(obj));
        }
    }

}
