package com.hzc.framework.ssh.controller;

import com.oreilly.servlet.MultipartRequest;

import java.io.File;

/**
 * Created by YinBin on 14-6-13.
 * <p/>
 * 请使用 UploadFileNewCall
 */


public abstract class UploadFileCall {

    public abstract <T> void file(T obj, File f, String filename, String originalFilename, String type) throws Exception;

    public <T> void form(T obj, MultipartRequest multipartRequest) throws Exception {
    }

}
