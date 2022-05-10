package uk.ncl.cs.teamproject.util;

/**
 * @author yantao xu
 */
public class SystemUtil {

    public static boolean isWindows() {
        return System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1;
    }
}
