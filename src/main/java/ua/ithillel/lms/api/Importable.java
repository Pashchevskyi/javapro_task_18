package ua.ithillel.lms.api;

import ua.ithillel.lms.Order;

public interface Importable {

  /**
   * Converts string from table file to instance of object
   *
   * @param data String from file in table
   * @return Order object
   */
  Order toObject(String data);
}
