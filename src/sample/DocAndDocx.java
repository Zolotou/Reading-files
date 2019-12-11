package sample;

import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.ooxml.extractor.ExtractorFactory;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.eventusermodel.XSSFBReader;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DocAndDocx {

    String content = new String();

    // if file in not txt
    public void read(String filepath, ArrayList<String> list) throws IOException, OpenXML4JException, XmlException {
        File path = new File(filepath);
        InputStream streampath = new FileInputStream(path);
        POITextExtractor extractor;


        // if docx
        if (filepath.toLowerCase().endsWith(".docx")) {
            XWPFDocument doc = new XWPFDocument(streampath);
            extractor = new XWPFWordExtractor(doc);
        }
        // if xlsx
        else if (filepath.toLowerCase().endsWith(".xlsx")) {
            XSSFWorkbook myWorkBook = new XSSFWorkbook(streampath);

            XSSFExcelExtractor XLextractor = new XSSFExcelExtractor(myWorkBook);

            extractor = XLextractor;
            XLextractor.close();
        } else {
            POIFSFileSystem fileSystem = new POIFSFileSystem(path);
            extractor = ExtractorFactory.createExtractor(fileSystem);
        }

        content = extractor.getText();

        streampath.close();


        Scanner scanner = new Scanner(content);

        while (scanner.hasNext()) {

            list.add(scanner.next());

        }
        System.out.println(list.size() + "read");
    }

}

