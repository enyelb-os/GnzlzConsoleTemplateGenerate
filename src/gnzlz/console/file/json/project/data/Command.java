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
     * options
     */
    private final ArrayList<Option> options;

    /**
     * value
     */
    private String value;

    /**
     * question
     */
    private String question;

    /**
     * Command
     */
    private Command(){
        options = new ArrayList<Option>();
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
     * options
     */
    public ArrayList<Option> options() {
        return options;
    }

    /**
     * options
     * @param options o
     */
    public Command options(Option ... options) {
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
     * question
     */
    public String question() {
        return question;
    }

    /**
     * question
     * @param question q
     */
    public Command question(String question) {
        this.question = question;
        return this;
    }
}
