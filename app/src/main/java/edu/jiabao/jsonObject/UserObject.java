package edu.jiabao.jsonObject;

public class UserObject {

    /**
     * user : {"userId":1,"nickname":"1","phoneNum":"13427521110","passwd":"1"}
     */

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public Long getUserId() {
        return user.userId;
    }

    public void setUserId(Long userId) {
        user.userId = userId;
    }

    public String getNickname() {
        return user.nickname;
    }

    public void setNickname(String nickname) {
        user.nickname = nickname;
    }

    public String getPhoneNum() {
        return user.phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        user.phoneNum = phoneNum;
    }

    public String getPasswd() {
        return user.passwd;
    }

    public void setPasswd(String passwd) {
        user.passwd = passwd;
    }


    public static class UserBean {
        /**
         * userId : 1
         * nickname : 1
         * phoneNum : 13427521110
         * passwd : 1
         */

        private Long userId;
        private String nickname;
        private String phoneNum;
        private String passwd;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }
    }
}
