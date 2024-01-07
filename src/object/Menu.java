package object;

import common.Category;

public class Menu {

  protected final Category category;
  protected final String name;
  protected final String description;


  public Menu(Category category, String description) {
    this.description = description;
    this.name = category.getName();
    this.category = category;
  }

  public Menu(Category category, String name, String description) {
    this.category = category;
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Category getCategory() {
    return category;
  }
}
