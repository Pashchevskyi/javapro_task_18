package ua.ithillel.lms;

public class CSVParser extends Parser {

  public CSVParser(CSVReader reader) {
    super(reader);
    this.data = reader.getFileData();
  }

  @Override
  public String[][] parse() {
    String[] dataRows = data.split(rowsDelimiter);
    header = dataRows[headerRow].split(columnsDelimiter);
    String[][] result = new String[dataRows.length - 1][header.length];
    for (int i = (theFirstRowIsHeader == true) ? 1 : 0; i < dataRows.length; i++) {
      String[] dataRow = dataRows[i].split(columnsDelimiter);
      for (int j = 0; j < header.length; j++) {
        result[(theFirstRowIsHeader == true) ? i - 1 : i][j] = (trimSpaces) ? dataRow[j].trim() :
            dataRow[j];
      }
    }
    return result;
  }
}
