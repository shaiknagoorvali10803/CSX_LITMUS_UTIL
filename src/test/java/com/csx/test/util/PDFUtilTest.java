package com.csx.test.util;

import org.junit.jupiter.api.Test;

public class PDFUtilTest {
    @Test
    public void PDFTest() {
        String pdfData=null;
        pdfData = PDFfileHandlingUtil.getPDFData("D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\pdfData.pdf",1,2);
        System.out.println(pdfData);
        System.out.println("===========================================================");
        pdfData = PDFfileHandlingUtil.getPDFData("D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\pdfData.pdf",1);
        System.out.println(pdfData);
        System.out.println("===========================================================");
        pdfData = PDFfileHandlingUtil.getPDFData("D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\pdfData.pdf");
        System.out.println(pdfData);


    }
}
