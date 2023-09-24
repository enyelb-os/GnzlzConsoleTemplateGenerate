package gnzlz.console.file.json;

public class Template {

    private String name;

    private String path;

    private String type;

    private Template(){
    }

    public static Template create(){
        return new Template();
    }

    public String name() {
        return name;
    }

    public Template name(String name) {
        this.name = name;
        return this;
    }

    public String path() {
        return path;
    }

    public Template path(String path) {
        this.path = path;
        return this;
    }

    public String type() {
        return type;
    }

    public Template type(String type) {
        this.type = type;
        return this;
    }
}
