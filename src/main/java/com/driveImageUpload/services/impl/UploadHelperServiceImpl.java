package com.driveImageUpload.services.impl;

import com.driveImageUpload.drive.StartDrive;
import com.driveImageUpload.services.UploadHelperService;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;


@Service
public class UploadHelperServiceImpl implements UploadHelperService {

    @Override
    public boolean uploadFilToDrive(MultipartFile file) {
        final String parentFolder = null;

        try {
            File tempFile = this._createFileOnDrive(parentFolder, file.getContentType(), file.getOriginalFilename(), file.getBytes());
            System.out.println("getWebContentLink : " + tempFile.getWebContentLink());
            System.out.println("WebViewLink: " + tempFile.getWebViewLink());
            return true;
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<File> getFilesFromDrive() throws GeneralSecurityException, IOException {
        Drive service = StartDrive.getDriveService();
        FileList result = service.files().list().execute();
        return result.getFiles();
    }

    private File _createFileOnDrive(String parentFolderPath, String contentType, String fileName, byte[] bytes) throws GeneralSecurityException, IOException {
        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        List<String> parents = Arrays.asList(parentFolderPath);
        fileMetadata.setParents(parents);
        Drive service = StartDrive.getDriveService();
        AbstractInputStreamContent uploadStreamContent = new ByteArrayContent(contentType, bytes);
        return service.files().create(fileMetadata, uploadStreamContent).setFields("id, webContentLink, webViewLink, parents").execute();
    }

}
