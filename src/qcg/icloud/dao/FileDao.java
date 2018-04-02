package qcg.icloud.dao;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import qcg.icloud.pojo.File;
import qcg.icloud.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/3/29 10:35
 */
public class FileDao {

    /**
     * 增加文件
     * @param file
     * @return 返回是否插入成功
     */
    public boolean addFile(File file){
        Connection connection = JDBCUtil.getConn();
        String sql = "insert into file(fileName,fileMD5,filePath,fileSize) values(?,?,?,?)";
        QueryRunner qr = new QueryRunner();
        if (connection != null){
            try{
                int result = qr.update(connection,sql,file.getFileName(),file.getFileMD5(),file.getFilePath(),file.getFileSize());
                if (result == 1){
                    return true;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                DbUtils.closeQuietly(connection);
            }
        }
        return false;
    }

    /**
     * 新增文件
     * @param file
     * @return 增加文件的id值，0表示新增失败
     */
    public int addFileRetId(File file){
        Connection connection = JDBCUtil.getConn();
        String sql = "insert into file(fileName,fileMD5,filePath,fileSize) values(?,?,?,?)";
        String sqlId = "select last_insert_id()";
        QueryRunner qr = new QueryRunner();
        if (connection != null){
            try{
                int result = qr.update(connection,sql,file.getFileName(),file.getFileMD5(),file.getFilePath(),file.getFileSize());
                if (result == 1){
                    return Integer.parseInt(qr.query(connection,sqlId,new ArrayHandler())[0].toString());
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                DbUtils.closeQuietly(connection);
            }
        }
        return 0;
    }

    /**
     * 用MD5和文件名称校验唯一性
     * 根据MD5值和文件名称删除文件
     * @param fileMD5
     * @param fileName
     * @return
     */
    public boolean delFile(String fileMD5,String fileName){
        Connection connection = JDBCUtil.getConn();
        String sql = "delete from file where fileMD5 = ? and fileName = ?";
        QueryRunner qr = new QueryRunner();
        if (connection != null){
            try{
                int result = qr.update(connection,sql,fileMD5,fileName);
                if (result == 1){
                    return true;
                }else if (result > 1){
                    System.out.println("删除文件大于一个，请检查程序是否有问题!!!");
                    return false;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                DbUtils.closeQuietly(connection);
            }
        }
        return false;
    }

    /**
     * 暂无更新文件的需求
     * @param file
     * @return
     */
    public boolean updateFile(File file){
        Connection connection = JDBCUtil.getConn();
        String sql = "update file set";
        return false;
    }

    /**
     * 查出文件数量
     * @return
     */
    public Integer fileCount(){
        int result = 0;
        Connection connection = JDBCUtil.getConn();
        String sql = "select count(*) from file";
        QueryRunner qr = new QueryRunner();
        if (connection != null){
            try {
                result = qr.execute(sql);
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                DbUtils.closeQuietly(connection);
            }
        }
        return result;
    }

    /**
     * file表示否已有此文件，根据filename和fileMD5校验文件唯一性
     * @param fileName
     * @param fileMD5
     * @return 返回存在文件的id值
     */
    public int isHave(String fileName,String fileMD5){
        int fileId = 0;
        Connection connection = JDBCUtil.getConn();
        String sql = "select id from file where fileName = ? and fileMD5 = ?";
        QueryRunner qr = new QueryRunner();
        if (connection != null) {
            try{
                List<Object[]> list =  qr.query(connection,sql,new ArrayListHandler(),fileName,fileMD5);
                if (list.size() > 0){
                    fileId = Integer.parseInt(list.get(0)[0].toString());
                    return fileId;
                }else if (list.size() > 1){
                    System.out.println(" 代码有问题，相同filename和filemd5出现了两个！！！ ");
                    return fileId;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                DbUtils.closeQuietly(connection);
            }
        }
        return fileId;
    }

}
