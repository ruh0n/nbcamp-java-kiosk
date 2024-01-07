package screen;

import common.Category;
import java.util.List;
import object.Product;

public class ProductMenuScreen extends AbstractScreen {

  private final List<Product> filteredProducts;

  private final Category category;


  public ProductMenuScreen(AbstractScreen screen, Category category) {
    super(screen);
    this.filteredProducts = this.products.stream()
                                         .filter(product -> product.getCategory() == category)
                                         .toList();
    this.category = category;
  }


  @Override
  protected AbstractScreen navigateByInput() {
    int input = this.getInput();
    int maxProduct = this.filteredProducts.size();

    if (input == 0) {
      return new MainMenuScreen(this);
    } else if (1 <= input && input <= maxProduct) {
      Product selectedProduct = new Product(this.filteredProducts.get(input - 1));

      if (selectedProduct.hasOption()) {
        return new OptionSelectionScreen(this, new Product(selectedProduct));
      } else {
        return new ProductAddConfirmScreen(this, selectedProduct);
      }
    }

    return null;
  }

  @Override
  public void printScreen() {
    System.out.println("\"Dunkin' Donuts 에 오신걸 환영합니다.\"");
    System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.");

    printProducts(this.filteredProducts, category);

    System.out.println("\n0. 이전화면으로...");
  }
}
