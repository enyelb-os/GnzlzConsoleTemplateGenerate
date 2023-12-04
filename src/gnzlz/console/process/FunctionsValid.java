package gnzlz.console.process;

import gnzlz.console.database.sqlite.config.repository.OutputRepository;
import gnzlz.console.process.project.ConsoleProject;
import tools.gnzlz.command.functional.FunctionValidCommand;
import tools.gnzlz.command.result.ResultListCommand;

import java.io.File;
import java.util.ArrayList;

public class FunctionsValid {

    /**
     * DIRECTORY
     */
    public static FunctionValidCommand DIRECTORY = (value, error, allList, list) -> {
        if (value instanceof String && new File(OutputRepository.findByHashToPath(value.toString())).isDirectory()) {
            return true;
        }
        error.set("the value is not a directory");
        return false;
    };

    /**
     * FILE_PATH
     */
    public static FunctionValidCommand FILE_PATH = (value, error, allList, list) -> {
        if (value instanceof String && new File(ConsoleProject.path + value).isFile()) {
            for (ResultListCommand template: allList.array("templates")) {
                if (template.string("path").equals(value)) {
                    error.set("the file path is already in use");
                    return false;
                }
            }
            return true;
        }
        error.set("the value is not a file path");
        return false;
    };

    /**
     * ARGS
     */
    public static FunctionValidCommand ARGS = (value, error, allList, list) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand commands: allList.array("commands")) {
            for (ResultListCommand args: commands.array("args")) {
                array.add(args.string("name"));
            }
        }
        if (array.contains(value.toString())) {
            error.set("the argument is already in use");
            return false;
        } else {
            return true;
        }
    };

    /**
     * COMMANDS_NAME
     */
    public static FunctionValidCommand COMMANDS_NAME = (value, error, allList, list) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand command: allList.array("commands")) {
            array.add(command.string("name"));
        }
        if (array.contains(value.toString())) {
            error.set("the command name is already in use");
            return false;
        } else {
            return true;
        }
    };

    /**
     * TEMPLATES_NAME
     */
    public static FunctionValidCommand TEMPLATES_NAME = (value, error, allList, list) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand template: allList.array("templates")) {
            array.add(template.string("name"));
        }
        if (array.contains(value.toString())) {
            error.set("the template name is already in use");
            return false;
        } else {
            return true;
        }
    };

    /**
     * COMMAND_OPTIONS
     */
    public static FunctionValidCommand COMMAND_OPTIONS = (value, error, allList, list) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand option: list.array("options")) {
            array.add(option.string("option"));
        }
        if (array.contains(value.toString())) {
            error.set("the option name is already in use");
            return false;
        } else {
            return true;
        }
    };

    /**
     * COMMAND_OPTIONS
     */
    public static FunctionValidCommand COMMAND_DEFAULT = (value, error, allList, list) -> {
        if (list.string("type").equals("number")) {
            try {
                Double.parseDouble(value.toString());
            } catch (NumberFormatException nfe) {
                return false;
            }
        }

        return true;
    };

    /**
     * GROUP_COMMAND
     */
    public static FunctionValidCommand GROUP_COMMAND = (value, error, allList, list) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand group: list.array("groups")) {
            array.add(group.string("command"));
        }
        if (array.contains(value.toString())) {
            error.set("the group command name is already in use");
            return false;
        } else {
            return true;
        }
    };

    /**
     * GROUP_USE_COMMANDS
     */
    public static FunctionValidCommand GROUP_USE_COMMANDS = (value, error, allList, list) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand command: list.array("use")) {
            array.add(command.string("name"));
        }
        if (array.contains(value.toString())) {
            error.set("the command name is already in use");
            return false;
        } else {
            return true;
        }
    };

    /**
     * GROUP_USE_TEMPLATES
     */
    public static FunctionValidCommand GROUP_USE_TEMPLATES = (value, error, allList, list) -> {
        ArrayList<String> array = new ArrayList<>();
        for (ResultListCommand template: list.array("templates")) {
            array.add(template.string("name"));
        }
        if (array.contains(value.toString())) {
            error.set("the template name is already in use");
            return false;
        } else {
            return true;
        }
    };
}
