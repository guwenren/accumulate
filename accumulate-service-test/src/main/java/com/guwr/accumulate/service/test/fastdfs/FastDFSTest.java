package com.guwr.accumulate.service.test.fastdfs;

import org.apache.commons.io.FileUtils;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

/**
 * Created by guwr
 * Project_name accumulate
 * Path com.guwr.accumulate.service.test.fastdfs.FastDFSTest
 * Date 2017/3/29
 * Time 18:02
 * Description
 */
public class FastDFSTest {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

    /**
     * 上传测试.
     *
     * @throws Exception
     */
    public static void upload() throws Exception {
        String filePath = "F:/logo_new.jpg";
        File file = new File(filePath);
        String fileId = FastDFSClient.uploadFile(file, filePath);
        System.out.println("Upload local file " + filePath + " ok, fileid=" + fileId);
        // fileId:	group1/M00/00/00/wKgEfVUYPieAd6a0AAP3btxj__E335.jpg
        // url:	http://192.168.4.125:8888/group1/M00/00/00/wKgEfVUYPieAd6a0AAP3btxj__E335.jpg
    }

    /**
     * 下载测试.
     *
     * @throws Exception
     */
    public static void download() throws Exception {
        String fileId = "group1/M00/00/00/wKgDjljbh_GAFFSNAAA7ifSmPVk823.jpg";
        InputStream inputStream = FastDFSClient.downloadFile(fileId);
        File destFile = new File("F:/fastfds/DownloadTest.jpg");
        FileUtils.copyInputStreamToFile(inputStream, destFile);
    }

    /**
     * 删除测试
     *
     * @throws Exception
     */
    public static void delete() throws Exception {
        String fileId = "group1/M00/00/00/wKgEfVUYPieAd6a0AAP3btxj__E335.jpg";
        int result = FastDFSClient.deleteFile(fileId);
        System.out.println(result == 0 ? "删除成功" : "删除失败:" + result);
    }


    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        upload();
        download();
//        delete();
    }
}
