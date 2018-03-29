package qcg.icloud.service;

import qcg.icloud.dao.UserDao;
import qcg.icloud.pojo.User;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/3/29 15:14
 */
public class UserService {

    /**
     * 增加用户，先判断是否有此用户
     * @param userName
     * @param passWord
     * @return
     */
    public boolean addUser(String userName,String passWord){
        User user = new User(userName);
        user.setPassWord(passWord);
        UserDao userDao = new UserDao();
        boolean isHave = userDao.isHave(user);
        if (!isHave){
            boolean result = userDao.addUser(user);
            if (result){
                return true;
            }
        }
        return false;
    }

    /**
     * 核对用户是否已经注册过
     * @param userName
     * @param passWord
     * @return
     */
    public boolean checkSignIn(String userName,String passWord){
        User user = new User(userName);
        user.setPassWord(passWord);
        UserDao userDao = new UserDao();
        if (userDao.checkSignIn(user)){
            return true;
        }
        return false;
    }
}
