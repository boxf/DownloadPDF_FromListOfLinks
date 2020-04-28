import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadDocument {

    public void readAndDownload(String title, String link) throws IOException {

        System.out.println("Opening connection");
        URL url = new URL(link);
        InputStream in = url.openStream();
        FileOutputStream fos = new FileOutputStream(new File(title+".pdf"));

        System.out.println("reading from resource and downloading file...");
        int lenght = -1;
        byte[] buffer = new byte[1024];
        while ((lenght = in.read(buffer)) > -1) {
            fos.write(buffer, 0, lenght);
        }
        fos.close();
        in.close();
        System.out.println("File downloaded");
    }

}
