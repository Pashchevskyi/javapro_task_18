package ua.ithillel.lms;

import lombok.Getter;

public abstract class Reader {

  protected String filePath;

  @Getter
  protected String fileData;
}
