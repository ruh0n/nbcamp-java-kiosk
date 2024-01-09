package screen;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import object.Menu;
import object.Product;

public class OrderConfirmScreen extends AbstractScreen {

  public OrderConfirmScreen(AbstractScreen screen) {
    super(screen);
  }


  @Override
  protected AbstractScreen navigateByInput() {
    AbstractScreen destination = null;

    int input = getInput();
    if (input == 1 && !productsToOrder.isEmpty()) {
      destination = new OrderCompleteScreen(this);
    } else if (input == 2) {
      destination = new MainMenuScreen(this);
    }

    return destination;
  }

  @Override
  public void printScreen() {
    System.out.println("아래와 같이 주문 하시겠습니까?");

    System.out.println("\n[ Orders ]");
    printProductsWithCount();

    System.out.println("\n[ Total ]");
    System.out.printf("₩ %.1fk%n", productsToOrder.stream().mapToDouble(Product::getPrice).sum());
    System.out.println("\n1. 확인        2. 취소");
  }

  public void printProductsWithCount() {
    if (productsToOrder.isEmpty()) {
      System.out.println("아무것도 없습니다.");
      return;
    }
    int maxNameLength = productsToOrder.stream()
                                       .map(Menu::getName)
                                       .mapToInt(String::length)
                                       .max()
                                       .orElse(0);

    Map<Product, Long> productsToPrint =
        productsToOrder.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    productsToPrint.forEach((product, count) ->
                                System.out.format(
                                    "%-" + maxNameLength + "s | ₩ %.1fk | %d개 | %s%n",
                                    product.getName(), product.getPrice() * count, count, product.getDescription()));
  }
}
