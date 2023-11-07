package gnzlz.console.file.json.project.data;

import java.util.ArrayList;
import java.util.Arrays;

public class Command {

    /**
     * name
     */
    private String name;

    /**
     * required
     */
    private boolean required;

    /**
     * type
     */
    private String type;

    /**
     * args
     */
    private final ArrayList<String> args;

    /**
     * options
     */
    private final ArrayList<String> options;

    /**
     * value
     */
    private String value;

    /**
     * message
     */
    private String message;

    /**
     * Command
     */
    private Command(){
        options = new ArrayList<String>();
        args = new ArrayList<String>();
    }

    /**
     * create
     */

    public static Command create(){
        return new Command();
    }

    /**
     * name
     */
    public String name() {
        return name;
    }

    /**
     * name
     * @param name n
     */

    public Command name(String name) {
        this.name = name;
        return this;
    }

    /**
     * isRequired
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * required
     * @param required r
     */
    public Command required(boolean required) {
        this.required = required;
        return this;
    }

    /**
     * type
     */
    public String type() {
        return type;
    }

    /**
     * type
     * @param type t
     */
    public Command type(String type) {
        this.type = type;
        return this;
    }

    /**
     * args
     */
    public ArrayList<String> args() {
        return args;
    }

    /**
     * options
     * @param args o
     */
    public Command args(String ... args) {
        if(args != null) {
            this.args.addAll(Arrays.asList(args));
        }
        return this;
    }


    /**
     * options
     */
    public ArrayList<String> options() {
        return options;
    }

    /**
     * options
     * @param options o
     */
    public Command options(String ... options) {
        if(options != null) {
            this.options.addAll(Arrays.asList(options));
        }
        return this;
    }

    /**
     * value
     */
    public String value() {
        return value;
    }

    /**
     * value
     * @param value v
     */
    public Command value(String value) {
        this.value = value;
        return this;
    }

    /**
     * message
     */
    public String message() {
        return message;
    }

    /**
     * message
     * @param message q
     */
    public Command message(String message) {
        this.message = message;
        return this;
    }
}
