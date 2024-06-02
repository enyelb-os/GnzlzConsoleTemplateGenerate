package tools.gnzlz.console.process.output;

import tools.gnzlz.console.database.sqlite.config.repository.OutputRepository;
import tools.gnzlz.command.command.object.ListCommand;
import tools.gnzlz.command.CommandString;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.process.Process;
import tools.gnzlz.command.result.ResultListCommand;
import tools.gnzlz.database.model.DBConnection;
import tools.gnzlz.system.ansi.Color;
import tools.gnzlz.system.io.SystemIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

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
    public static void createOutput(ArrayList<String> args) {
        InitListCommand oldCommands = InitListCommand.create();
        ResultListCommand commands = Process.argsAndQuestions(args, commandsCreateOutput, oldCommands);
        OutputRepository.create(
            commands.string("output_path"),
            commands.string("output_name")
        );
    }

    /**
     * listOutput
     * @param args a
     */
    public static void listOutput(ArrayList<String> args) {
        var list = OutputRepository.findAll();
        SystemIO.OUT.println("");
        if (list.isEmpty()) {
            SystemIO.OUT.println(Color.RED.print("Outputs is empty, press enter to continue"));
            SystemIO.INP.process();
            return;
        }
        AtomicInteger i = new AtomicInteger(1);
        list.forEach(output -> {
            SystemIO.OUT.println(
                Color.BLACK.print(Color.WHITE,"["+ i +"]") + " " +
                Color.BLUE.print(output.name() + ":" + output.hash()) + Color.WHITE.print(" (") +
                Color.GREEN.print(output.path()) + Color.WHITE.print(")")
            );

            i.getAndIncrement();
        });
        SystemIO.OUT.println("");
        SystemIO.OUT.println(Color.RED.print("Press enter to continue"));
        SystemIO.INP.process();
    }

    /**
     * main
     * @param args a
     */
    public static void main(String[] args) {
        DBConnection.outMetaData = false;
        DBConnection.outMigration = false;
        DBConnection.outModel = false;
        ConsoleOutput.createOutput(new ArrayList<>(Arrays.asList(args)));
    }

    /**
     * mainDB
     * @param args a
     */
    public static void mainCreate(String[] args) {
        ConsoleOutput.createOutput(new ArrayList<>(Arrays.asList(args)));
    }

    /**
     * main
     * @param args a
     */
    public static void mainList(String[] args) {
        ConsoleOutput.listOutput(new ArrayList<>(Arrays.asList(args)));
    }
}
