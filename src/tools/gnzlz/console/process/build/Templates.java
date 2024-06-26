package tools.gnzlz.console.process.build;

import tools.gnzlz.filetemplete.TemplateObjects;
import tools.gnzlz.filetemplete.TemplatesCatalog;
import tools.gnzlz.filetemplete.TemplatesModel;
import tools.gnzlz.filetemplete.TemplatesScheme;
import tools.gnzlz.template.TemplateManager;
import tools.gnzlz.template.TemplatesNone;

public class Templates {

    /**
     * templateManager
     */
    private final TemplateManager manager;

    /**
     * templateDatabase
     */
    private final TemplatesCatalog database;

    /**
     * templateScheme
     */
    private final TemplatesScheme scheme;

    /**
     * templateModel
     */
    private final TemplatesModel model;

    /**
     * templateNone
     */
    private final TemplatesNone none;

    /**
     * Templates
     */
    protected Templates(String path, String out){

        manager = TemplateManager.create(path, out);
        database = TemplatesCatalog.create();
        scheme = TemplatesScheme.create();
        model = TemplatesModel.create();
        none = TemplatesNone.create(TemplateObjects.setObjectsCommands);

        manager.add(database).add(scheme).add(model).add(none);
    }

    /**
     * create
     * @param path p
     * @param out o
     */
    public static Templates create(String path, String out){
        return new Templates(path, out);
    }

    /**
     * load
     * @param type type
     * @param name name
     * @param path path
     */
    public void load(String type, String name, String path) {
        switch (type) {
            case "catalog" : case "database" :
                database.load(name, path);
                break;
            case "scheme" :
                scheme.load(name, path);
                break;
            case "model" :
                model.load(name, path);
                break;
            case "none" :
                none.load(name, path);
                break;
        }
    }

    /**
     * manager
     */
    public TemplateManager manager() {
        return manager;
    }
}
