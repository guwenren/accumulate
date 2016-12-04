package com.guwr.accumulate.facade.authority.enums;

/**
 * Created by gwr
 * Description
 * Path accumulate.facade.notify.enums.UserEnum
 * Date 2016/8/21
 * Time 14:37
 */
public class UserEnum {

    public enum UserEnumStatus {
        INVALID("无效", 0),
        VALID("有效", 1);
        private String desc;
        private int value;

        UserEnumStatus(String desc, int value) {
            this.desc = desc;
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
