package gnzlz.console.file.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;

public class JSON {

    public static Project project;

    /**********************************
     * static
     **********************************/

    public static void updateAndCreate(String path){
        File file = new File(path);

        if(file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                Type type = new TypeToken<Project>(){}.getType();
                Gson gson = new Gson();
                project = gson.fromJson(fileReader, type);
                fileReader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {

            try {
                FileWriter fileWriter = new FileWriter(path);
                Gson gson =  new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(project, fileWriter);
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static Project file(String path){
        File file = new File(path);

        if(file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                Type type = new TypeToken<Project>(){}.getType();
                Gson gson = new Gson();
                Project project = gson.fromJson(fileReader, type);
                fileReader.close();
                return project;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Project.create();
    }

    public static void save(String path, Project project){
        File file = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(path);
            Gson gson =  new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(project, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
