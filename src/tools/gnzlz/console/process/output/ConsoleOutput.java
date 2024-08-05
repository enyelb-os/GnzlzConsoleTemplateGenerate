package tools.gnzlz.console.process.output;

import tools.gnzlz.console.database.sqlite.config.model.Output;
import tools.gnzlz.console.database.sqlite.config.repository.OutputRepository;
import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.command.process.Process;
import tools.gnzlz.command.result.ResultListCommand;
import tools.gnzlz.console.process.output.controller.OutputController;
import tools.gnzlz.console.process.output.model.CommandSchemeCreateOutput;
import tools.gnzlz.console.process.output.model.CommandSchemeEditOutput;
import tools.gnzlz.system.ansi.Color;
import tools.gnzlz.system.io.SystemIO;
import tools.gnzlz.system.text.TextIO;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleOutput {

    /**
     * createOutput
     * @param args args
     */
    public static void createOutput(ArrayList<String> args) {
        InitListCommand oldCommands = InitListCommand.create();
        ResultListCommand commands = Process.argsAndQuestions(args, CommandSchemeCreateOutput.commands, oldCommands);
        OutputRepository.create(
            commands.string("output_path"),
            commands.string("output_name")
        );
    }

    /**
     * editOutput
     * @param args a
     */
    public static void editOutput(ArrayList<String> args) {
        InitListCommand oldCommands = InitListCommand.create();

        ResultListCommand commands = Process.argsAndQuestions(args, CommandSchemeEditOutput.commands, oldCommands);
        var outputIdSearch = !commands.string("output_id").isEmpty();
        var outputs = OutputRepository.findByHash(commands.string("output_id"));

        if (outputs.isEmpty()) {
            SystemIO.OUT.println(Color.RED.print("No results found, press enter to continue"));
            SystemIO.INP.process();
            return;
        }

        String line = "";
        Output output;
        do {
            int index = 0;
            if (outputs.size() > 1 || !outputIdSearch) {
                ConsoleOutput.printListOutput(outputs, true);
                SystemIO.OUT.print(Color.GREEN.print("Choose an option?:"));
                line = SystemIO.INP.process().toString();
                try {
                    index = Integer.parseInt(line) - 1;
                } catch (NumberFormatException e) {
                    index = -1;
                }
            }
            output = (index >= 0 && outputs.size() > index) ? outputs.get(index) : null;

            if (output != null) {
                oldCommands = OutputController.parseOutputToInitListCommand(output);
                ResultListCommand newCommands = Process.argsAndQuestions(args, CommandSchemeCreateOutput.commands, oldCommands);
                OutputRepository.update(output.id(), newCommands.string("output_path"), newCommands.string("output_name"));
            }
        } while(output == null && !line.equalsIgnoreCase("0") && !line.equalsIgnoreCase("exit"));
    }

    /**
     * listOutput
     * @param args a
     */
    public static void listOutput(ArrayList<String> args) {
        var list = OutputRepository.findAll();
        if (list.isEmpty()) {
            SystemIO.OUT.println(Color.RED.print("Outputs is empty, press enter to continue"));
            SystemIO.INP.process();
            return;
        }
        ConsoleOutput.printListOutput(list, false);
        SystemIO.OUT.println(Color.RED.print("Press enter to continue"));
        SystemIO.INP.process();
    }

    /**
     * printListOutput
     * @param list l
     * @param exit e
     */
    public static void printListOutput(ArrayList<Output> list, boolean exit) {
        AtomicInteger i = new AtomicInteger(1);
        SystemIO.OUT.println(Color.BLACK.print(Color.WHITE, TextIO.center("List Output")));
        list.forEach(output -> {
            SystemIO.OUT.println(
                Color.BLACK.print(Color.WHITE,"("+ i +")") + " " +
                Color.BLUE.print(output.name() + ":" + output.hash()) + Color.WHITE.print(" (") +
                Color.GREEN.print(output.path()) + Color.WHITE.print(")")
            );
            i.getAndIncrement();
        });
        if (exit) {
            SystemIO.OUT.println(Color.BLACK.print(Color.WHITE,"(0)") + Color.RED.print(" Exit continue"));
        }
        SystemIO.OUT.println(Color.BLACK.print(Color.WHITE, TextIO.repeat(" ")));
    }
}
