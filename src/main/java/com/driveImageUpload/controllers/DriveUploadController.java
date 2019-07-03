package com.driveImageUpload.controllers;

import com.driveImageUpload.model.DriveFormModel;
import com.driveImageUpload.services.UploadHelperService;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DriveUploadController {
    private final UploadHelperService uploadHelperService;

    @Autowired
    public DriveUploadController(UploadHelperService uploadHelperService) {
        this.uploadHelperService = uploadHelperService;
    }

    @RequestMapping(value = "/drive-upload", method = RequestMethod.GET)
    public ModelAndView getView() {
        List<File> files = new ArrayList<>();
        try {
            files = uploadHelperService.getFilesFromDrive();
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        DriveFormModel driveFormModel = new DriveFormModel(files);
        return new ModelAndView("fileUploadView.jsp", "driveFormModel", driveFormModel);
    }

    @RequestMapping(value = "/post-file", method = RequestMethod.POST)
    public ModelAndView postFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            uploadHelperService.uploadFilToDrive(file);
            return new ModelAndView("redirect:/");
        } else {
            return new ModelAndView("uploaded-file.jsp", "message", "File is empty, nothing to upload");
        }
    }

    @RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    public ModelAndView fileUploadView(@RequestParam("file") MultipartFile file) {
        if (file != null) {
            uploadHelperService.uploadFilToDrive(file);
        }
        return new ModelAndView("redirect:/drive-upload");
    }

}
