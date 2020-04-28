import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        //Please enter Excel file location
        String fileLocation = "C:/Users/.....";


        try {
            ReadExcelAndDownloadDoc readExcelAndDownloadDoc = new ReadExcelAndDownloadDoc(fileLocation);

            readExcelAndDownloadDoc.readAndDownload(fileLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
