package screen;

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
    int input = getInput();

    AbstractScreen result = null;

    if (input == 1) {
      addProductToOrder();
      result = new MainMenuScreen(this, product);
    } else {
      result = new MainMenuScreen(this);
    }

    if (product.hasOption()) {
      product.getOptionList().forEach(option -> option.setSelectedChoice(null));
    }

    return result;
  }

  @Override
  public void printScreen() {
    printProductWithOptions(product);

    System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
    System.out.println("1. 확인        2. 취소");
  }

  private void addProductToOrder() {
    StringBuilder optionedName = new StringBuilder(product.getName());
    double optionedPrice = product.getPrice();

    if (product.hasOption()) {
      for (ProductOption option : product.getOptionList()) {
        Integer selectedIndex = option.getSelectedIndex();
        if (selectedIndex == null) {
          break;
        }

        Choice choice = option.getChoices().get(selectedIndex);
        optionedName.append("(")
                    .append(choice.description())
                    .append(")");
        optionedPrice += choice.offsetPrice();
      }
    }

    productsToOrder.add(new Product(product.getCategory(), optionedName.toString(), optionedPrice, product.getDescription()));

    if (product.hasOption()) {
      product.getOptionList().forEach(option -> option.setSelectedChoice(null));
    }
  }
}
