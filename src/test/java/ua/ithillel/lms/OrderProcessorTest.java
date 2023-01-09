package ua.ithillel.lms;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderProcessorTest {

  @BeforeEach
  public void deleteGeneratedFiles() {
    File fileAuchan = new File("./src/test/resources/Auchan.csv");
    if (fileAuchan.isFile()) {
      fileAuchan.delete();
    }
    File fileFora = new File("./src/test/resources/Fora.csv");
    if (fileFora.isFile()) {
      fileFora.delete();
    }
    File fileSummary = new File("./src/test/resources/summary.csv");
    if (fileSummary.isFile()) {
      fileSummary.delete();
    }
  }

  @Test
  public void testSplitByShops() {
    System.out.println("testing of splitByShops() method");
    //CSVReader mock object
    CSVReader[] readers = {Mockito.mock(CSVReader.class)};
    Mockito.when(readers[0].getFileData()).thenReturn(
        "ИМЯ;НАИМЕНОВАНИЕ;ЦЕНА;ШТ\nAuchan;Chocolate;30.91;2\nFora;Coca-Cola;40.57;1\n"
            + "Fora;Chocolate;15.45;3;");
    CSVParser[] parsers = {Mockito.mock(CSVParser.class)};
    String[][] parsedData = {{"Auchan", "Chocolate", "30.91", "2"},
        {"Fora", "Coca-Cola", "40.57", "1"},
        {"Fora", "Chocolate", "15.45", "3"}};
    Mockito.when(parsers[0].parse()).thenReturn(parsedData);

    try {
      OrderProcessor orderProcessor = new OrderProcessor(parsers);
      Map<String, List<Order>> ordersPerShop = orderProcessor.splitByShops();
      Assertions.assertEquals("Chocolate", ordersPerShop.get("Auchan").get(0).getWareName());
      Assertions.assertEquals("Coca-Cola", ordersPerShop.get("Fora").get(0).getWareName());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

  }

  @Test
  public void testWareSummary() {
    CSVReader[] readers = {Mockito.mock(CSVReader.class)};
    Mockito.when(readers[0].getFileData()).thenReturn(
        "ИМЯ;НАИМЕНОВАНИЕ;ЦЕНА;ШТ\nAuchan;Chocolate;30.91;2\nFora;Coca-Cola;40.57;1\n"
            + "Fora;Chocolate;15.45;3;");
    CSVParser[] parsers = {Mockito.mock(CSVParser.class)};
    String[][] parsedData = {{"Auchan", "Chocolate", "30.91", "2"},
        {"Fora", "Coca-Cola", "40.57", "1"},
        {"Fora", "Chocolate", "15.45", "3"}};
    Mockito.when(parsers[0].parse()).thenReturn(parsedData);

    try {
      OrderProcessor orderProcessor = new OrderProcessor(parsers);
      Map<String, List<Order>> ordersPerShop = orderProcessor.splitByShops();
      List<String> summary = orderProcessor.wareSummary();
      Assertions.assertEquals("Coca-Cola;40.57;1", summary.get(0));
      Assertions.assertEquals("Chocolate;23.18;5", summary.get(1));
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
