package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ResourceReader {
    public String resourceToString(String resourceName) throws IOException {
        return String.join("\n", resourceToList(resourceName));
    }

    /*
        This method does not need preceeding / in the path - navigation starts at the root of the classpath
    */
    public List<String> resourceToList(String resourceName){
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + resourceName);
        } else {
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            return reader.lines().toList();
        }
    }

    /*
        This method requires preceeding / to navigate from the root of the classpath, otherwise it navigates from the package.
    */
//    public List<String> resourceToList(String resourceName){
//        InputStream inputStream = ResourceReader.class.getResourceAsStream(resourceName);
//        if (inputStream == null) {
//            throw new IllegalArgumentException("file not found! " + resourceName);
//        } else {
//            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
//            BufferedReader reader = new BufferedReader(streamReader);
//            return reader.lines().toList();
//        }
//    }


}
