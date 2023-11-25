package gnzlz.console.file.json.project.data;

import java.util.ArrayList;
import java.util.Arrays;

public class FunctionCast {

    /**
     * name
     */
    private String name;

    /**
     * value
     */
    private String value;

    /**
     * type
     */
    private String type;

    /**
     * cast
     */
    private final ArrayList<Cast> cast;

    /**
     * FunctionCast
     */
    private FunctionCast(){
        this.cast = new ArrayList<>();
    }

    /**
     * create
     */
    public static FunctionCast create(){
        return new FunctionCast();
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
    public FunctionCast name(String name) {
        this.name = name;
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
     * @param value value
     */
    public FunctionCast value(String value) {
        this.value = value;
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
     * @param type type
     */
    public FunctionCast type(String type) {
        this.type = type;
        return this;
    }

    /**
     * cast
     */
    public ArrayList<Cast> cast() {
        return cast;
    }

    /**
     * cast
     * @param cast cast
     */
    public FunctionCast cast(Cast ... cast) {
        if(cast != null){
            this.cast.addAll(Arrays.asList(cast));
        }
        return this;
    }
}
