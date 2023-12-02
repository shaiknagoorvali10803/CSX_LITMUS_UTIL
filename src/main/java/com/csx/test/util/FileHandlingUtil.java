package com.csx.test.util;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * --------------------------------Author: Perraj Kumar K (S9402)---------------------
 */

public class FileHandlingUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileHandlingUtil.class);
    public static final String downloadPath = "D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\FileHandling";

    //File download confirmation
    public static boolean isFileDownloaded(String partialFileName) {
        File dir = new File(downloadPath);
        File[] files = dir.listFiles();
        boolean flag = false;
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().contains(partialFileName)) {
                flag = true;
            }
        }
        return flag;
    }

    //Get Newest File
    public static String getTheNewestFile(String filePath, String ext) {
        File dir = new File(filePath);
        FileFilter fileFilter = new WildcardFileFilter("*." + ext);
        File[] files = dir.listFiles(fileFilter);
        String name = "";

        if (files.length > 0) {
            /** The newest file comes first **/
            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            name = files[0].getName();
        }

        return name;
    }

//Delete all files with some partial filename match

    public static void deleteExistingFile(String partialFileName) {
        File dir = new File(downloadPath);
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().contains(partialFileName)) {
                LOGGER.info("deleting file name ==>  " + files[i]);
                files[i].delete();
            }
        }
    }

    // Get actual filename with partial file name match
    public static String getFilename(String partialFileName) {
        File dir = new File(downloadPath);
        File[] files = dir.listFiles();
        String reportFileName = "";
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().contains(partialFileName)) {
                reportFileName = files[i].getName();
            }
        }
        return reportFileName;
    }

    /**
     * -----Check file with given partial name and format is available ?  ---------
     */
    public static String checkWithPartialFileNameInFolder(String partialFileName, String format) {
        String folderName = downloadPath + File.separator; // Give your folderName
        File[] listFiles = new File(folderName).listFiles();
        String file = null;
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isFile()) {
                String fileName = listFiles[i].getName();
                if (fileName.contains(partialFileName) && fileName.endsWith(format)) {
                    file = fileName;
                    LOGGER.info("found file " + file);
                }
            }
        }
        return file;
    }

    /**
     * -----delete the filename with given partialName and extention ---------
     */
    public static void deleteExistingFile(String path, String partialFileName, String extension) {
        File dir = new File(path);
        FileFilter fileFilter = new WildcardFileFilter("*." + extension);
        File[] files = dir.listFiles(fileFilter);
        try {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().contains(partialFileName)) {
                    LOGGER.info("deleting file name ==>  " + files[i]);
                    files[i].delete();
                }
            }
        } catch (Exception e) {
            LOGGER.info("no file exists with given details");
        }
    }

    public static void createNewFile(String path, String partialFileName, String extension) {
        File dir = new File(path + File.separator + partialFileName + "." + extension);
        boolean isFileAlreadyExists = false;

        FileFilter fileFilter = new WildcardFileFilter("*." + extension);
        File[] files = dir.listFiles(fileFilter);
        try {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().contains(partialFileName)) {
                    isFileAlreadyExists = true;
                }
            }
        } catch (Exception e) {
            LOGGER.info("no file exists with given details hence creating File");
        }

        if (!isFileAlreadyExists) {
            try {
                boolean success = dir.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            LOGGER.info("file exists with given details");
        }

    }

}
