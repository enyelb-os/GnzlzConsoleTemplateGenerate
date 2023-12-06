package tools.gnzlz.console.file.json.project.data;

public class Cast {

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private String value;


    /**
     * Cast
     */
    private Cast(){
    }

    /**
     * create
     */
    public static Cast create(){
        return new Cast();
    }

    /**
     * key
     */
    public String key() {
        return this.key;
    }

    /**
     * key
     * @param key key
     */
    public Cast key(String key) {
        this.key = key;
        return this;
    }

    /**
     * value
     */
    public String value() {
        return this.value;
    }

    /**
     * value
     * @param value value
     */
    public Cast value(String value) {
        this.value = value;
        return this;
    }
}
