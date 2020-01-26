package warmer.star.blog;

import org.junit.Test;
import warmer.star.blog.model.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class TestJavaBeanToMysql {
    @Test
    public void test() throws IOException {
        StringBuilder sql = new StringBuilder();
        sql.append(TestJavaBeanToMysql.generateSqlMapper(Article.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(ArticleDetail.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(ArticleFile.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(ArticleTag.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(Banner.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(Category.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(Comment.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(ELog.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(ELogFile.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(Logger.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(LogInfo.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(Menu.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(Music.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(Partner.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(Role.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(RolePermission.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(Tag.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(User.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(UserInfo.class,""));
        sql.append(TestJavaBeanToMysql.generateSqlMapper(UserRole.class,""));
        //sql.append(TestJavaBeanToMysql.generateSqlMapper(Menu.class,""));
        String path = "e:\\starblog.sql";
        File file = new File(path);
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        // write
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(sql.toString());
        bw.flush();
        bw.close();
        fw.close();
    }
    public static String generateSqlMapper(Class obj,String tableName) throws IOException {
        Field[] fields = obj.getDeclaredFields();
        String param = null;
        StringBuilder sql = new StringBuilder();
        if (tableName == null || tableName.equals("")) {
            // 未传表明默认用类名
            tableName = obj.getName().substring(obj.getName().lastIndexOf(".") + 1);
        }
        /**
         * 以下部分生成建表Sql
         */
        sql.append(String.format("#===============%s===============",tableName)).append("\n");
        sql.append("drop table if exists ").append(tableName).append(";\r\n");
        sql.append("create table ").append(tableName).append("( \r\n");
        boolean firstId = true;
        for (Field f : fields) {
            sql.append(f.getName()).append(" ");
            param = f.getType().getTypeName();
            if (param.equals("java.lang.Integer")||param.equals("int")) {
                sql.append("int(11)");
            }else if(param.equals("java.lang.Long")||param.equals("long")){
                sql.append("bigint(20)");
            }
            else if(param.equals("java.util.Date")){
                sql.append("datetime(0) NOT NULL ");
            }
            else if(param.equals("java.lang.Float")){
                sql.append("bit(1) NOT NULL DEFAULT 0 ");
            }
            else {
                // 根据需要自行修改
                sql.append("VARCHAR(255)");
            }
            if (firstId) {
                // 默认第一个字段为ID主键
                sql.append(" PRIMARY KEY AUTO_INCREMENT");
                firstId = false;
            }
            sql.append(",\n");
        }
        sql.delete(sql.lastIndexOf(","), sql.length()).append("\n)ENGINE=INNODB DEFAULT CHARSET=UTF8 AUTO_INCREMENT=1;\r\n");
        System.out.println(sql);
        return  sql.toString();
    }
}
