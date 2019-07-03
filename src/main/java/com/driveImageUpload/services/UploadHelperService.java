package com.driveImageUpload.services;

import com.google.api.services.drive.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


public interface UploadHelperService {
    boolean uploadFilToDrive(MultipartFile file);

    List<File> getFilesFromDrive() throws GeneralSecurityException, IOException;
}
