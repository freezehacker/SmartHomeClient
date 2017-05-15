package utils;

import java.io.*;

import static org.hibernate.sql.InFragment.NULL;

/**
 * Created by JK.
 */
public class StringUtils {

    public static boolean isEmpty(String s) {
        return s == NULL || s.length() == 0;
    }

    public static String fromFile(String file) {
        StringBuilder sb=  new StringBuilder();
        InputStream is =null;
        BufferedReader br=null;
        try {
            is = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
