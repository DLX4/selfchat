package pers.dlx.selfchat.config;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MybatisGenerator {

    public static void main(String[] args) {
        try {
            generateMyMapper();
            // generateCtiMapper();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 生成selfchat数据库mapper
     *
     * @throws SQLException
     * @throws IOException
     * @throws InterruptedException
     * @throws InvalidConfigurationException
     */
    public static void generateMyMapper()
            throws SQLException, IOException, InterruptedException, InvalidConfigurationException {
        /* 配置xml配置项 */
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        Configuration config = new Configuration();
        Context context = new Context(ModelType.CONDITIONAL);
        context.setTargetRuntime("MyBatis3");
        context.setId("mysql-selfchat");

        // 自动识别数据库关键字
        context.addProperty("autoDelimitKeywords", "true");
        context.addProperty("beginningDelimiter", "`");
        context.addProperty("endingDelimiter", "`");
        // 生成的Java文件的编码
        context.addProperty("javaFileEncoding", "utf-8");

        // 格式化java代码
        context.addProperty("javaFormatter", "org.mybatis.generator.api.dom.DefaultJavaFormatter");
        // 格式化xml代码
        context.addProperty("xmlFormatter", "org.mybatis.generator.api.dom.DefaultXmlFormatter");

        // 格式化信息
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.ToStringPlugin");
        pluginConfiguration.setConfigurationType("pers.dlx.selfchat.config.MybatisOverwriteXmlPlugin");
        context.addPluginConfiguration(pluginConfiguration);

        // 设置是否生成注释
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        commentGeneratorConfiguration.setConfigurationType("pers.dlx.selfchat.config.MybatisCommentGenerator");
        commentGeneratorConfiguration.addProperty("suppressAllComments", "false");
        commentGeneratorConfiguration.addProperty("suppressDate", "true");
        commentGeneratorConfiguration.addProperty("addRemarkComments", "true");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

        // 设置连接数据库
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass("com.mysql.cj.jdbc.Driver");
        jdbcConnectionConfiguration.setConnectionURL(
                "jdbc:mysql://0.0.0.0:3306/selfchat?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false&useInformationSchema=true");
        jdbcConnectionConfiguration.setPassword("zjipst_dev");
        jdbcConnectionConfiguration.setUserId("root");
        jdbcConnectionConfiguration.addProperty("useInformationSchema", "true");
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        // 实体类
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetPackage("pers.dlx.selfchat.entity");
        javaModelGeneratorConfiguration.setTargetProject("src/main/java");
        javaModelGeneratorConfiguration.addProperty("trimStrings", "true");
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        // mapxml
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject("src/main/resources");
        sqlMapGeneratorConfiguration.setTargetPackage("mapper");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        // mapxml对应client，也就是接口dao
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetPackage("pers.dlx.selfchat.dao");
        javaClientGeneratorConfiguration.setTargetProject("src/main/java");
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        // table
        TableConfiguration tableConfiguration;

        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("system_status");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("jjd_snapshot");
        // context.addTableConfiguration(tableConfiguration);
        //
        // // 20190618新增表
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("cjd");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("fkd_snapshot");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("cjd_timeout");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("fk_timeout_setting");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("cjd_snapshot");
        // context.addTableConfiguration(tableConfiguration);
        //
        // // 201090624 关键字
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("jj_keywords");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("fk_keywords");
        // context.addTableConfiguration(tableConfiguration);
        //
        // // 20190626接警类型反馈类型关联对照
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("bjlx_relate_fklx");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("jj_speech_keywords");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("bjlb_relate_fklb");
        // context.addTableConfiguration(tableConfiguration);
        //
        // // 20190703 处警超时预警
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("cjd_warning");
        // context.addTableConfiguration(tableConfiguration);
        //
        // // 20190711 重命名
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("jj_content_setting");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("jj_defect");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("fk_defect");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("fk_content_setting");
        // context.addTableConfiguration(tableConfiguration);
        //
        // // 20190729 新增需求
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("cjd2_snapshot");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("fkd_czqk_snapshot");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("fkd_xc_snapshot");
        // context.addTableConfiguration(tableConfiguration);
        //
        // // 20190816新增处警相关配置
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("cj_setting");
        // context.addTableConfiguration(tableConfiguration);
        //
        // // 20190827 新增定向推送相关配置
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("push_jjd");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("push_setting");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("push_setting_jy");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("push_setting_jz");
        // context.addTableConfiguration(tableConfiguration);
        //
        // // 20190830 记录审核日志
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("jj_defect_log");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("view_jj_defect_review");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("fk_defect_log");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("view_fk_defect_review");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("push_dx_log");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("audit_log");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("push_group_relate_setting");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("push_group");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("push_jjd_relate_setting");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("view_push_jjd");
        // context.addTableConfiguration(tableConfiguration);

        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("jj_defect");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("fk_defect");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("view_jj_defect_review");
        // context.addTableConfiguration(tableConfiguration);
        //
        // tableConfiguration = new TableConfiguration(context);
        // tableConfiguration.setTableName("view_fk_defect_review");
        // context.addTableConfiguration(tableConfiguration);
        tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName("cjd");
        context.addTableConfiguration(tableConfiguration);

        tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName("cjd_warning");
        context.addTableConfiguration(tableConfiguration);

        tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName("cjd_timeout");
        context.addTableConfiguration(tableConfiguration);

        tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName("fk_defect");
        context.addTableConfiguration(tableConfiguration);

        tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName("jj_defect");
        context.addTableConfiguration(tableConfiguration);

        tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName("push_jjd");
        context.addTableConfiguration(tableConfiguration);

        tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName("jjd_version");
        context.addTableConfiguration(tableConfiguration);

        config.addContext(context);

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    public static void generateCtiMapper()
            throws SQLException, IOException, InterruptedException, InvalidConfigurationException {
        /* 配置xml配置项 */
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        Configuration config = new Configuration();
        Context context = new Context(ModelType.CONDITIONAL);
        context.setTargetRuntime("MyBatis3");
        context.setId("mysql-cti");

        // 自动识别数据库关键字
        context.addProperty("autoDelimitKeywords", "true");
        context.addProperty("beginningDelimiter", "`");
        context.addProperty("endingDelimiter", "`");
        // 生成的Java文件的编码
        context.addProperty("javaFileEncoding", "utf-8");

        // 格式化java代码
        context.addProperty("javaFormatter", "org.mybatis.generator.api.dom.DefaultJavaFormatter");
        // 格式化xml代码
        context.addProperty("xmlFormatter", "org.mybatis.generator.api.dom.DefaultXmlFormatter");

        // 格式化信息
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.ToStringPlugin");
        pluginConfiguration.setConfigurationType("pers.dlx.selfchat.config.MybatisOverwriteXmlPlugin");
        context.addPluginConfiguration(pluginConfiguration);

        // 设置是否生成注释
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        commentGeneratorConfiguration.setConfigurationType("pers.dlx.selfchat.config.MybatisCommentGenerator");
        commentGeneratorConfiguration.addProperty("suppressAllComments", "false");
        commentGeneratorConfiguration.addProperty("suppressDate", "true");
        commentGeneratorConfiguration.addProperty("addRemarkComments", "true");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

        // 设置连接数据库
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass("com.mysql.cj.jdbc.Driver");
        jdbcConnectionConfiguration.setConnectionURL(
                "jdbc:mysql://10.10.14.49:3306/zjipst?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false");
        jdbcConnectionConfiguration.setPassword("111111");
        jdbcConnectionConfiguration.setUserId("root");
        jdbcConnectionConfiguration.addProperty("useInformationSchema", "true");
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        // 实体类
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetPackage("pers.dlx.selfchat.entity");
        javaModelGeneratorConfiguration.setTargetProject("src/main/java");
        javaModelGeneratorConfiguration.addProperty("trimStrings", "true");
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        // // mapxml
        // SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new
        // SqlMapGeneratorConfiguration();
        // sqlMapGeneratorConfiguration.setTargetProject("src/main/resources");
        // sqlMapGeneratorConfiguration.setTargetPackage("mapper");
        // context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
        //
        // // mapxml对应client，也就是接口dao
        // JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new
        // JavaClientGeneratorConfiguration();
        // javaClientGeneratorConfiguration.setTargetPackage("pers.dlx.selfchat.dao");
        // javaClientGeneratorConfiguration.setTargetProject("src/main/java");
        // javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        // context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        // table
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName("fact_agent_detail");
        context.addTableConfiguration(tableConfiguration);

        config.addContext(context);

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    /**
     * timestamp 类型字段使用java.sql.Timestamp解析，不丢失毫秒微秒精度
     *
     * @param columnName
     * @return
     */
    public static ColumnOverride getTimestampColumnOverride(String columnName) {
        ColumnOverride columnOverride = new ColumnOverride(columnName);
        columnOverride.setJdbcType("TIMESTAMP");
        columnOverride.setJavaType("java.sql.Timestamp");
        return columnOverride;
    }

}
