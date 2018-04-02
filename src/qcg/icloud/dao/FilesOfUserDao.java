package qcg.icloud.dao;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import qcg.icloud.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/3/29 11:06
 */
public class FilesOfUserDao {

    /**
     * 增加用户文件
     * @param userName
     * @param fileId
     * @return
     */
    public boolean addUserFile(String userName, int fileId){
        Connection connection = JDBCUtil.getConn();
        String sql = "insert into file_user(fileId,userName) values(?,?)";
        QueryRunner qr = new QueryRunner();
        if (connection != null) {
            try{
                int result = qr.update(connection,sql,fileId,userName);
                if (result == 1){
                    return true;
                }else if (result > 1){
                    System.out.println(" file_user出错，请检查程序！！！ ");
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
     * 删除用户文件
     * @param userName 用户名
     * @param fileId 文件id
     * @return
     */
    public boolean delUserFile(String userName,int fileId){
        Connection connection = JDBCUtil.getConn();
        String sql = "delete from file_user where fileId = ? and userName = ?";
        QueryRunner qr = new QueryRunner();
        if (connection != null) {
            try{
                int result = qr.update(connection,sql,userName,fileId);
                if (result == 1){
                    return true;
                }else if (result > 1){
                    System.out.println("代码有问题，请检查！！！");
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
     * 批量删除用户文件
     * @param userName 用户名
     * @param fileIds 文件id数组
     * @return
     */
    public void delUserFiles(String userName,int[] fileIds){
        for (int fileId : fileIds) {
            boolean result = delUserFile(userName, fileId);
            if (!result){
                System.out.println("删除文件id为" + fileId + "出错！！！！" );
            }
        }
    }

    /**
     * 查出文件数量
     * @param userName
     * @return
     */
    public int fileCount(String userName){
        int result = 0;
        Connection connection = JDBCUtil.getConn();
        String sql = "select count(*) from file_user where userName = ?";
        QueryRunner qr = new QueryRunner();
        if (connection != null) {
            try {
                Object[] objects = qr.query(connection,sql,new ArrayHandler(),userName);
                result = Integer.parseInt(objects[0].toString());
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                DbUtils.closeQuietly(connection);
            }
        }
        return result;
    }

    /**
     * 查出用户拥有的文件
     * @param userName
     * @return 一个objece[文件id，文件名，文件大小]类型的list
     */
    public List<Object[]> queryFiles(String userName){
        List<Object[]> list = new ArrayList<Object[]>();
        Connection connection = JDBCUtil.getConn();
        String sql = "select f.id,f.fileName,f.fileSize from file f,file_user fu where fu.fileId = f.id and fu.userName = ?";
        QueryRunner qr = new QueryRunner();
        if (connection != null) {
            try{
                list = qr.query(connection,sql,new ArrayListHandler(),userName);
                if (list.size() > 0){
                    return list;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                DbUtils.closeQuietly(connection);
            }
        }
        return list;
    }

    /**
     * 查出file_user表中是否已有记录
     * @param userName 用户名
     * @param fileId 文件id
     * @return
     */
    public boolean isHave(String userName,int fileId){
        Connection connection = JDBCUtil.getConn();
        String sql = "select * from file_user where fileId = ? and userName = ?";
        QueryRunner qr = new QueryRunner();
        if (connection != null) {
            try {
                List<Object[]> list = qr.query(connection,sql,new ArrayListHandler(),fileId,userName);
                if (list.size() >0){
                    return true;
                }else if (list.size() > 1){
                    System.out.println(" file_user重复插入了，请检查代码！！！");
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
}
