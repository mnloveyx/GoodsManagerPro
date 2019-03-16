package com.amuse.enums.license;
 
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;
 
/**
 * 
 * @author Administrator
 *
 */
public class LicenseManagerHolder {
	private static LicenseManager licenseManager;
	private LicenseManagerHolder(){}
	public static synchronized LicenseManager getLicenseManager(LicenseParam param){
		if(licenseManager == null){
			licenseManager = new LicenseManager(param);
		}
		return licenseManager;
	}
}