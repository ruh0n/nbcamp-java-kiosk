import common.Category;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import object.Menu;
import object.Product;
import object.ProductOption;
import object.ProductOption.Choice;
import screen.AbstractScreen;
import screen.MainMenuScreen;

public class KioskApp {

  List<Menu> MENUS;
  List<Product> PRODUCTS;
  List<Product> productsToOrder = new ArrayList<>();

  List<Product> orderHistory = new ArrayList<>();
  Integer orderNumber = 1;

  AbstractScreen screen;

  public KioskApp() {
    initialize();
  }

  public void run() {
    do {
      clearScreen();
      this.screen = this.screen.execute();
    } while (this.screen != null);
  }

  private void clearScreen() {
    System.out.println("\n".repeat(25));
  }

  private void initialize() {

    this.MENUS = Arrays.asList(new Menu(Category.DONUT, "어떤 취향도 만족시킬 던킨도너츠의 도넛"),
                               new Menu(Category.BAGEL, "상쾌한 아침을 만나기 위한 베이글"),
                               new Menu(Category.SANDWICHES, "신선한 재료와 풍부한 맛의 조화로 만들어진 샌드위치"),
                               new Menu(Category.COFFEE, "일상에 활력을 불어넣을 향긋한 커피"),
                               new Menu(Category.BEVERAGE, "도넛의 맛을 한층 업그레이드 시켜주는 음료"));

    ProductOption sizeOption = new ProductOption("Size", new Choice("Small", 0), new Choice("Medium", 0.5), new Choice("Large", 1.0));
    ProductOption iceHotOption = new ProductOption("Ice/Hot", new Choice("Ice", 0.5), new Choice("Hot", 0));
    ProductOption packageOption = new ProductOption("Package", new Choice("NoPackage", 0), new Choice("Package", 0.5));
    this.PRODUCTS = Arrays.asList(new Product(Category.DONUT, "Famous Glazed", 1.5, "더욱 촉촉하고 부드러워진 달콤한 정통 도넛"),
                                  new Product(Category.DONUT, "Strawberry Filled", 1.9, "예쁜 딸기가 상큼, 새하얀 슈가파우더가 입안에서 스르륵"),
                                  new Product(Category.DONUT, "Bavarian Filled", 1.9, "부드러운 바바리안 크림과 부드러운 도넛이 조화를 이룬 도넛"),
                                  new Product(Category.DONUT, "Boston Kreme", 2.1, "부드러운 바바리안 크림이 들어간 도넛에 달콤한 초콜릿과 화려한 일곱 빛깔 무지개 컬러 스프링클을 토핑한 도넛"),
                                  new Product(Category.DONUT, "Cacao Frosted", 1.9, "카카오의 진한 맛과 부드러운 도넛, 일곱 빛깔 무지개 컬러로 업그레이드된 제품"),
                                  new Product(Category.BAGEL, "Truffle_Cheese_Bagel", 2.5, "트러플 오일과 롤치즈가 들어있어 풍미 가득한 트러플 치즈 베이글"),
                                  new Product(Category.BAGEL, "Basil Bagel", 2.5, "바질페스토가 들어간 반죽으로 만들어 향긋한 바질 풍미가 살아있는 바질 베이글"),
                                  new Product(Category.BAGEL, "Campagne Bagel", 2.5, "견과류가 듬뿍 들어있어 고소한 풍미가 특징인 깜빠뉴 베이글"),
                                  new Product(Category.BAGEL, "Plain Bagel", 2.5, "씹을수록 고소하고 쫄깃한 플레인 베이글"),
                                  new Product(Category.BAGEL, "Blueberry Bagel", 2.5, "상큼한 블루베리 맛을 즐길 수 있는 인기 만점의 담백한 던킨 베이글"),
                                  new Product(Category.SANDWICHES, "Bacon Egg Maple Bun", 3.5, "소프트빵에 베이컨, 에그를 넣고 메이플시럽을 듬뿍 뿌린 샌드위치", packageOption),
                                  new Product(Category.SANDWICHES, "Basil Scrambled Sandwich", 4.5, "바질과 버섯, 계란을 호밀 사워도우에 가득품은 샌드위치", packageOption),
                                  new Product(Category.SANDWICHES, "Bacon & Egg English Muffin", 3.5, "부드러운 계란과 베이컨, 치즈가 담백한 잉글리쉬머핀과 어우러진 샌드위치",
                                              packageOption),
                                  new Product(Category.SANDWICHES, "Quatro Cheese Croque monsieur", 4.9, "부드러운 계란과 베이컨, 치즈가 담백한 잉글리쉬머핀과 어우러진 샌드위치",
                                              packageOption),
                                  new Product(Category.COFFEE, "Americano", 3.7, "깊은 카카오의 풍미와 풍부한 바디감을 느낄 수 있는 던킨 아메리카노", sizeOption, iceHotOption),
                                  new Product(Category.COFFEE, "Cafe Latte", 4.0, "진한 에스프레소와 따뜻한 우유의 만남", sizeOption, iceHotOption),
                                  new Product(Category.COFFEE, "Caramel Macchiato", 4.5, "진한 에스프레소와 따뜻한 우유, 달콤한 카라멜이 조화로운 커피", sizeOption,
                                              iceHotOption),
                                  new Product(Category.BEVERAGE, "Lemonade", 4.2, "레몬과즙이 함유된 새콤달콤한 레몬에이드"),
                                  new Product(Category.BEVERAGE, "Yogurt Strawberry Latte", 5.9, "과육이 씹히는 딸기와 플레인 요거트가 만나 더욱 새콤달콤한 딸기 요거트 라떼"),
                                  new Product(Category.BEVERAGE, "English Black First Tea", 3.5, "깔끔한 맛이 매력적인 깊고 그윽한 블랙티"),
                                  new Product(Category.BEVERAGE, "Kiwi Coolatta", 4.5, "키위의 상큼함과 대체당(몽크프룻)으로 건강한 단맛을 살린 키위 쿨라타"),
                                  new Product(Category.BEVERAGE, "Hershey´s Choco", 4.0, "달콤한고 진한 허쉬 초콜릿의 풍미를 느낄 수 있는 음료"));

    this.screen = new MainMenuScreen(MENUS, PRODUCTS, productsToOrder, orderHistory, orderNumber);
  }
}
