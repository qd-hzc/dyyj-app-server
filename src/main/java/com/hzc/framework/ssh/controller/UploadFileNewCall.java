package com.hzc.framework.ssh.controller;

import com.oreilly.servlet.MultipartRequest;

import java.io.File;

/**
 * Created by YinBin on 14-6-13.
 */


public abstract class UploadFileNewCall {

    public abstract <T> void file(T obj, File f, String filename, String originalFilename, long fileSize, String type) throws Exception;

    public <T> void form(T obj, MultipartRequest multipartRequest) throws Exception {
    }

}
