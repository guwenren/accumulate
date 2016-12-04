package com.guwr.accumulate.facade.account.enums;

/**
 * Created by gwr
 * Description
 * Path accumulate.facade.notify.enums.AccountBalanceRecordEnum
 * Date 2016/8/21
 * Time 14:37
 */
public class AccountBalanceRecordEnum {

    public enum AccountBalanceRecordEnumType {
        OUTGO("支出", 1),
        INCOME("收入", 2);
        private String desc;
        private int value;

        AccountBalanceRecordEnumType(String desc, int value) {
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
