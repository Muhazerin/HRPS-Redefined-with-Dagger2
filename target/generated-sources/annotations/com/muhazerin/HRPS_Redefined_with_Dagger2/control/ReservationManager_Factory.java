package com.muhazerin.HRPS_Redefined_with_Dagger2.control;

import com.muhazerin.HRPS_Redefined_with_Dagger2.interfaces.DataAccess;
import dagger.internal.Factory;
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
public final class ReservationManager_Factory implements Factory<ReservationManager> {
  private final Provider<Scanner> scProvider;

  private final Provider<DataAccess> dataAccessProvider;

  public ReservationManager_Factory(Provider<Scanner> scProvider,
      Provider<DataAccess> dataAccessProvider) {
    this.scProvider = scProvider;
    this.dataAccessProvider = dataAccessProvider;
  }

  @Override
  public ReservationManager get() {
    return newInstance(scProvider.get(), dataAccessProvider.get());
  }

  public static ReservationManager_Factory create(Provider<Scanner> scProvider,
      Provider<DataAccess> dataAccessProvider) {
    return new ReservationManager_Factory(scProvider, dataAccessProvider);
  }

  public static ReservationManager newInstance(Scanner sc, DataAccess dataAccess) {
    return new ReservationManager(sc, dataAccess);
  }
}
