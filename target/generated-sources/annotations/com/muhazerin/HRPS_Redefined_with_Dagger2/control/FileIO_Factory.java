package com.muhazerin.HRPS_Redefined_with_Dagger2.control;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class FileIO_Factory implements Factory<FileIO> {
  @Override
  public FileIO get() {
    return newInstance();
  }

  public static FileIO_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FileIO newInstance() {
    return new FileIO();
  }

  private static final class InstanceHolder {
    private static final FileIO_Factory INSTANCE = new FileIO_Factory();
  }
}
