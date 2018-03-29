package qcg.icloud.dao;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import qcg.icloud.pojo.User;
import qcg.icloud.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/3/28 16:26
 */
public class UserDao {

    /**
     * 增加用户
     * @param user
     * @return
     */
    public boolean addUser(User user){
        Connection connection = JDBCUtil.getConn();
        String sql = "insert into user(userName,password) values(?,?)";
        QueryRunner qr = new QueryRunner();
        if (connection != null){
            try {
                int result = qr.update(connection, sql, user.getUserName(), user.getPassWord());
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
     * 删除用户
     * @param user
     * @return
     */
    public boolean delUser(User user){
        Connection connection = JDBCUtil.getConn();
        String sql = "delete from user where userName = ?";
        QueryRunner qr = new QueryRunner();
        if (connection != null) {
            try {
                int result = qr.update(connection,sql,user.getUserName());
                if (result == 1){
                    return true;
                }else if (result > 1){
                    System.out.println("请检查addUser是否有问题，删除user大于1条！！！");
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
     * 根据用户名更新密码
     * @param user
     * @return
     */
    public boolean updateUserPassword(User user){
        Connection connection = JDBCUtil.getConn();
        String sql = "update user set password = ? where userName = ?";
        QueryRunner qr = new QueryRunner();
        if (connection != null){
            try{
                int result = qr.update(connection,sql,user.getUserName());
                if (result > 0 ){
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
     * 查询是否已有此用户
     * @param user
     * @return
     */
    public boolean isHave(User user){
        Connection connection = JDBCUtil.getConn();
        String sql = "select * from user where userName = ?";
        QueryRunner qr = new QueryRunner();
        if (connection != null) {
            try {
                Object[] objects = qr.query(connection,sql,new ArrayHandler(),user.getUserName());
                if (objects.length > 0){
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
     * 用于判断用户名，密码是否正确
     * @param user
     * @return
     */
    public boolean checkSignIn(User user){
        Connection connection = JDBCUtil.getConn();
        String sql = "select * from user where userName = ? and password = ?";
        QueryRunner qr = new QueryRunner();
        if (connection != null) {
            try {
                Object[] objects = qr.query(connection, sql, new ArrayHandler(), user.getUserName());
                if (objects.length > 0) {
                    return true;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
