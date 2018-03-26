package qcg.icloud.pojo;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 最大可使用容量（暂未使用）
     */
    private long MaxUse;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public long getMaxUse() {
        return MaxUse;
    }

    public void setMaxUse(long maxUse) {
        MaxUse = maxUse;
    }
}
