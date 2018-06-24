package edu.jiabao.jsonObject;

public class HttpRequest {

    /**
     * data : {"user":{"userId":1,"nickname":"1","phoneNum":"13427521110","passwd":"1"}}
     * status : SUCCESS
     * msg : 成功
     * date : 1529406388437
     */

    private DataBean data;
    private String status;
    private String msg;
    private long date;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public static class DataBean {
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

        public static class UserBean {
            /**
             * userId : 1
             * nickname : 1
             * phoneNum : 13427521110
             * passwd : 1
             */

            private int userId;
            private String nickname;
            private String phoneNum;
            private String passwd;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
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
}
