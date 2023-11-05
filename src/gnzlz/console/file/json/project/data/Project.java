package gnzlz.console.file.json.project.data;

import java.util.ArrayList;
import java.util.Arrays;

public class Project {

    /**
     * name
     */
    private String name;

    /**
     * version
     */
    private String version;

    /**
     * templates
     */
    private final ArrayList<Template> templates;

    /**
     * commands
     */
    private final ArrayList<Command> commands;

    /**
     * groups
     */
    private final ArrayList<Group> groups;

    /**
     * Project
     */
    private Project(){
        templates = new ArrayList<Template>();
        commands = new ArrayList<Command>();
        groups = new ArrayList<Group>();
    }

    /**
     * create
     */
    public static Project create(){
        return new Project();
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
    public Project name(String name) {
        this.name = name;
        return this;
    }

    /**
     * version
     */
    public String version() {
        return version;
    }

    /**
     * version
     * @param version v
     */
    public Project version(String version) {
        this.version = version;
        return this;
    }

    /**
     * templates
     */
    public ArrayList<Template> templates() {
        return templates;
    }

    /**
     * templates
     * @param templates t
     */
    public Project templates(Template ... templates) {
        if(templates != null){
            this.templates.addAll(Arrays.asList(templates));
        }
        return this;
    }

    /**
     * commands
     */
    public ArrayList<Command> commands() {
        return commands;
    }

    /**
     * commands
     * @param commands c
     */
    public Project commands(Command ... commands) {
        if(commands != null){
            this.commands.addAll(Arrays.asList(commands));
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
    public Project groups(Group ... groups) {
        if(groups != null){
            this.groups.addAll(Arrays.asList(groups));
        }
        return this;
    }
}
