package util;

import java.util.Properties;

public final class ClassNameUtil {

    public static String getCurrentClassName(){
        try {
            throw new RuntimeException();
        }catch (RuntimeException e){
            return e.getStackTrace()[1].getClassName();
        }
    }
}
