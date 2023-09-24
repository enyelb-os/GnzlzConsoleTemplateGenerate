package gnzlz.console.file.json;

import java.util.ArrayList;

public class Project {

    private String name;

    private String version;

    private ArrayList<Template> templates;

    private ArrayList<Command> commands;

    private ArrayList<Group> groups;

    private Project(){
        templates = new ArrayList<Template>();
        commands = new ArrayList<Command>();
        groups = new ArrayList<Group>();
    }

    public static Project create(){
        return new Project();
    }

    public String name() {
        return name;
    }

    public Project name(String name) {
        this.name = name;
        return this;
    }

    public String version() {
        return version;
    }

    public Project version(String version) {
        this.version = version;
        return this;
    }

    public ArrayList<Template> templates() {
        return templates;
    }

    public Project templates(Template ... templates) {
        if(templates != null){
            for (Template template: templates) {
                this.templates.add(template);
            }
        }
        return this;
    }

    public ArrayList<Command> commands() {
        return commands;
    }

    public Project commands(Command ... commands) {
        if(commands != null){
            for (Command command: commands) {
                this.commands.add(command);
            }
        }
        return this;
    }

    public ArrayList<Group> groups() {
        return groups;
    }

    public Project groups(Group ... groups) {
        if(groups != null){
            for (Group group: groups) {
                this.groups.add(group);
            }
        }
        return this;
    }
}
