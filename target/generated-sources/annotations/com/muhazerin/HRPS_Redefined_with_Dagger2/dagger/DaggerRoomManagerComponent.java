package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.FileIO;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.FileIO_Factory;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.RoomManager;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.Scanner;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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

  private Provider<FileIO> fileIOProvider;

  private DaggerRoomManagerComponent(Scanner scannerParam) {
    this.scanner = scannerParam;
    initialize(scannerParam);
  }

  public static RoomManagerComponent.Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Scanner scannerParam) {
    this.fileIOProvider = DoubleCheck.provider(FileIO_Factory.create());
  }

  @Override
  public RoomManager getRoomManager() {
    return new RoomManager(scanner, fileIOProvider.get());}

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
