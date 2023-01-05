package ua.ithillel.lms.api;

import java.io.IOException;

public interface Writable {
  void write(String data) throws IOException;
}
