package tools.gnzlz.console.process.output.model;

import tools.gnzlz.command.CommandString;
import tools.gnzlz.command.command.object.ListCommand;

public class CommandSchemeEditOutput {

    /**
     * OUTPUT_ID
     */
    public static CommandString OUTPUT_ID = CommandString
        .create("output_id")
        .commands("--outputid", "-outid")
        .message("Output name");

    /**
     * commands
     */
    public static final ListCommand commands = ListCommand
        .create()
        .addCommand(OUTPUT_ID);
}
