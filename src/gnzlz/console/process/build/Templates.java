package gnzlz.console.process.build;

import gnzlz.console.ModelBase;
import tools.gnzlz.filetemplete.TemplatesDatabase;
import tools.gnzlz.filetemplete.TemplatesModel;
import tools.gnzlz.filetemplete.TemplatesScheme;
import tools.gnzlz.template.TemplateManager;

public class Templates {

    /**
     * templateManager
     */
    private final TemplateManager manager;

    /**
     * templateDatabase
     */
    private final TemplatesDatabase database;


    /**
     * templateScheme
     */
    private final TemplatesScheme scheme;

    /**
     * templateModel
     */
    private final TemplatesModel model;

    /**
     * Templates
     */
    protected Templates(String path, String out, boolean dbmodel){
        TemplatesDatabase.isObjectsDBModel = dbmodel;

        manager = TemplateManager.create(path, out);
        database = TemplatesDatabase.create();
        scheme = TemplatesScheme.create();
        model = TemplatesModel.create().object("model", ModelBase.class);

        manager.add(database).add(scheme).add(model);
    }

    /**
     * create
     * @param path p
     * @param out o
     */
    public static Templates create(String path, String out, boolean dbmodel){
        return new Templates(path, out, dbmodel);
    }

    /**
     * load
     * @param type type
     * @param name name
     * @param path path
     */
    public Templates load(String type, String name, String path) {
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
                //templatesModel.load(template.name(), JSON.path(project.path()) + template.path());
                break;
        }
        return this;
    }

    /**
     * manager
     */
    public TemplateManager manager() {
        return manager;
    }
}
