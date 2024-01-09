package screen;

import java.util.List;
import java.util.stream.IntStream;
import object.Product;
import object.ProductOption;
import object.ProductOption.Choice;

public class OptionSelectionScreen extends AbstractScreen {

  private final Product product;
  private boolean isLastOption;
  private ProductOption optionToPrint;

  public OptionSelectionScreen(AbstractScreen screen, Product product) {
    super(screen);
    this.product = product;
  }

  @Override
  protected AbstractScreen navigateByInput() {
    boolean isOptionSelected = false;

    do {
      int input = getInput();

      if (1 <= input && input <= optionToPrint.getChoices().size()) {
        optionToPrint.setSelectedChoice(input - 1);
        isOptionSelected = true;
      }
    } while (!isOptionSelected);

    return isLastOption ? new ProductAddConfirmScreen(this, product) : new OptionSelectionScreen(this, this.product);

  }

  @Override
  protected void printScreen() {
    List<ProductOption> optionList = product.getOptionList();
    optionToPrint = null;

    for (int i = 0; i < optionList.size(); i++) {
      if (i == optionList.size() - 1) {
        this.isLastOption = true;
      }

      ProductOption productOption = optionList.get(i);
      if (productOption.getSelectedIndex() == null) {
        optionToPrint = productOption;
        break;
      }
    }

    printProductWithOptions(product);
    System.out.format("%n%s를 선택 해주세요.%n", optionToPrint.getOptionName());

    List<Choice> choices = optionToPrint.getChoices();
    IntStream.range(0, choices.size())
             .forEach(
                 i -> System.out.format("%d. %s(₩ +%.1f)%n", i + 1, choices.get(i).description(), choices.get(i).offsetPrice()));

  }
}
