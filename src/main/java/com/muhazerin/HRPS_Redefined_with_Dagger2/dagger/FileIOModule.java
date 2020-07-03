package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.FileIO;
import com.muhazerin.HRPS_Redefined_with_Dagger2.interfaces.DataAccess;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class FileIOModule {
	
	@Binds
	public abstract DataAccess bindDataAccess(FileIO fileIO);
}
