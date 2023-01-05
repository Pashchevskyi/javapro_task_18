package ua.ithillel.lms;

import java.io.IOException;
import ua.ithillel.lms.api.Writable;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) {

    System.out.println("Hello World!");
    try {
      Reader reader = new CSVReader("./uploads/order_1.csv");
      System.out.println(reader.fileData);
      System.out.println("Parsing...");
      Parser parser = new CSVParser((CSVReader) reader);
      String[][] parsedData = parser.parse();
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < parser.header.length; j++) {
        sb.append(parser.header[j]);
        sb.append("\t");
      }
      sb.append("\n");
      for (String[] row : parsedData) {
        for (String column : row) {
          sb.append(column);
          sb.append("\t");
        }
        sb.append("\n");
      }
      System.out.println(sb);
      Order order = new Order();
      order.toObject("Auchan;Chocolate;30.91;2");
      String dataToWrite = order.fromObject();
      Writable csvWriter = new CSVWriter("./downloads/auchan.csv",
          "ИМЯ;НАИМЕНОВАНИЕ;ЦЕНА;ШТ");
      csvWriter.write(dataToWrite);
      System.out.println(order.getPrice());
      System.out.println("Order processor...");
      Reader[] readers = {new CSVReader("./uploads/order_1.csv"),
          new CSVReader("./uploads/order_2.csv")};
      Parser[] parsers = {new CSVParser((CSVReader) readers[0]),
          new CSVParser((CSVReader) readers[1])};
      OrderProcessor orderProcessor = new OrderProcessor(parsers);
      orderProcessor.splitByShops();
      orderProcessor.wareSummary();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
