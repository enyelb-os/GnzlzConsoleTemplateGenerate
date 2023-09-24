package gnzlz.console.file.json;

import java.util.ArrayList;

public class Group {

    private String command;

    private ArrayList<String> use;

    private ArrayList<String> templates;

    private ArrayList<Group> groups;

    private Group(){
        use = new ArrayList<String>();
        templates = new ArrayList<String>();
        groups = new ArrayList<Group>();
    }

    public static Group create(){
        return new Group();
    }

    public String command() {
        return command;
    }

    public Group command(String command) {
        this.command = command;
        return this;
    }

    public ArrayList<String> use() {
        return use;
    }

    public Group use(String ... uses) {
        if(uses != null){
            for (String use:uses) {
                this.use.add(use);
            }
        }
        return this;
    }

    public ArrayList<String> templates() {
        return templates;
    }

    public Group templates(String ... templates) {
        if(templates != null){
            for (String template : templates) {
                this.templates.add(template);
            }
        }
        return this;
    }

    public ArrayList<Group> groups() {
        return groups;
    }

    public Group groups(Group ... groups) {
        if(groups != null){
            for (Group group: groups) {
                this.groups.add(group);
            }
        }
        return this;
    }
}
