package ua.ithillel.lms;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CSVParserTest {

  @Test
  public void testParse() {
    CSVReader reader = Mockito.mock(CSVReader.class, Mockito.withSettings()
        .useConstructor("./src/test/resources/order_1.csv"));
    Mockito.when(reader.getFileData()).thenReturn(
        "ИМЯ;НАИМЕНОВАНИЕ;ЦЕНА;ШТ\nAuchan;Chocolate;30.91;2\nFora;Coca-Cola;40.57;1\n"
            + "Fora;Chocolate;15.45;3;");
    CSVParser parser = new CSVParser(reader);
    String[][] parsedData = parser.parse();
    Assertions.assertEquals("ИМЯ", parser.header[0]);
    Assertions.assertEquals("НАИМЕНОВАНИЕ", parser.header[1]);
    Assertions.assertEquals("ЦЕНА", parser.header[2]);
    Assertions.assertEquals("ШТ", parser.header[3]);
    Assertions.assertEquals("Auchan", parsedData[0][0]);
    Assertions.assertEquals("Chocolate", parsedData[0][1]);
  }

  @Test
  public void testParseEmptyFiles() {
    CSVReader reader = Mockito.mock(CSVReader.class);
    Mockito.when(reader.getFileData()).thenReturn("");
    CSVParser parser = new CSVParser(reader);
    String[][] parsedData = parser.parse();
    Assertions.assertEquals(1, parser.header.length);
    Assertions.assertEquals("", parser.header[0]);
    Assertions.assertEquals(0, parsedData.length);
  }

  @Test
  public void testParseNonExisting() {
    Assertions.assertThrows(IOException.class, () -> {
      new CSVReader("./src/test/resources/non_existing.csv");
    });
  }
}
