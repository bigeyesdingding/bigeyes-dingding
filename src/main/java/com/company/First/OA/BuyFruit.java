package com.company.First.OA;

import java.util.List;

public class BuyFruit {
    public int buyFruit(List<List<String>> codeList, List<String> shoppingCart){
        if(codeList== null || codeList.size()==0 || codeList.get(0).size()==0){
            return -1;
        }
        int i = 0;
        int j = 0;
        int index = 0;
        while(i<codeList.size()){
            j = 0;
            while(j<codeList.get(i).size() && index < shoppingCart.size()){
                if(codeList.get(i).get(j).equals(shoppingCart.get(index)) || codeList.get(i).get(j).equals("anything")){
                    j++;
                }
                index++;
            }
            if(index>=shoppingCart.size()){
                break;
            }
            i++;
        }

        return i==codeList.size()-1 && j>=codeList.get(codeList.size()-1).size()? 1: -1;

    }
}
