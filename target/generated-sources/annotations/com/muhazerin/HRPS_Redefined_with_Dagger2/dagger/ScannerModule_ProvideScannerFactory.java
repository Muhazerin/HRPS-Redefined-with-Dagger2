package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import dagger.internal.Factory;
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
public final class ScannerModule_ProvideScannerFactory implements Factory<Scanner> {
  private final ScannerModule module;

  public ScannerModule_ProvideScannerFactory(ScannerModule module) {
    this.module = module;
  }

  @Override
  public Scanner get() {
    return provideScanner(module);
  }

  public static ScannerModule_ProvideScannerFactory create(ScannerModule module) {
    return new ScannerModule_ProvideScannerFactory(module);
  }

  public static Scanner provideScanner(ScannerModule instance) {
    return Preconditions.checkNotNull(instance.provideScanner(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
