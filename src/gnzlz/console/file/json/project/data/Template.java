package gnzlz.console.file.json.project.data;

public class Template {

    /**
     * name
     */
    private String name;

    /**
     * path
     */
    private String path;

    /**
     * type
     */
    private String type;

    /**
     * Template
     */
    private Template(){
    }

    /**
     * create
     */
    public static Template create(){
        return new Template();
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
    public Template name(String name) {
        this.name = name;
        return this;
    }

    /**
     * path
     */
    public String path() {
        return path;
    }

    /**
     * path
     * @param path p
     */
    public Template path(String path) {
        this.path = path;
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
    public Template type(String type) {
        this.type = type;
        return this;
    }
}
