package tools.gnzlz.console.process.output.model;

import tools.gnzlz.command.CommandString;
import tools.gnzlz.command.command.object.ListCommand;

public class CommandSchemeCreateOutput {

    /**
     * OUTPUT_PATH
     */
    public final static CommandString OUTPUT_PATH = CommandString
        .create("output_path")
        .commands("--outputpath", "-outpath")
        .required()
        .message("Output path")
        .value(System.getProperty("user.dir"));

    /**
     * PROJECT_NAME
     */
    public static CommandString OUTPUT_NAME = CommandString
        .create("output_name")
        .commands("--outputname", "-outname")
        .required()
        .message("Output name");

    /**
     * commandsCreateProject
     */
    public static final ListCommand commands = ListCommand
        .create()
        .addCommand(OUTPUT_NAME, OUTPUT_PATH);
}
