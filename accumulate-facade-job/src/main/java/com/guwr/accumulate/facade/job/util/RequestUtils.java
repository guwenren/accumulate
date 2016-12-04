package com.guwr.accumulate.facade.job.util;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.facade.job.router.model.RequestModel;
import com.guwr.accumulate.facade.job.router.model.ResponseModel;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.job.util.RequestUtils
 * Date 2016/9/27
 * Time 13:23
 */
public class RequestUtils {

    private static Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    /**
     * http post request
     *
     * @param reqURL
     */
    public static ResponseModel postHex(String reqURL, RequestModel requestModel) {
        logger.info("RequestUtils.postHex");
        logger.info("reqURL = [" + reqURL + "], requestModel = [" + requestModel + "]");
        // parse RequestModel to hex-json
        String requestHex = CommonUtils.obj2Json(requestModel);
        // msg
        String failMsg = null;
        // do post
        HttpPost httpPost = null;
        CloseableHttpClient httpClient = null;
        try {
            httpPost = new HttpPost(reqURL);
            List<NameValuePair> formParams = new ArrayList<>();
            formParams.add(new BasicNameValuePair("hex", requestHex));
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));


            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
            httpPost.setConfig(requestConfig);

            //httpClient = HttpClients.createDefault();	// default retry 3 times
            httpClient = HttpClients.custom().disableAutomaticRetries().build();

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200 && null != entity) {
                String responseHex = EntityUtils.toString(entity, "UTF-8");
                logger.info("job, net comm success, requestHex:{}, responseHex:{}", requestHex, responseHex);
                EntityUtils.consume(entity);

                // i do not know why
                //responseHex = responseHex.replace("\n", "");
                //responseHex = responseHex.replace("\r", "");

                if (responseHex != null) {
                    responseHex = responseHex.trim();
                }

                // parse hex-json to ResponseModel
                ResponseModel responseModel = JSON.parseObject(responseHex, ResponseModel.class);

                if (responseModel != null) {
                    return responseModel;
                }
            } else {
                failMsg = "http statusCode error, statusCode:" + response.getStatusLine().getStatusCode();
            }
        } catch (Exception e) {
            logger.info("", e);
            /*StringWriter out = new StringWriter();
            e.printStackTrace(new PrintWriter(out));
			callback.setMsg(out.toString());*/
            failMsg = e.getMessage();
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.info("", e);
                }
            }
        }

        // other, default fail
        logger.info("postHex fail");
        ResponseModel callback = new ResponseModel();
        callback.setStatus(ResponseModel.FAIL);
        callback.setMsg(failMsg);
        return callback;
    }
}
