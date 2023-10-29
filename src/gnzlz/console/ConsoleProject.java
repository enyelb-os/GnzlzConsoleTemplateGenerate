package gnzlz.console;

import tools.gnzlz.command.command.object.ListCommand;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.process.Process;
import tools.gnzlz.command.result.ResultListCommand;
import tools.gnzlz.command.command.type.CommandArray;
import tools.gnzlz.command.command.type.CommandBoolean;
import tools.gnzlz.command.command.type.CommandOptionString;
import tools.gnzlz.command.command.type.CommandString;

public class ConsoleProject {

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
        .message("Template name");

    /**
     * TEMPLATE_PATH
     */
    public static CommandString TEMPLATE_PATH = CommandString
        .create("path")
        .required()
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
        .message("Lista de templates")
        .array(TEMPLATE_NAME, TEMPLATE_PATH, TEMPLATE_TYPE);

    /**
     * COMMAND_NAME
     */
    public static CommandString COMMAND_NAME = CommandString
        .create("name")
        .required()
        .message("Nombre del comando");

    /**
     * COMMAND_REQUIRED
     */
    public static CommandBoolean COMMAND_REQUIRED = CommandBoolean
        .create("required")
        .required()
        .message("Es requerido")
        .value(true);

    /**
     * COMMAND_TYPE
     */
    public static CommandOptionString COMMAND_TYPE = CommandOptionString
        .create("type")
        .required()
        .message("Tipo de comando")
        .option("number","string","option");

    /**
     * COMMAND_DEFAULT
     */
    public static CommandString COMMAND_DEFAULT = CommandString
        .create("value")
        .required()
        .message("Valor por defecto");

    /**
     * COMMAND_OPTION
     */
    public static CommandString COMMAND_OPTION = CommandString
        .create("option")
        .required()
        .message("Nombre de la opcion");

    /**
     * COMMAND_OPTIONS
     */
    public static CommandArray COMMAND_OPTIONS = CommandArray
        .create("options")
        .required()
        .message("Opciones")
        .array(COMMAND_OPTION);

    /**
     * COMMANDS
     */
    public static CommandArray COMMANDS = CommandArray
        .create("commands")
        .required()
        .message("Lista de comandos")
        .array(COMMAND_NAME, COMMAND_REQUIRED, COMMAND_TYPE, COMMAND_OPTIONS, COMMAND_DEFAULT);

    /**
     * GROUP_NAME
     */
    public static CommandString GROUP_NAME = CommandString
        .create("command")
        .required()
        .message("Nombre del comando");

    /**
     * GROUP_COMMAND_NAME
     */
    public static CommandOptionString GROUP_COMMAND_NAME = CommandOptionString
        .create("name")
        .required()
        .message("Commando a usar")
        .option(COMMAND_NAME);

    /**
     * GROUP_USE
     */
    public static CommandArray GROUP_USE = CommandArray
        .create("use")
        .required()
        .message("Comandos")
        .array(GROUP_COMMAND_NAME);

    /**
     * GROUP_TEMPLATE_NAME
     */
    public static CommandOptionString GROUP_TEMPLATE_NAME = CommandOptionString
        .create("name")
        .required()
        .message("Templates a usar")
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
        .message("Lista de grupos");

    static {
        GROUP_GROUP.array(GROUP_NAME, GROUP_USE, GROUP_TEMPLATES, GROUP_GROUP);
    }

    /**
     * commands
     */
    private static ListCommand commands = ListCommand
        .create()
        .addCommand(PROJECT, VERSION, TEMPLATES, COMMANDS, GROUP_GROUP);


    /**
     * processCommandsCreateProjectJSon
     * @param old initListCommand
     */
    public static ResultListCommand processCommandsCreateProjectJSon(InitListCommand old){
        return Process.questions(commands, old);
    }
}
