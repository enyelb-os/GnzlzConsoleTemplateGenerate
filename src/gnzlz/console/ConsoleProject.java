package gnzlz.console;

import tools.gnzlz.command.Process;
import tools.gnzlz.command.*;
import tools.gnzlz.command.ResultListCommand;
import tools.gnzlz.command.type.CommandArray;
import tools.gnzlz.command.type.CommandBoolean;
import tools.gnzlz.command.type.CommandOption;
import tools.gnzlz.command.type.CommandString;

public class ConsoleProject {

    /*********************************
     * listCommand create Project
     *********************************/

    public static Command PROJECT;
    public static Command VERSION;
    public static Command TEMPLATE_NAME;
    public static Command TEMPLATE_PATH;
    public static Command TEMPLATE_TYPE;
    public static Command TEMPLATES;
    public static Command COMMAND_NAME;
    public static Command COMMAND_REQUIRED;
    public static Command COMMAND_TYPE;
    public static Command COMMAND_DEFAULT;
    public static Command COMMAND_OPTION;
    public static Command COMMAND_OPTIONS;
    public static Command COMMANDS;
    public static Command GROUP_NAME;
    public static Command GROUP_COMMAND_NAME;
    public static Command GROUP_USE;
    public static Command GROUP_TEMPLATE_NAME;
    public static Command GROUP_TEMPLATES;
    public static CommandArray GROUP_GROUP;

    /*********************************
     * listCommand create Project
     *********************************/

    private static ListCommand commands = ListCommand.create();

    static {
        PROJECT             = CommandString.create("project").required(true).message("Project name");
        VERSION             = CommandString.create("version").required(true).message("Version").value("1.0");
        TEMPLATE_NAME       = CommandString.create("name").required().message("Template name");
        TEMPLATE_PATH       = CommandString.create("path").required().message("Path file");
        TEMPLATE_TYPE       = CommandOption.create("type").required().message("Template type").option("model","catalog","scheme","none");
        TEMPLATES           = CommandArray.create("templates").required().message("Lista de templates").array(TEMPLATE_NAME, TEMPLATE_PATH, TEMPLATE_TYPE);
        COMMAND_NAME        = CommandString.create("name").required().message("Nombre del comando");
        COMMAND_REQUIRED    = CommandBoolean.create("required").required().message("Es requerido").value(true);
        COMMAND_TYPE        = CommandOption.create("type").required().message("Tipo de comando").option("number","string","option");
        COMMAND_DEFAULT     = CommandString.create("value").required().message("Valor por defecto");
        COMMAND_OPTION      = CommandString.create("option").required().message("Nombre de la opcion");
        COMMAND_OPTIONS     = CommandArray.create("options").required().message("Opciones").array(COMMAND_OPTION);
        COMMANDS            = CommandArray.create("commands").required().message("Lista de comandos").array(COMMAND_NAME, COMMAND_REQUIRED, COMMAND_TYPE, COMMAND_OPTIONS, COMMAND_DEFAULT);
        GROUP_NAME          = CommandString.create("command").required().message("Nombre del comando");
        GROUP_COMMAND_NAME  = CommandOption.create("name").required().message("Commando a usar").option(COMMAND_NAME);
        GROUP_USE           = CommandArray.create("use").required().message("Comandos").array(GROUP_COMMAND_NAME);
        GROUP_TEMPLATE_NAME = CommandOption.create("name").required().message("Templates a usar").option(TEMPLATE_NAME);
        GROUP_TEMPLATES     = CommandArray.create("templates").required().message("Templates").array(GROUP_TEMPLATE_NAME);
        GROUP_GROUP         = CommandArray.create("groups").required().message("Lista de grupos");

        GROUP_GROUP.array(GROUP_NAME, GROUP_USE, GROUP_TEMPLATES, GROUP_GROUP);

        commands.addCommand(PROJECT, VERSION, TEMPLATES, COMMANDS, GROUP_GROUP);
    }

    public static ResultListCommand processCommandsCreateProjectJSon(ResultListCommand old){
        return Process.questions(commands, old);
    }
}
