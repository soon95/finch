package org.leon.finch.tool.snail.command;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.leon.finch.common.exception.BadRequestException;
import org.leon.finch.common.util.PathUtil;
import org.leon.finch.common.util.StringUtil;
import org.leon.finch.dal.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户对于生成文件的配置
 *
 * @author Leon Song
 * @date 2020-11-06
 */
@Slf4j
@Data
public class SnailCommand {

    /**
     * 数据库表名
     */
    private String tableName;

    /**
     * 默认与表名相同的驼峰写法
     * 如果表名有一些通用前缀,例如:t_foo ,则需要指定为foo
     */
    private String entityName;

    /**
     * dal模块名
     */
    private String dalModuleName;

    /**
     * dal的主路径
     */
    private String dalPackage;

    /**
     * 实体类所在包
     */
    private String entityPackage;

    /**
     * 类名
     * 默认是entityName的驼峰
     */
    private String entityUpperClassName;

    /**
     * 表单名
     * 默认是EntityNameForm
     */
    private String formUpperClassName;

    /**
     * mapper所在包
     */
    private String mapperPackage;

    /**
     * mapper类名
     */
    private String mapperUpperClassName;

    /**
     * xml所在文件
     * 相对于resources文件夹
     * 默认 mybatis/mapper
     */
    private String xmlFolder;

    /**
     * xml文件名
     * 默认 表名的驼峰写法+mapper
     */
    private String xmlName;

    /**
     * equal筛选字段
     */
    private List<String> pageEqualFields;

    /**
     * like筛选字段
     */
    private List<String> pageLikeFields;

    /**
     * 排序字段
     */
    private List<String> pageOrderFields;

    /**
     * 数据库中存在的字段，但是已经废弃了的
     */
    private List<String> ignoreFields;

    /**
     * 是否需要entity文件
     */
    private boolean needEntity = true;

    /**
     * 是否需要form文件
     */
    private boolean needForm = true;

    /**
     * 是否需要mapper.java文件
     */
    private boolean needMapperJava = true;

    /**
     * 是否需要mapper.xml文件
     */
    private boolean needMapperXml = true;

    /**
     * 是否覆盖所有
     */
    private boolean overwriteAll = false;

    /**
     * 是否覆盖entity
     */
    private Boolean overwriteEntity;

    /**
     * 是否覆盖mapperJava
     */
    private Boolean overwriteMapperJava;

    /**
     * 是否覆盖mapperXml
     */
    private Boolean overwriteMapperXml;


    public SnailCommand(@NonNull String tableName) {
        this.tableName = tableName;
    }

    /**
     * 校验参数，以及回填默认值
     */
    public void prepare() {

        // 表名不能为空
        if (StringUtil.isBlank(tableName)) {
            throw new BadRequestException("表名不能为空");
        }

        // 实体名默认与表名相同
        if (StringUtil.isBlank(entityName)) {
            this.entityName = tableName;
        }

        String appName = PathUtil.getAppName();

        // dal模块名
        if (StringUtil.isBlank(dalModuleName)) {
            this.dalModuleName = appName + "-dal";
        }

        // 这里以BaseEntity文件为指南针
        if (StringUtil.isBlank(dalPackage)) {
            this.dalPackage = PathUtil.prePackage(PathUtil.getPackageName(BaseEntity.class));
        }


        if (StringUtil.isBlank(entityPackage)) {
            this.entityPackage = dalPackage + "." + entityName;
        }

        if (StringUtil.isBlank(entityUpperClassName)) {
            this.entityUpperClassName = StringUtil.underscoreToUpperCamel(entityName);
        }

        if (StringUtil.isBlank(formUpperClassName)) {
            this.formUpperClassName = entityUpperClassName + "Form";
        }

        if (StringUtil.isBlank(mapperPackage)) {
            this.mapperPackage = dalPackage + "." + entityName;
        }

        if (StringUtil.isBlank(mapperUpperClassName)) {
            this.mapperUpperClassName = entityUpperClassName + "Mapper";
        }

        if (StringUtil.isBlank(xmlFolder)) {
            this.xmlFolder = StringUtil.format("mybatis/mapper/{}", entityName);
        }

        if (StringUtil.isBlank(xmlName)) {
            this.xmlName = mapperUpperClassName;
        }

        if (null == pageOrderFields) {
            pageOrderFields = new ArrayList<>();
        }

        // 添加默认排序字段 id, gmt_create, gmt_modified
        if (!pageOrderFields.contains("gmt_modified")) {
            pageOrderFields.add(0, "gmt_modified");
        }
        if (!pageOrderFields.contains("gmt_create")) {
            pageOrderFields.add(0, "gmt_create");
        }
        if (!pageOrderFields.contains("id")) {
            pageOrderFields.add(0, "id");
        }

        if (null == pageLikeFields) {
            pageLikeFields = new ArrayList<>();
        }

        if (null == pageEqualFields) {
            pageEqualFields = new ArrayList<>();
        }


        if (null == ignoreFields) {
            ignoreFields = new ArrayList<>();
        }

    }


    /**
     * 获取到entity文件的绝对路径
     */
    public String getEntityAbsolutePath() {

        String directoryPath = PathUtil.getAppHomePath() + "/" + this.getDalModuleName() + "/src/main/java/" + this.entityPackage.replaceAll("\\.", "/");

        String safeDirectoryPath = PathUtil.getSafeDirectoryPath(directoryPath);

        return safeDirectoryPath + "/" + this.entityUpperClassName + ".java";
    }

    /**
     * 获得mapper文件的绝对路径
     */
    public String getMapperJavaAbsolutePath() {

        String directoryPath = PathUtil.getAppHomePath() + "/" + this.getDalModuleName() + "/src/main/java/" + this.mapperPackage.replaceAll("\\.", "/");

        String safeDirectoryPath = PathUtil.getSafeDirectoryPath(directoryPath);

        return safeDirectoryPath + "/" + this.mapperUpperClassName + ".java";

    }

    /**
     * 获得mapper xml文件的绝对路径
     */
    public String getMapperXmlAbsolutePath(){

        String directoryPath=PathUtil.getAppHomePath()+"/"+this.getDalModuleName()+"/src/main/resources/"+this.xmlFolder;

        String safeDirectoryPath = PathUtil.getSafeDirectoryPath(directoryPath);

        return safeDirectoryPath+"/"+this.xmlName+".xml";

    }

    /**
     * 有具体的就覆盖全局的
     */
    public boolean getOverwriteEntity() {
        if (null == this.overwriteEntity) {
            return this.overwriteAll;
        } else {
            return this.overwriteEntity;
        }
    }

    /**
     * 是否覆盖
     */
    public boolean getOverwriteMapperJava() {
        if (null == this.overwriteMapperJava) {
            return this.overwriteAll;
        } else {
            return this.overwriteMapperJava;
        }
    }

    /**
     * 是否覆盖
     */
    public boolean getOverwriteMapperXml(){
        if (null == this.overwriteMapperXml) {
            return this.overwriteAll;
        } else {
            return this.overwriteMapperXml;
        }
    }


}
