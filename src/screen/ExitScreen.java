package screen;

public class ExitScreen extends AbstractScreen {

  public ExitScreen(AbstractScreen screen) {
    super(screen);
  }

  @Override
  protected AbstractScreen navigateByInput() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    return null;
  }

  @Override
  protected void printScreen() {
    System.out.println("감사합니다. 또 오세요~");
    System.out.println("([ 3 ]초후 종료됩니다.)\n");

  }
}
