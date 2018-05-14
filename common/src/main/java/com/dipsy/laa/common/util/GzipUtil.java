package com.dipsy.laa.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 压缩，解压
 *
 * @auther cuiqiongyu
 * @create 2018/5/14 22:31
 */
public class GzipUtil {

    /**
     * 压缩
     * @param bytes 待压缩字节流
     * @return
     * @throws Exception
     */
    public static byte[] encode(byte[] bytes) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gos = null;
        try {
            gos = new GZIPOutputStream(baos);
            gos.write(bytes);
            baos.close();
            gos.close();
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != baos) {
                baos.close();
            }
            if (null != gos) {
                gos.close();
            }
        }
        return baos.toByteArray();
    }


    /**
     * 解压
     * @param bytes 待解压字节流
     * @return
     * @throws Exception
     */
    public static byte[] decode(byte[] bytes) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPInputStream gis = null;
        try {
            gis = new GZIPInputStream(bais);
            int bufferSize = 1024;
            byte[] b = new byte[bufferSize];
            int len = 0;
            while ((len = gis.read(b, 0, bufferSize)) != -1) {
                baos.write(b, 0, len);
            }
            bais.close();
            gis.close();
            baos.close();
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != bais) {
                bais.close();
            }
            if (null != gis) {
                gis.close();
            }
            if (null != baos) {
                baos.close();
            }
        }
        return baos.toByteArray();
    }

}
