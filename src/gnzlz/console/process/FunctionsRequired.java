package gnzlz.console.process;

import tools.gnzlz.command.functional.FunctionRequiredCommand;

public class FunctionsRequired {

    /**
     * OPTIONS
     */
    public static FunctionRequiredCommand OPTIONS = (allCommands, commands) -> commands.string("type").equalsIgnoreCase("option");
}
