package com.driveImageUpload.model;

import com.google.api.services.drive.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class DriveFormModel {
    List<File> files;
    MultipartFile file;
    String message;

    public DriveFormModel() {
    }

    public DriveFormModel(List<File> files) {
        setFiles(files);
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
