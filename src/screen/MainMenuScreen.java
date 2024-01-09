package screen;

import java.util.List;
import object.Menu;
import object.Product;

public class MainMenuScreen extends AbstractScreen {

  Product lastInsertedProduct = null;


  public MainMenuScreen(AbstractScreen screen) {
    super(screen);
  }

  public MainMenuScreen(ProductAddConfirmScreen screen, Product product) {
    super(screen);
    this.lastInsertedProduct = product;
  }

  public MainMenuScreen(List<Menu> menus, List<Product> products, List<Product> productsToOrder,
      List<Product> orderHistory, Integer orderNumber) {
    super(menus, products, productsToOrder, orderHistory, orderNumber);
  }


  @Override
  protected AbstractScreen navigateByInput() {
    AbstractScreen destination = null;

    int input = getInput();

    int maxCategory = menus.size();

    if (input == 0) {
      destination = new HiddenScreen(this);
    } else if (1 <= input && input <= maxCategory) {
      destination = new ProductMenuScreen(this, super.menus.get(input - 1).getCategory());
    } else if (input == maxCategory + 1) {
      destination = new OrderConfirmScreen(this);
    } else if (input == maxCategory + 2) {
      destination = new OrderCancelScreen(this);
    } else if (input == maxCategory + 3) {
      destination = new ExitScreen(this);
    }

    return destination;
  }

  @Override
  public void printScreen() {
    if (lastInsertedProduct != null) {
      System.out.format("%s 가 장바구니에 추가되었습니다.%n%n", productsToOrder.get(
          productsToOrder.size() - 1).getName());
    }
    System.out.println("\"Dunkin' Donuts 에 오신걸 환영합니다.\"");
    System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");

    printMenus(menus);
    printOrderMenus();
  }

  private void printOrderMenus() {
    System.out.println("\n[ ORDER MENU ]");
    int index = menus.size() + 1;

    String format = "%d. %-10s | %s%n";
    System.out.format(format, index++, "Order", "장바구니를 확인 후 주문합니다.");
    System.out.format(format, index++, "Cancel", "진행중인 주문을 취소합니다.");
    System.out.format(format, index++, "Exit", "종료");
  }
}
