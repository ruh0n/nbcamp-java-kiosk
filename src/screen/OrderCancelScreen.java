package screen;

public class OrderCancelScreen extends AbstractScreen {

  public OrderCancelScreen(AbstractScreen screen) {
    super(screen);
  }


  @Override
  protected AbstractScreen navigateByInput() {
    int input = getInput();
    if (input == 1) {
      productsToOrder.clear();
      System.out.println("진행하던 주문이 취소되었습니다.");
    }
    return new MainMenuScreen(this);
  }

  @Override
  public void printScreen() {
    System.out.println("진행하던 주문을 취소하시겠습니까?");
    System.out.println("\n1. 확인        2. 취소");
  }
}
