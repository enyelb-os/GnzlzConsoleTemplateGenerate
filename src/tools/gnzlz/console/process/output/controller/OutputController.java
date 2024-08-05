package tools.gnzlz.console.process.output.controller;

import tools.gnzlz.command.init.InitListCommand;
import tools.gnzlz.console.database.sqlite.config.model.Output;
import tools.gnzlz.console.process.output.model.CommandSchemeCreateOutput;

public class OutputController {

    /**
     * parseOutputToInitListCommand
     * @param output o
     */
    public static InitListCommand parseOutputToInitListCommand(Output output) {
        if (output == null) {
            return InitListCommand.create();
        }
        return InitListCommand.create()
            .addCommand(CommandSchemeCreateOutput.OUTPUT_NAME, output.name())
            .addCommand(CommandSchemeCreateOutput.OUTPUT_PATH, output.path());
    }
}
