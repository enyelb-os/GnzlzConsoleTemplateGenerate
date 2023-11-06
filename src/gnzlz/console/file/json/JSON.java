package gnzlz.console.file.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import gnzlz.console.file.json.project.data.Project;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public class JSON {

    /**
     * path
     * @param path path
     */
    public static String path(String path) {
        char last = path.charAt(path.length() - 1);
        if (last != '\\' && last != '/') {
            return path + (path.contains("\\") ? "\\" : "/");
        }
        return path;
    }

    /**
     * updateAndCreate
     * @param <T> t
     * @param path p
     * @param json j
     * @param typeClass t
     */

    public static <T> T updateAndCreate(String path, T json, Class<T> typeClass){
        T dataJson = JSON.create(path, typeClass);
        if (json == null) {
            json = dataJson;
        }
        return save(path, json);
    }

    /**
     * create
     * @param <T> t
     * @param path p
     * @param typeClass t
     */
    public static <T> T create(String path, Class<T> typeClass){
        T t = get(path, typeClass);
        if (t == null) {
            try {
                return typeClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                return null;
            }
        } else {
            return t;
        }

    }

    /**
     * create
     * @param <T> t
     * @param path p
     * @param typeClass t
     */
    public static <T> T get(String path, Class<T> typeClass){
        File file = new File(path);
        if(file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                Type type = new TypeToken<Project>(){}.getType();
                Gson gson = new Gson();
                T t = gson.fromJson(fileReader, type);
                fileReader.close();
                return t;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * updateAndCreate
     * @param <T> t
     * @param path p
     * @param json j
     */
    public static <T> T save(String path, T json){
        File file = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(path);
            Gson gson =  new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(json, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            return null;
        }
        return json;
    }
}
