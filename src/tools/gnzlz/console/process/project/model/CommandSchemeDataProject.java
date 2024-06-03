package tools.gnzlz.console.process.project.model;

import tools.gnzlz.command.CommandArray;
import tools.gnzlz.command.CommandBoolean;
import tools.gnzlz.command.CommandOptionString;
import tools.gnzlz.command.CommandString;
import tools.gnzlz.command.command.object.ListCommand;
import tools.gnzlz.console.process.FunctionsRequired;
import tools.gnzlz.console.process.FunctionsValid;

public class CommandSchemeDataProject {

    /**
     * PROJECT
     */
    public final static CommandString PROJECT = CommandString
        .create("project")
        .required()
        .message("Project name");

    /**
     * VERSION
     */
    public static CommandString VERSION = CommandString
        .create("version")
        .required()
        .message("Version")
        .value("1.0");

    /**
     * TEMPLATE_NAME
     */
    public static CommandString TEMPLATE_NAME = CommandString
        .create("name")
        .required()
        .message("Template name")
        .valid(FunctionsValid.TEMPLATES_NAME);

    /**
     * TEMPLATE_PATH
     */
    public static CommandString TEMPLATE_PATH = CommandString
        .create("path")
        .required()
        .valid(FunctionsValid.FILE_PATH)
        .message("Path file");

    /**
     * TEMPLATE_TYPE
     */
    public static CommandOptionString TEMPLATE_TYPE = CommandOptionString
        .create("type")
        .required()
        .message("Template type")
        .option("model","catalog","scheme","none");

    /**
     * TEMPLATES
     */
    public static CommandArray TEMPLATES = CommandArray
        .create("templates")
        .required()
        .message("List of templates")
        .array(TEMPLATE_NAME, TEMPLATE_PATH, TEMPLATE_TYPE);

    /**
     * COMMAND_NAME
     */
    public static CommandString COMMAND_NAME = CommandString
        .create("name")
        .required()
        .message("Command name")
        .valid(FunctionsValid.COMMANDS_NAME);

    /**
     * COMMAND_MESSAGE
     */
    public static CommandString COMMAND_MESSAGE = CommandString
        .create("message")
        .required()
        .message("Command message");

    /**
     * COMMAND_REQUIRED
     */
    public static CommandBoolean COMMAND_REQUIRED = CommandBoolean
        .create("required")
        .required()
        .message("Is required")
        .value(true);

    /**
     * COMMAND_TYPE
     */
    public static CommandOptionString COMMAND_TYPE = CommandOptionString
        .create("type")
        .required()
        .message("Command type")
        .option("number","string","option");

    /**
     * COMMAND_DEFAULT
     */
    public static CommandString COMMAND_DEFAULT = CommandString
        .create("value")
        .required()
        .valid(FunctionsValid.COMMAND_DEFAULT)
        .message("Default");

    /**
     * COMMAND_OPTION
     */
    public static CommandString COMMAND_OPTION = CommandString
        .create("option")
        .required()
        .message("Option name")
        .valid(FunctionsValid.COMMAND_OPTIONS);

    /**
     * COMMAND_OPTIONS
     */
    public static CommandArray COMMAND_OPTIONS = CommandArray
        .create("options")
        .required(FunctionsRequired.OPTIONS)
        .message("Options")
        .array(COMMAND_OPTION);

    /**
     * COMMAND_ARGS_NAME
     */
    public static CommandString COMMAND_ARGS_NAME = CommandString
        .create("name")
        .required()
        .message("Arg")
        .valid(FunctionsValid.ARGS);

    /**
     * COMMAND_ARGS
     */
    public static CommandArray COMMAND_ARGS = CommandArray
        .create("args")
        .required()
        .message("Args")
        .array(COMMAND_ARGS_NAME);

    /**
     * COMMANDS
     */
    public static CommandArray COMMANDS = CommandArray
        .create("commands")
        .required()
        .message("List of commands")
        .array(COMMAND_NAME, COMMAND_MESSAGE, COMMAND_REQUIRED, COMMAND_TYPE, COMMAND_ARGS, COMMAND_OPTIONS, COMMAND_DEFAULT);

    /**
     * GROUP_NAME
     */
    public static CommandString GROUP_NAME = CommandString
        .create("command")
        .required()
        .message("Group name")
        .valid(FunctionsValid.GROUP_COMMAND);

    /**
     * GROUP_COMMAND_NAME
     */
    public static CommandOptionString GROUP_COMMAND_NAME = CommandOptionString
        .create("name")
        .required()
        .message("Command name")
        .valid(FunctionsValid.GROUP_USE_COMMANDS)
        .option(COMMAND_NAME);

    /**
     * GROUP_USE
     */
    public static CommandArray GROUP_USE = CommandArray
        .create("use")
        .required()
        .message("Commands")
        .array(GROUP_COMMAND_NAME);

    /**
     * GROUP_TEMPLATE_NAME
     */
    public static CommandOptionString GROUP_TEMPLATE_NAME = CommandOptionString
        .create("name")
        .required()
        .message("Template name")
        .valid(FunctionsValid.GROUP_USE_TEMPLATES)
        .option(TEMPLATE_NAME);

    /**
     * GROUP_TEMPLATES
     */
    public static CommandArray GROUP_TEMPLATES = CommandArray
        .create("templates")
        .required()
        .message("Templates")
        .array(GROUP_TEMPLATE_NAME);

    /**
     * GROUP_GROUP
     */
    public static CommandArray GROUP_GROUP = CommandArray
        .create("groups")
        .required()
        .message("List of groups");

    static {
        GROUP_GROUP.array(GROUP_NAME, GROUP_USE, GROUP_TEMPLATES, GROUP_GROUP);
    }

    /**
     * commands
     */
    public static final ListCommand commands = ListCommand
        .create()
        .addCommand(PROJECT, VERSION, TEMPLATES, COMMANDS, GROUP_GROUP);
}
