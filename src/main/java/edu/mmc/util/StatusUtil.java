package edu.mmc.util;

import java.util.HashMap;
import java.util.Map;

public class StatusUtil {
    private static Map<Integer,Integer> sub;
    private static Map<Integer,Integer> back;
    static {
        sub = new HashMap<>();
        back = new HashMap<>();
        sub.put(1,3);
        sub.put(2,5);
        sub.put(3,7);
        sub.put(4,8);
        back.put(2,2);
        back.put(3,4);
        back.put(4,6);
    }
    public static Map<Integer,Integer> getSub(){
        return sub;
    }
    public static Map<Integer,Integer> getBack(){
        return back;
    }


}
