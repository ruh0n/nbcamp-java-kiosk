package screen;

import common.Category;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import object.Menu;
import object.Product;
import object.ProductOption;
import object.ProductOption.Choice;

public abstract class AbstractScreen {

  static Scanner sc = new Scanner(System.in);
  protected final List<Menu> menus;
  protected final List<Product> products;
  protected final List<Product> productsToOrder;
  protected final List<Product> orderHistory;
  Integer orderNumber;

  protected AbstractScreen(AbstractScreen screen) {
    this.menus = screen.menus;
    this.products = screen.products;
    this.productsToOrder = screen.productsToOrder;
    this.orderHistory = screen.orderHistory;
    this.orderNumber = screen.orderNumber;
  }

  protected AbstractScreen(List<Menu> menus, List<Product> products, List<Product> productsToOrder,
      List<Product> orderHistory, Integer orderNumber) {
    this.menus = menus;
    this.products = products;
    this.productsToOrder = productsToOrder;
    this.orderHistory = orderHistory;
    this.orderNumber = orderNumber;
  }


  public AbstractScreen execute() {
    this.printScreen();

    return this.navigateByInput();
  }

  protected abstract AbstractScreen navigateByInput();


  protected abstract void printScreen();

  protected void printMenus(List<Menu> menus) {
    System.out.println("\n[ DUNKIN' MENU ]");
    int maxNameLength = menus.stream().mapToInt(product -> product.getName().length()).max()
                             .orElse(0);

    IntStream.range(0, menus.size()).forEach(index -> System.out.format(
        "%d. %-" + maxNameLength + "s | %s %n",
        index + 1, menus.get(index).getName(), menus.get(index).getDescription()));

  }

  protected void printProducts(List<Product> products) {
    printProducts(products, null);
  }

  protected void printProducts(List<Product> products, Category category) {
    if (category != null) {
      System.out.printf("%n[ %s MENU ]%n", category.getName());
    }

    if (products.isEmpty()) {
      System.out.println("아무것도 없습니다.");
      return;
    }

    List<Product> productsToPrint = products.stream()
                                            .filter(product -> category == null || product.getCategory().equals(category)).toList();
    int maxNameLength = productsToPrint.stream().mapToInt(product -> product.getName().length())
                                       .max().orElse(0);

    IntStream.range(0, productsToPrint.size()).forEach(index -> {
      Product product = productsToPrint.get(index);
      System.out.format(
          "%d. %-" + maxNameLength + "s | ₩ %.1fk | %s %n",
          index + 1, product.getName(), product.getPrice(), product.getDescription());
    });
  }

  protected void printProductWithOptions(Product product) {
    StringBuilder sb = new StringBuilder();

    // 이름
    sb.append(product.getName());
    List<ProductOption> options = product.getOptionList();
    double price = product.getPrice();

    if (product.hasOption()) {
      for (ProductOption option : options) {
        Integer selectedIndex = option.getSelectedIndex();
        if (selectedIndex == null) {
          break;
        }

        Choice choice = option.getChoices().get(selectedIndex);
        sb.append("(")
          .append(choice.description())
          .append(")");
        price += choice.offsetPrice();
      }
    }

    sb.append(" | ₩ ")
      .append(price)
      .append("k | ")
      .append(product.getDescription());

    System.out.println(sb);
  }


  protected int getInput() {
    String input;
    do {
      System.out.print("\n> ");
      input = sc.nextLine().strip();
    } while (input.isEmpty());
    return Integer.parseInt(input);
  }
}
