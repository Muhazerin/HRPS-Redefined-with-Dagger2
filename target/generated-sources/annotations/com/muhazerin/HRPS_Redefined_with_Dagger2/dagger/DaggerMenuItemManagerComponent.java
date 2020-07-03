package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.FileIO;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.MenuItemManager;
import dagger.internal.Preconditions;
import java.util.Scanner;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerMenuItemManagerComponent implements MenuItemManagerComponent {
  private final Scanner scanner;

  private DaggerMenuItemManagerComponent(Scanner scannerParam) {
    this.scanner = scannerParam;
  }

  public static MenuItemManagerComponent.Builder builder() {
    return new Builder();
  }

  @Override
  public MenuItemManager getMenuItemManager() {
    return new MenuItemManager(scanner, new FileIO());}

  private static final class Builder implements MenuItemManagerComponent.Builder {
    private Scanner scanner;

    @Override
    public Builder scanner(Scanner sc) {
      this.scanner = Preconditions.checkNotNull(sc);
      return this;
    }

    @Override
    public MenuItemManagerComponent build() {
      Preconditions.checkBuilderRequirement(scanner, Scanner.class);
      return new DaggerMenuItemManagerComponent(scanner);
    }
  }
}
