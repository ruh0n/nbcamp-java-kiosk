package common;

public enum Category {
  DONUT("DONUT"),
  BAGEL("BAGEL"),
  SANDWICHES("SANDWICHES"),
  COFFEE("COFFEE"),
  BEVERAGE("BEVERAGE");

  private final String name;

  Category(String name) {
    this.name = name;

  }

  public String getName() {
    return name;
  }
}