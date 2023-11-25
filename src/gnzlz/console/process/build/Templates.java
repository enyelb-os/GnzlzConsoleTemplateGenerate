package gnzlz.console.process.build;

import gnzlz.console.ModelBase;
import tools.gnzlz.filetemplete.TemplatesCatalog;
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
     * Templates
     */
    protected Templates(String path, String out){

        manager = TemplateManager.create(path, out);
        database = TemplatesCatalog.create();
        scheme = TemplatesScheme.create();
        model = TemplatesModel.create().object("model", ModelBase.class);

        manager.add(database).add(scheme).add(model);
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
                //templatesModel.load(template.name(), JSON.path(project.path()) + template.path());
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
