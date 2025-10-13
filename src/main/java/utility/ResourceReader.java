package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
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

    public List<String> getFiles(String path, String pattern) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            System.out.println(resource.getFile());
        }

//        if (inputStream == null) {
//            throw new IllegalArgumentException("file not found! " + resourceName);
//        } else {
//            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
//            BufferedReader reader = new BufferedReader(streamReader);
//            return reader.lines().toList();
//        }
        return null;
    }
}

