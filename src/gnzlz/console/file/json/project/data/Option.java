package gnzlz.console.file.json.project.data;

public class Option {

    /**
     * option
     */
    private String option;

    /**
     * Option
     */
    private Option(){
    }

    /**
     * create
     */
    public static Option create(){
        return new Option();
    }

    /**
     * option
     */
    public String option() {
        return this.option;
    }

    /**
     * option
     * @param option o
     */
    public Option option(String option) {
        this.option = option;
        return this;
    }
}
