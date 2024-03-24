package xb.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {
    public static List<String> arrayToList(String[] strArr){
        List<String> result = new ArrayList<>();
        Collections.addAll(result, strArr);
        return result;
    }

    public static void listToArray(){

    }
}
