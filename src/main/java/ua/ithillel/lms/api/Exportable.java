package ua.ithillel.lms.api;

public interface Exportable {

  /**
   * Converts object to string, which is ready to insert to file
   *
   * @return String ready to insert into file
   */
  String fromObject();
}
