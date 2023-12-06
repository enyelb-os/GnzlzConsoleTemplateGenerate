package tools.gnzlz.console.file.json.project.data;

import java.util.ArrayList;
import java.util.Arrays;

public class Group {

    /**
     * command
     */
    private String command;

    /**
     * use
     */
    private final ArrayList<String> use;

    /**
     * templates
     */
    private final ArrayList<String> templates;

    /**
     * groups
     */
    private final ArrayList<Group> groups;

    /**
     * Group
     */
    private Group(){
        use = new ArrayList<String>();
        templates = new ArrayList<String>();
        groups = new ArrayList<Group>();
    }

    /**
     * create
     */
    public static Group create(){
        return new Group();
    }

    /**
     * command
     */
    public String command() {
        return command;
    }

    /**
     * command
     * @param command c
     */
    public Group command(String command) {
        this.command = command;
        return this;
    }

    /**
     * use
     */
    public ArrayList<String> use() {
        return use;
    }

    /**
     * use
     * @param uses u
     */
    public Group use(String ... uses) {
        if(uses != null){
            this.use.addAll(Arrays.asList(uses));
        }
        return this;
    }

    /**
     * templates
     */
    public ArrayList<String> templates() {
        return templates;
    }

    /**
     * templates
     * @param templates t
     */
    public Group templates(String ... templates) {
        if(templates != null){
            this.templates.addAll(Arrays.asList(templates));
        }
        return this;
    }

    /**
     * groups
     */
    public ArrayList<Group> groups() {
        return groups;
    }

    /**
     * groups
     * @param groups g
     */
    public Group groups(Group ... groups) {
        if(groups != null){
            this.groups.addAll(Arrays.asList(groups));
        }
        return this;
    }
}
