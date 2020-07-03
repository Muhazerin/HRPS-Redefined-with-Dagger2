package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.FileIO;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.RoomManager;
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
public final class DaggerRoomManagerComponent implements RoomManagerComponent {
  private final Scanner scanner;

  private DaggerRoomManagerComponent(Scanner scannerParam) {
    this.scanner = scannerParam;
  }

  public static RoomManagerComponent.Builder builder() {
    return new Builder();
  }

  @Override
  public RoomManager getRoomManager() {
    return new RoomManager(scanner, new FileIO());}

  private static final class Builder implements RoomManagerComponent.Builder {
    private Scanner scanner;

    @Override
    public Builder scanner(Scanner sc) {
      this.scanner = Preconditions.checkNotNull(sc);
      return this;
    }

    @Override
    public RoomManagerComponent build() {
      Preconditions.checkBuilderRequirement(scanner, Scanner.class);
      return new DaggerRoomManagerComponent(scanner);
    }
  }
}
