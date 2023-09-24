package gnzlz.console.file.json;

public class Option {

    private String option;

    private Option(){
    }

    public static Option create(){
        return new Option();
    }

    public String option() {
        return this.option;
    }

    public Option option(String option) {
        this.option = option;
        return this;
    }
}
