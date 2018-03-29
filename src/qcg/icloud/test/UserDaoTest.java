package qcg.icloud.test;

import qcg.icloud.dao.UserDao;
import qcg.icloud.pojo.User;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/3/28 17:03
 */
public class UserDaoTest {
    public static void main(String[] args) {
        UserDao ud = new UserDao();
        User user = new User();
        user.setUserName("ceshi");
        user.setPassWord("123");
        ud.addUser(user);
    }
}
