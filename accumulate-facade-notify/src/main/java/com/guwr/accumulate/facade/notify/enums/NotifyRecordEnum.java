package com.guwr.accumulate.facade.notify.enums;

/**
 * Created by gwr
 * Description
 * Path accumulate.facade.notify.enums.NotifyRecordEnum
 * Date 2016/8/21
 * Time 14:37
 */
public class NotifyRecordEnum {
    public enum NotifyRecordStatus {
        WAITING_CONFIRM("待确认", 0),
        SENDING("发送中", 1);
        private String desc;
        private int value;

        NotifyRecordStatus(String desc, int value) {
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

    public enum NotifyRecordEnumStatus {
        SUCCESS("通知成功", 100),
        FAILED("通知失败", 101),
        CREATED("通知记录已创建", 102),
        HTTP_REQUEST_SUCCESS("http请求响应成功", 200),
        HTTP_REQUEST_FALIED("http请求失败", 201);
        private String desc;
        private int value;

        NotifyRecordEnumStatus(String desc, int value) {
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

    public enum NotifyRecordEnumType {
        MERCHANT("商户通知", 1);
        private String desc;
        private int value;

        NotifyRecordEnumType(String desc, int value) {
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
