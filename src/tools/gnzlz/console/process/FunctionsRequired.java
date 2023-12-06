package tools.gnzlz.console.process;

import tools.gnzlz.command.command.functional.FunctionRequiredCommand;

public class FunctionsRequired {

    /**
     * OPTIONS
     */
    public static FunctionRequiredCommand OPTIONS = (data) -> data.list.string("type").equalsIgnoreCase("option");
}
