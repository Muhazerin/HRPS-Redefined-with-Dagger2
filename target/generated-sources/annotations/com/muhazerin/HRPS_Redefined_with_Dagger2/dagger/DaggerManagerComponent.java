package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.FileIO;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.FileIO_Factory;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.GuestManager;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.MenuItemManager;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.ReservationManager;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.RoomManager;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;
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
public final class DaggerManagerComponent implements ManagerComponent {
  private final Scanner scanner;

  private final ScheduledExecutorService scheduledExecutorService;

  private Provider<FileIO> fileIOProvider;

  private DaggerManagerComponent(Scanner scannerParam,
      ScheduledExecutorService scheduledExecutorServiceParam) {
    this.scanner = scannerParam;
    this.scheduledExecutorService = scheduledExecutorServiceParam;
    initialize(scannerParam, scheduledExecutorServiceParam);
  }

  public static ManagerComponent.Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Scanner scannerParam,
      final ScheduledExecutorService scheduledExecutorServiceParam) {
    this.fileIOProvider = DoubleCheck.provider(FileIO_Factory.create());
  }

  @Override
  public GuestManager getGuestManager() {
    return new GuestManager(scanner, fileIOProvider.get());}

  @Override
  public RoomManager getRoomManager() {
    return new RoomManager(scanner, fileIOProvider.get());}

  @Override
  public MenuItemManager getMenuItemManager() {
    return new MenuItemManager(scanner, fileIOProvider.get());}

  @Override
  public ReservationManager getReservationManager() {
    return new ReservationManager(scanner, fileIOProvider.get(), scheduledExecutorService);}

  private static final class Builder implements ManagerComponent.Builder {
    private Scanner scanner;

    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public Builder scanner(Scanner sc) {
      this.scanner = Preconditions.checkNotNull(sc);
      return this;
    }

    @Override
    public Builder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
      this.scheduledExecutorService = Preconditions.checkNotNull(scheduledExecutorService);
      return this;
    }

    @Override
    public ManagerComponent build() {
      Preconditions.checkBuilderRequirement(scanner, Scanner.class);
      Preconditions.checkBuilderRequirement(scheduledExecutorService, ScheduledExecutorService.class);
      return new DaggerManagerComponent(scanner, scheduledExecutorService);
    }
  }
}
