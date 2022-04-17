package tests;

import java.util.ArrayList;

public class Minimum {
    public String FindMinimum(ArrayList<Integer> list,ArrayList<String> strlist){
        int i = 0;
        int flag = 0;
        int min = list.get(0);
        for(i = 1 ; i < list.size() ; i++){
            if(min>list.get(i)){
                min = list.get(i);
                flag = i;
            }
        }
        return strlist.get(flag);
    }
}
