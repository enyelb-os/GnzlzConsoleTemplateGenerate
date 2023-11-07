package gnzlz.console.process.output;

import gnzlz.console.database.sqlite.config.repository.OutputRepository;
import tools.gnzlz.command.command.object.ListCommand;
import tools.gnzlz.command.command.type.CommandString;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.process.Process;
import tools.gnzlz.command.result.ResultListCommand;

public class ConsoleOutput {

    /**
     * OUTPUT_PATH
     */
    public final static CommandString OUTPUT_PATH = CommandString
        .create("output_path")
        .commands("--ouputpath", "-outpath")
        .required()
        .message("Output path")
        .value(System.getProperty("user.dir"));

    /**
     * PROJECT_NAME
     */
    public static CommandString OUTPUT_NAME = CommandString
        .create("output_name")
        .required()
        .message("Output name");

    /**
     * commandsCreateProject
     */
    private static final ListCommand commandsCreateOutput = ListCommand
        .create()
        .addCommand(OUTPUT_NAME, OUTPUT_PATH);


    /**
     * createOutput
     * @param args args
     */
    public static void createOutput(String ... args) {
        InitListCommand oldCommands = InitListCommand.create();
        ResultListCommand commands = Process.argsAndQuestions(args, commandsCreateOutput, oldCommands);
        OutputRepository.create(
            commands.string("output_path"),
            commands.string("output_name")
        );
    }
}
