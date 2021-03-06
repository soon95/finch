package org.leon.finch.tool.snail.support;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.leon.finch.common.util.JsonUtil;
import org.leon.finch.common.util.PathUtil;
import org.leon.finch.common.util.TemplateUtil;
import org.leon.finch.tool.snail.SnailGenerator;
import org.leon.finch.tool.snail.command.SnailCommand;
import org.leon.finch.tool.snail.meta.SnailColumn;
import org.leon.finch.tool.snail.meta.SnailTable;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author Leon Song
 * @date 2020-11-06
 */
@Slf4j
public class SnailHelper {

    public final static String TEMPLATE_ENTITY_JAVA = "TemplateEntityJava.vm";
    public final static String TEMPLATE_FORM = "TemplateForm.vm";
    public final static String TEMPLATE_MAPPER_JAVA = "TemplateMapperJava.vm";
    public final static String TEMPLATE_MAPPER_XML = "TemplateMapperXml.vm";

    /**
     * 数据源
     */
    private DataSource dataSource;

    /**
     * 接收所有指令
     */
    private List<SnailCommand> snailCommands = new ArrayList<>();

    /**
     * 所有表元信息
     */
    private List<SnailTable> snailTables;

    public SnailHelper(SnailGenerator snailGenerator) {
        this.dataSource = snailGenerator.getDataSource();
        this.snailCommands = snailGenerator.getSnailCommands();
    }

    /**
     * 获取数据库元信息
     */
    @SneakyThrows
    private List<SnailTable> fetchMetaTable() {

        Connection connection = dataSource.getConnection();

        List<SnailTable> tables = new ArrayList<>();

        DatabaseMetaData metaData = connection.getMetaData();

        // 获取所有表名
        ResultSet resultSet = metaData.getTables(null, "%", "%", new String[]{"TABLE"});

        while (resultSet.next()) {

            String tableName = resultSet.getString("TABLE_NAME");
            String tableRemarks = resultSet.getString("REMARKS");

            // 获取表中所有字段属性
            ResultSet columnResultSet = metaData.getColumns(null, "%", tableName, "%");

            // 查找当前表的主键信息
            ResultSet primaryKeysResultSet = metaData.getPrimaryKeys(null, "%", tableName);
            Set<String> primaryKeys = new HashSet<>();
            while (primaryKeysResultSet.next()) {
                String primaryKey = primaryKeysResultSet.getString("COLUMN_NAME");
                primaryKeys.add(primaryKey);
            }

            List<SnailColumn> columns = new ArrayList<>();
            while (columnResultSet.next()) {

                String columnName = columnResultSet.getString("COLUMN_NAME");
                String remarks = columnResultSet.getString("REMARKS");
                String sqlType = columnResultSet.getString("TYPE_NAME");

                SnailColumn column = new SnailColumn(columnName, remarks, sqlType);

                if (primaryKeys.contains(columnName)) {
                    column.setPrimaryKey(true);
                }

                columns.add(column);

            }

            SnailTable table = new SnailTable(tableName, tableRemarks, columns);

            tables.add(table);
        }

        if (!connection.isClosed()) {
            connection.close();
        }

        return tables;
    }

    /**
     * 根据模板名获取模板内容
     */
    @SneakyThrows
    private static String getTemplateFileContent(String fileName) {

        String filePath = PathUtil.getModuleHomePath(fileName) + "/src/main/resources/" + fileName;

        FileReader fileReader = new FileReader(filePath);


        String content = IOUtils.toString(fileReader);

        fileReader.close();

        return content;
    }

    /**
     * 获得模板的路径
     * 这里随便以一个模板为指南针，找到当前所在的包路径
     */
    private String getSourceFilePath(String fileName) {
        return PathUtil.getModuleHomePath(TEMPLATE_ENTITY_JAVA) + "/src/main/resources/" + fileName;
    }

    /**
     * 拿到模板内容
     */
    @SneakyThrows
    private String getResourceFileContent(String fileName) {

        String filePath = getSourceFilePath(fileName);

        FileReader fileReader = new FileReader(filePath);

        String content = IOUtils.toString(fileReader);

        fileReader.close();

        return content;
    }

    @SneakyThrows
    private void writeFileContent(String filePath, String content) {

        FileWriter fileWriter = new FileWriter(filePath);

        IOUtils.write(content, fileWriter);

        fileWriter.close();

    }

    /**
     * 渲染模板
     */
    private void renderTemplate(String templateName, Map<String, Object> params, String filePath) {

        String template = getResourceFileContent(templateName);

        String content = TemplateUtil.render(template, params);

        writeFileContent(filePath, content);
    }

    private void generateFile(String templateName, Doraemon doraemon, String filePath, boolean overwrite) {

        Map<String, Object> params = JsonUtil.objectToMap(doraemon);

        if (PathUtil.isExist(filePath)) {
            if (overwrite) {

                this.renderTemplate(templateName, params, filePath);

            } else {
                log.info("文件已经存在，取消自动生成；你可以通过设置 overwrite=true 来覆盖文件，文件路径为:{}", filePath);
            }
        } else {

            this.renderTemplate(templateName, params, filePath);

        }
    }

    /**
     * 根据一个生成命令，生成对应的文件
     */
    private void generate(@NonNull SnailCommand command) {

        log.info("开始生成表: {} 对应文件", command.getTableName());

        // 找到对应的表元信息
        SnailTable table = null;
        for (SnailTable snailTable : this.snailTables) {
            if (snailTable.getName().equals(command.getTableName())) {
                table = snailTable;
            }
        }

        // 拿到所有生成文件的原材料
        Doraemon doraemon = new Doraemon(command, table);

        if (command.isNeedEntity()) {
            this.generateFile(TEMPLATE_ENTITY_JAVA, doraemon, command.getEntityAbsolutePath(), command.getOverwriteEntity());
        } else {
            log.info("表 {} 不需要生成Entity，已跳过!", command.getTableName());
        }

        if (command.isNeedMapperJava()) {
            this.generateFile(TEMPLATE_MAPPER_JAVA, doraemon, command.getMapperJavaAbsolutePath(), command.getOverwriteMapperJava());
        } else {
            log.info("表 {} 不需要生成MapperJava，已跳过!", command.getTableName());
        }

        if (command.isNeedMapperXml()) {
            this.generateFile(TEMPLATE_MAPPER_XML, doraemon, command.getMapperXmlAbsolutePath(), command.getOverwriteMapperXml());
        } else {
            log.info("表 {} 不需要生成MapperXml，已跳过!", command.getTableName());
        }


    }


    /**
     * 真正进行处理的地方
     */
    private void process() {

        log.info("Snail开始执行");

        // 校验用户输入数据，并进行默认值回填
        this.snailCommands.forEach(SnailCommand::prepare);
        log.info("用户输入校验和默认值回填完成！");

        // 获取所有 表元信息 和 字段元信息
        this.snailTables = this.fetchMetaTable();
        log.info("获取到如下表元信息：");
        this.snailTables.forEach(table -> log.info("表名为:{}", table.getName()));
        log.info("获取表元信息完成！");


        // 根据配置的信息，生成对应表的代码
        this.snailCommands.forEach((this::generate));


        log.info("Snail执行完毕");
    }


    public void start() {

        try {
            this.process();
        } catch (Throwable throwable) {
            log.error("Snail代码生成过程出错！");
            log.error(ExceptionUtils.getRootCauseMessage(throwable), throwable);
        }
    }

}
