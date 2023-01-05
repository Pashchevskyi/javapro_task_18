package ua.ithillel.lms;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import ua.ithillel.lms.api.Writable;

@RequiredArgsConstructor
public class CSVWriter implements Writable {

  private final String filePath;

  private final String header;

  @Override
  public void write(String data) throws IOException {
    try (FileWriter writer = new FileWriter(filePath, true);
        FileReader reader = new FileReader(filePath)) {
      char[] readChars = new char[header.length()];
      reader.read(readChars);
      String readStr = String.valueOf(readChars, 0, header.length());
      if (readStr.indexOf(header) != 0) {
        writer.write(header);
        if (header.indexOf("\n") != header.length() - 1) {
          writer.append("\n");
        }
      }
      writer.append(data);
      if (data.indexOf("\n") != data.length() - 1) {
        writer.append("\n");
      }
      writer.flush();
    }
  }
}
