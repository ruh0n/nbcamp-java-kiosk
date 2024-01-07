package object;

import java.util.List;

public class ProductOption {

  protected final String optionName;
  protected final List<Choice> choices;
  protected Integer selectedChoice = null;

  public ProductOption(String optionName, Choice... choices) {
    this.optionName = optionName;
    this.choices = List.of(choices);
  }


  public String getOptionName() {
    return optionName;
  }

  public List<Choice> getChoices() {
    return choices;
  }

  public Integer getSelectedIndex() {
    return selectedChoice;
  }

  public void setSelectedChoice(Integer selectedChoice) {
    this.selectedChoice = selectedChoice;
  }

  public record Choice(String description, double offsetPrice) {

  }


}
