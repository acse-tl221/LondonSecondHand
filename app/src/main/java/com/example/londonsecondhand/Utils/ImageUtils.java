package com.example.londonsecondhand.Utils;

import java.util.HashMap;
import java.util.Map;
import com.example.londonsecondhand.R;

public class ImageUtils {
    private static Map<String,Integer> map = new HashMap<>();

    static {
        map.put("handbag1", R.drawable.handbag1);
        map.put("handbag2",R.drawable.handbag2);
        map.put("ear1",R.drawable.ear1);
        map.put("ear2",R.drawable.ear2);
    }

    public static Integer getimageid(String name){
        if(map.containsKey(name)){
            return map.get(name);
        }
        return 0;
    }
}
