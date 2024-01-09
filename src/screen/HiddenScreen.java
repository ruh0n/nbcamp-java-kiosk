package screen;

import object.Product;

public class HiddenScreen extends AbstractScreen {

  public HiddenScreen(AbstractScreen screen) {
    super(screen);
  }

  @Override
  protected AbstractScreen navigateByInput() {
    AbstractScreen destination = null;

    int input = getInput();

    if (input == 0) {
      destination = new MainMenuScreen(this);
    }

    return destination;
  }

  @Override
  protected void printScreen() {
    System.out.println("\n[ 총 판매상품 목록 현황 ]");
    System.out.println("현재까지 총 판매된 상품 목록은 아래와 같습니다.");
    int maxNameLength = orderHistory.stream().mapToInt(value -> value.getName().length()).max()
                                    .orElse(0);

    orderHistory.forEach(product -> System.out.format(
        "- %-" + maxNameLength + "s | ₩ %.1f%n", product.getName(), product.getPrice()));

    System.out.println("\n[ 총 판매금액 현황 ]");
    System.out.format("현재까지 총 판매된 금액은 [ ₩ %.1f ] 입니다.",
                      orderHistory.stream().mapToDouble(Product::getPrice).sum());

    System.out.println("\n0. 이전화면으로...");
  }
}
