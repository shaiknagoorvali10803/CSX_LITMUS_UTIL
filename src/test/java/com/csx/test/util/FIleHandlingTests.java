package com.csx.test.util;

import org.junit.jupiter.api.Test;

public class FIleHandlingTests {
    @Test
    public void fileHandlingTest() {
        boolean file;
        String fileName=null;
        file = FileHandlingUtil.isFileDownloaded("nagoor");
        System.out.println(file);
        file = FileHandlingUtil.isFileDownloaded("ntt");
        System.out.println(file);
        fileName = FileHandlingUtil.getTheNewestFile("D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\FileHandling","pdf");
        System.out.println("newest file name is ==> "+ fileName);

        fileName = FileHandlingUtil.getFilename("nt");
        System.out.println("File name is ==> "+ fileName);

        fileName = FileHandlingUtil.checkWithPartialFileNameInFolder("nt","pdf");
        System.out.println("partial FIle name is ==> "+ fileName);

        FileHandlingUtil.createNewFile("D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\FileHandling","delete","txt");
        FileHandlingUtil.createNewFile("D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\FileHandling","delete1","pdf");
        FileHandlingUtil.deleteExistingFile("delete");

        FileHandlingUtil.createNewFile("D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\FileHandling","nagoor","txt");
        FileHandlingUtil.createNewFile("D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\FileHandling","nagoor1","txt");
        FileHandlingUtil.deleteExistingFile("D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\FileHandling","nagoor","txt");
        FileHandlingUtil.deleteExistingFile("D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\FileHandling","nagoor1","txt");


    }
}
