import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadExcelAndDownloadDoc {
    ReadExcel readExcel;
    FindLink findLink;
    DownloadDocument downloadDocument;

    public ReadExcelAndDownloadDoc(String filePath) throws IOException {
        this.readExcel = new ReadExcel(filePath);
        this.findLink = new FindLink();
        this.downloadDocument = new DownloadDocument();
    }

    public void readAndDownload(String excelPath) throws IOException {
        this.readExcel = new ReadExcel(excelPath);
        int i;
        //Enter from which line until which line you want to download the files
        for (i=1; i<2; i++) {
            String title = ReadExcel.data.get(i).get(0); //0 is the column in which is entered the Title of the book
            String oldURL = ReadExcel.data.get(i).get(3); //3 is the column in which is entered the link to the web page of the book
            System.out.println("Launching download of file "+title+"...");
            List<String> urLsInText = new ArrayList();
            urLsInText = findLink.findURLsInText(oldURL);
            String goodURL = findLink.isolateGoodLink(urLsInText.get(0)); //The first URL of the pages in the site we are using is
            // the URL that we use to go to our PDF book
            System.out.println("Downloading file n°"+i + "/400");
            downloadDocument.readAndDownload(title, goodURL);
            System.out.println("File "+title+" downloaded with success");
        }

    }

}
