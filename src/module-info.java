module GnzlzConsoleTemplateGenerate {

    opens tools.gnzlz.console.database.sqlite.config;
    opens tools.gnzlz.console.database.sqlite.config.model;
    opens tools.gnzlz.console.database.migration;
    opens tools.gnzlz.console.file.json.project.data;
    opens tools.gnzlz.console;
    opens tools.gnzlz.console.process;

    requires com.google.gson;
    requires org.xerial.sqlitejdbc;
    requires mysql.connector.j;

    requires tools.gnzlz.system.io;
    requires tools.gnzlz.database;
    requires tools.gnzlz.command;
    requires tools.gnzlz.filetemplate;
}