package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.FileIO;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.GuestManager;
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
public final class DaggerGuestManagerComponent implements GuestManagerComponent {
  private final Scanner scanner;

  private DaggerGuestManagerComponent(Scanner scannerParam) {
    this.scanner = scannerParam;
  }

  public static GuestManagerComponent.Builder builder() {
    return new Builder();
  }

  @Override
  public GuestManager getGuestManager() {
    return new GuestManager(scanner, new FileIO());}

  private static final class Builder implements GuestManagerComponent.Builder {
    private Scanner scanner;

    @Override
    public Builder scanner(Scanner sc) {
      this.scanner = Preconditions.checkNotNull(sc);
      return this;
    }

    @Override
    public GuestManagerComponent build() {
      Preconditions.checkBuilderRequirement(scanner, Scanner.class);
      return new DaggerGuestManagerComponent(scanner);
    }
  }
}
