package ua.ithillel.lms;

public abstract class Parser {

  protected final Reader reader;
  protected String data;
  protected String rowsDelimiter = "\n";

  protected final boolean theFirstRowIsHeader = true;
  protected final String columnsDelimiter = ";";

  protected int headerRow = 0;

  protected String[] header = {};

  protected boolean trimSpaces = true;

  public Parser(Reader reader) {
    this.reader = reader;
  }

  public abstract String[][] parse();
}
