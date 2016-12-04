package com.guwr.accumulate.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.guwr.accumulate.common.web.vo.BaseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gwr
 * DESCRIPTION
 * PACKAGE_NAME com.eloancn.trust.common.util
 * DATE 2016/6/17
 * TIME 17:02
 */
public class CommonUtils {

    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    public static void printObj2Json(Object obj, Logger log) {
//        String simpleName = log.getClass().getSuperclass().getSimpleName();
        if (obj == null) {
            log.info("{} is null", "obj");
            return;
        }
        if (obj instanceof String) {
            log.info("{}", obj);
            return;
        }
        log.info("{}", obj2Json(obj));
    }

    public static void printObj2Json(Object obj) {
        printObj2Json(obj, logger);
    }

    /**
     * 将对象转换为JSON
     *
     * @param obj
     * @return
     */
    public static String obj2Json(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 忽略属性
     *
     * @param obj
     * @return
     */
    public static String simpleObj2Json(Object obj) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(); // 构造方法里，也可以直接传需要序列化的属性名字
        if (obj instanceof BaseVO) {
            filter.getExcludes().add("pageNum");
            filter.getExcludes().add("numPerPage");
            filter.getExcludes().add("sortType");
            filter.getExcludes().add("uuid");
            return JSON.toJSONString(obj, filter, SerializerFeature.WriteMapNullValue);
        }
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }

    public static void main(String[] args) {
        BaseVO info = new BaseVO();
        info.setSortType(BaseVO.SORT_TYPE_ASC);
        info.setUuid(StringUtils.getUUID());
        info.setNumPerPage(10);
        info.setPageNum(1);
        System.out.println(simpleObj2Json(info));
        System.out.println(obj2Json(info));
    }
}
