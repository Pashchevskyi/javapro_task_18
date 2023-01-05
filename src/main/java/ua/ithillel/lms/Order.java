package ua.ithillel.lms;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ua.ithillel.lms.api.Exportable;
import ua.ithillel.lms.api.Importable;

@NoArgsConstructor
@AllArgsConstructor
public class Order implements Exportable, Importable {
  @Getter
  @Setter
  private String shopName;
  @Getter
  @Setter
  private String wareName;
  @Getter
  @Setter
  private double price;
  @Getter
  @Setter
  private int quantity;

  @Override
  public String fromObject() {
    String[] result = new String[4];
    result[0] = this.shopName;
    result[1] = this.wareName;
    result[2] = "" + this.price;
    result[3] = "" + this.quantity;
    return String.join(";", result);
  }

  @Override
  public Order toObject(String data) {
    String[] separatedData = data.split(";");
    this.setShopName(separatedData[0]);
    this.setWareName(separatedData[1]);
    this.setPrice(Double.parseDouble(separatedData[2]));
    this.setQuantity(Integer.parseInt(separatedData[3]));
    return this;
  }
}
