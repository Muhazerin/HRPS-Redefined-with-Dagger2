package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import java.util.Scanner;

import dagger.Module;
import dagger.Provides;

@Module
public class ScannerModule {
	@Provides
	public Scanner provideScanner() {
		return new Scanner(System.in);
	}
}
