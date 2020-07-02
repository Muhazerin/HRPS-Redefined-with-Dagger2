package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.FileIO;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.GuestManager;
import dagger.internal.Preconditions;
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
  private final ScannerModule scannerModule;

  private DaggerGuestManagerComponent(ScannerModule scannerModuleParam) {
    this.scannerModule = scannerModuleParam;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static GuestManagerComponent create() {
    return new Builder().build();
  }

  @Override
  public GuestManager getGuestManager() {
    return new GuestManager(ScannerModule_ProvideScannerFactory.provideScanner(scannerModule), new FileIO());}

  public static final class Builder {
    private ScannerModule scannerModule;

    private Builder() {
    }

    public Builder scannerModule(ScannerModule scannerModule) {
      this.scannerModule = Preconditions.checkNotNull(scannerModule);
      return this;
    }

    public GuestManagerComponent build() {
      if (scannerModule == null) {
        this.scannerModule = new ScannerModule();
      }
      return new DaggerGuestManagerComponent(scannerModule);
    }
  }
}
