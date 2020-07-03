package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.FileIO;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.ReservationManager;
import dagger.internal.Preconditions;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerReservationManagerComponent implements ReservationManagerComponent {
  private final Scanner scanner;

  private DaggerReservationManagerComponent(Scanner scannerParam,
      ScheduledExecutorService scheduledExecutorService) {
    this.scanner = scannerParam;
  }

  public static ReservationManagerComponent.Builder builder() {
    return new Builder();
  }

  @Override
  public ReservationManager getReservationManager() {
    return new ReservationManager(scanner, new FileIO());}

  private static final class Builder implements ReservationManagerComponent.Builder {
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
    public ReservationManagerComponent build() {
      Preconditions.checkBuilderRequirement(scanner, Scanner.class);
      Preconditions.checkBuilderRequirement(scheduledExecutorService, ScheduledExecutorService.class);
      return new DaggerReservationManagerComponent(scanner, scheduledExecutorService);
    }
  }
}
