package object;

import common.Category;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Product extends Menu {

  protected final double price;
  protected List<ProductOption> optionList = null;

  public Product(Product product) {
    super(product.category, product.name, product.description);
    this.price = product.price;
    this.optionList = Collections.unmodifiableList(product.optionList);
  }

  public Product(Category category, String name, double price, String description) {
    super(category, name, description);
    this.price = price;
  }

  public Product(Category category, String name, double price, String description,
      ProductOption... productOption) {
    this(category, name, price, description);
    if (productOption != null) {
      this.optionList = List.of(productOption);
    }
  }

  @Override
  public String toString() {
    return this.name + " | "
        + "₩ " + this.price + "k | "
        + this.description;
  }

  public double getPrice() {
    return price;
  }

  public boolean hasOption() {
    return this.optionList != null;
  }

  public List<ProductOption> getOptionList() {
    return optionList;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Product)) {
      return false;
    }
    return Objects.equals(name, ((Product) obj).name)
        && Objects.equals(optionList, ((Product) obj).optionList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, optionList);
  }
}
