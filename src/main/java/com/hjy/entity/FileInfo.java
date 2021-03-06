package com.hjy.entity;

public class FileInfo {
    private String fileHash;

    private String fileName;

    private String fileUrl;

    public FileInfo(String fileHash, String fileName, String fileUrl) {
        this.fileHash = fileHash;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }

    public FileInfo() {
        super();
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash == null ? null : fileHash.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }
}