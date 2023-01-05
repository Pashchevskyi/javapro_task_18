package ua.ithillel.lms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CSVParserTest {

  @Test
  public void testParse() {
    CSVReader reader = Mockito.mock(CSVReader.class);
    Mockito.when(reader.getFileData()).thenReturn(
        "ИМЯ;НАИМЕНОВАНИЕ;ЦЕНА;ШТ\nAuchan;Chocolate;30.91;2\nFora;Coca-Cola;40.57;1\n"
            + "Fora;Chocolate;15.45;3;");
    CSVParser parser = new CSVParser(reader);
    String[][] parsedData = parser.parse();
    Assertions.assertEquals("ИМЯ", parsedData[0][0]);
    Assertions.assertEquals("Auchan", parsedData[1][0]);
    Assertions.assertEquals("НАИМЕНОВАНИЕ", parsedData[0][1]);
    Assertions.assertEquals("Chocolate", parsedData[1][1]);
  }
}
