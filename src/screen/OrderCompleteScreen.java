package screen;

public class OrderCompleteScreen extends AbstractScreen {

  public OrderCompleteScreen(AbstractScreen screen) {
    super(screen);
  }


  @Override
  protected AbstractScreen navigateByInput() {
    this.orderHistory.addAll(productsToOrder);
    this.productsToOrder.clear();

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return new MainMenuScreen(this);
  }

  @Override
  public void printScreen() {
    System.out.println("주문이 완료되었습니다!\n");
    System.out.format("대기 번호는 [ %d ] 번 입니다.\n", this.orderNumber++);
    System.out.println("([ 3 ]초후 메뉴판으로 돌아갑니다.)");
  }
}
