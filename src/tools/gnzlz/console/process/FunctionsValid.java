package tools.gnzlz.console.process;

import tools.gnzlz.console.database.sqlite.config.repository.OutputRepository;
import tools.gnzlz.console.process.project.ConsoleProject;
import tools.gnzlz.command.command.functional.FunctionValidCommand;
import tools.gnzlz.command.result.ResultListCommand;

import java.io.File;
import java.util.ArrayList;

public class FunctionsValid {

    /**
     * DIRECTORY
     */
    public static FunctionValidCommand DIRECTORY = (data) -> {
        if (data.value instanceof String && new File(OutputRepository.findByHashToPath(data.value.toString())).isDirectory()) {
            return true;
        }
        data.error.set("the value is not a directory");
        return false;
    };

    /**
     * FILE_PATH
     */
    public static FunctionValidCommand FILE_PATH = (data) -> {
        if (data.value instanceof String && new File(ConsoleProject.path + data.value).isFile()) {
            for (ResultListCommand template: data.allList.array("templates")) {
                if (template.string("path").equals(data.value)) {
                    data.error.set("the file path is already in use");
                    return false;
                }
            }
            return true;
        }
        data.error.set("the value is not a file path");
        return false;
    };

    /**
     * ARGS
     */
    public static FunctionValidCommand ARGS = (data) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand commands: data.allList.array("commands")) {
            for (ResultListCommand args: commands.array("args")) {
                array.add(args.string("name"));
            }
        }
        if (array.contains(data.value.toString())) {
            data.error.set("the argument is already in use");
            return false;
        } else {
            return true;
        }
    };

    /**
     * COMMANDS_NAME
     */
    public static FunctionValidCommand COMMANDS_NAME = (data) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand command: data.allList.array("commands")) {
            array.add(command.string("name"));
        }
        if (array.contains(data.value.toString())) {
            data.error.set("the command name is already in use");
            return false;
        } else {
            return true;
        }
    };

    /**
     * TEMPLATES_NAME
     */
    public static FunctionValidCommand TEMPLATES_NAME = (data) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand template: data.allList.array("templates")) {
            array.add(template.string("name"));
        }
        if (array.contains(data.value.toString())) {
            data.error.set("the template name is already in use");
            return false;
        } else {
            return true;
        }
    };

    /**
     * COMMAND_OPTIONS
     */
    public static FunctionValidCommand COMMAND_OPTIONS = (data) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand option: data.arraylist.array()) {
            array.add(option.string("option"));
        }
        if (array.contains(data.value.toString())) {
            data.error.set("the option name is already in use");
            return false;
        } else {
            return true;
        }
    };

    /**
     * COMMAND_OPTIONS
     */
    public static FunctionValidCommand COMMAND_DEFAULT = (data) -> {
        if (data.list.string("type").equals("number")) {
            try {
                Double.parseDouble(data.value.toString());
            } catch (NumberFormatException nfe) {
                return false;
            }
        }

        return true;
    };

    /**
     * GROUP_COMMAND
     */
    public static FunctionValidCommand GROUP_COMMAND = (data) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand list : data.arraylist.array()) {
            array.add(list.string("command"));
        }
        if (array.contains(data.value.toString())) {
            data.error.set("the group command name is already in use");
            return false;
        } else {
            return true;
        }
    };

    /**
     * GROUP_USE_COMMANDS
     */
    public static FunctionValidCommand GROUP_USE_COMMANDS = (data) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand list : data.arraylist.array()) {
            array.add(list.string("name"));
        }
        if (array.contains(data.value.toString())) {
            data.error.set("the command name is already in use");
            return false;
        } else {
            return true;
        }
    };

    /**
     * GROUP_USE_TEMPLATES
     */
    public static FunctionValidCommand GROUP_USE_TEMPLATES = (data) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand list : data.arraylist.array()) {
            array.add(list.string("name"));
        }
        if (array.contains(data.value.toString())) {
            data.error.set("the template name is already in use");
            return false;
        } else {
            return true;
        }
    };
}
