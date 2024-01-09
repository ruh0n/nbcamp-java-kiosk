package screen;

import java.util.List;
import object.Product;
import object.ProductOption;
import object.ProductOption.Choice;

public class ProductAddConfirmScreen extends AbstractScreen {

  private final Product product;

  public ProductAddConfirmScreen(AbstractScreen screen, Product product) {
    super(screen);
    this.product = product;
  }

  @Override
  protected AbstractScreen navigateByInput() {
    AbstractScreen destination = null;

    int input = getInput();
    if (input == 1) {
      addProductToOrder();
      destination = new MainMenuScreen(this, product);
    } else if (input == 2) {
      destination = new MainMenuScreen(this);
    }

    // To Fix NullPointerException
    if (product.hasOption()) {
      product.getOptionList().forEach(option -> option.setSelectedChoice(null));
    }

    return destination;
  }

  @Override
  public void printScreen() {
    printProductWithOptions(product);

    System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
    System.out.println("1. 확인        2. 취소");
  }

  private void addProductToOrder() {
    StringBuilder optionedName = new StringBuilder(product.getName());
    List<ProductOption> optionList = product.getOptionList();
    double optionedPrice = product.getPrice();

    if (product.hasOption()) {
      optionedName.append("(");

      for (int i = 0; i < optionList.size(); i++) {
        ProductOption currentOption = optionList.get(i);
        Integer selectedIndex = currentOption.getSelectedIndex();

        if (selectedIndex == null) {
          break;
        }

        Choice choice = currentOption.getChoices().get(selectedIndex);
        optionedName.append(choice.description());
        optionedPrice += choice.offsetPrice();

        if (i != optionList.size() - 1) {
          optionedName.append(", ");
        }
      }

      optionedName.append(")");
    }

    productsToOrder.add(new Product(product.getCategory(), optionedName.toString(), optionedPrice, product.getDescription()));

    if (product.hasOption()) {
      optionList.forEach(option -> option.setSelectedChoice(null));
    }
  }
}
