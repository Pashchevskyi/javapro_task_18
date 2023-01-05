package ua.ithillel.lms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ua.ithillel.lms.api.Writable;

public class OrderProcessor {

  private final Parser[] parsers;
  private Order[] orders;

  public OrderProcessor(Parser[] parsers) throws IOException {
    this.parsers = parsers;
    for (Parser parser : this.parsers) {
      String[][] tableData = parser.parse();
      orders = new Order[tableData.length];
      int i = 0;
      for (String[] tableRow : tableData) {
        orders[i] = new Order(tableRow[0], tableRow[1], Double.parseDouble(tableRow[2]),
            Integer.parseInt(tableRow[3]));
        i++;
      }
    }
  }
  public Map<String, List<Order>> splitByShops() throws IOException {
    Map<String, List<Order>> ordersPerShop = Arrays.stream(orders).collect(
        Collectors.groupingBy(Order::getShopName));
    for (String shopName : ordersPerShop.keySet()) {
      Writable writer = new CSVWriter("./downloads/" + shopName + ".csv",
          "ИМЯ;НАИМЕНОВАНИЕ;ЦЕНА;ШТ\n");
      for (int i = 0; i < ordersPerShop.get(shopName).size(); i++) {
        writer.write(ordersPerShop.get(shopName).get(i).fromObject());
      }
    }
    return ordersPerShop;
  }

  public List<String> wareSummary() throws IOException {
    Map<String, Double> avg = Arrays.stream(orders).collect(Collectors.groupingBy(
        Order::getWareName, Collectors.averagingDouble(Order::getPrice)));
    Map<String, Integer> qty = Arrays.stream(orders).collect(Collectors.groupingBy(
        Order::getWareName, Collectors.summingInt(Order::getQuantity)));
    Writable writer = new CSVWriter("./downloads/summary.csv",
        "НАИМЕНОВАНИЕ;ЦЕНА;ШТ\n");
    List<String> result = new ArrayList<>(avg.keySet().size());
    int i = 0;
    for (String wareName : avg.keySet()) {
      result.add(wareName + ";" + avg.get(wareName) + ";" + qty.get(wareName));
      writer.write(result.get(i));
      i++;
    }
    return result;
  }
}
