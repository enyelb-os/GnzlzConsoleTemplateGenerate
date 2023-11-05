package gnzlz.console;

import gnzlz.console.process.database.ConsoleDatabase;
import gnzlz.console.database.sqlite.config.ConfigSQLite;

public class Main {

    public static void main(String[] args) {
        System.out.println("Test "+ ConfigSQLite.connection.query("Select 1+1").execute());

        //JSON.file("project.gnzlz.json");

        /*String path = "project.gnzlz.test.json";
        String path2 = "project.gnzlz.test.json";


        JSON.save(path2,
            CommandParse.parseCommandsToProject(
                ConsoleProject.processCommandsCreateProjectJSon(
                    CommandParse.parseProjectToCommands(JSON.file(path))
                )//,
                //JSON.file(path)
            )
        );*/

       ConsoleDatabase.main(args);
    }
}