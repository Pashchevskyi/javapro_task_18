package ua.ithillel.lms;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import lombok.Getter;

public class CSVReader extends Reader {

  protected final String filePath;

  @Getter
  protected final String fileData;

  public CSVReader(String strFilePath) throws IOException {
    File csvFile = new File(strFilePath);
    filePath = (csvFile.isFile()) ? strFilePath : "";
    int c;
    StringBuilder sb = new StringBuilder();
    try (FileReader reader = new FileReader(csvFile)) {
      while ((c = reader.read()) != -1) {
        sb.append((char) c);
      }
    }
    fileData = sb.toString();
  }
}
