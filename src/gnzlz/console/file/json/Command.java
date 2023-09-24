package gnzlz.console.file.json;

import java.util.ArrayList;

public class Command {

    private String name;

    private boolean required;

    private String type;

    private ArrayList<Option> options;

    private String value;

    private String question;

    private Command(){
        options = new ArrayList<Option>();
    }

    public static Command create(){
        return new Command();
    }

    public String name() {
        return name;
    }

    public Command name(String name) {
        this.name = name;
        return this;
    }

    public boolean isRequired() {
        return required;
    }

    public Command required(boolean required) {
        this.required = required;
        return this;
    }

    public String type() {
        return type;
    }

    public Command type(String type) {
        this.type = type;
        return this;
    }

    public ArrayList<Option> options() {
        return options;
    }

    public Command options(Option ... options) {
        if(options != null) {
            for (Option option: options) {
                this.options.add(option);
            }
        }
        return this;
    }

    public String value() {
        return value;
    }

    public Command value(String value) {
        this.value = value;
        return this;
    }

    public String question() {
        return question;
    }

    public Command question(String question) {
        this.question = question;
        return this;
    }
}
