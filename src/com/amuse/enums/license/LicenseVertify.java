package com.amuse.enums.license;
 
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.prefs.Preferences;
 
 
import de.schlichtherle.license.CipherParam;
import de.schlichtherle.license.DefaultCipherParam;
import de.schlichtherle.license.DefaultKeyStoreParam;
import de.schlichtherle.license.DefaultLicenseParam;
import de.schlichtherle.license.KeyStoreParam;
import de.schlichtherle.license.LicenseContentException;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;
import lombok.extern.slf4j.Slf4j;
 
@Slf4j
public class LicenseVertify {
	/**
	 * 公钥别名
	 */
	private String pubAlias;
	/**
	 * 该密码是在使用keytool生成密钥对时设置的密钥库的访问密码
	 */
	private String keyStorePwd;
	/**
	 * 系统的统一识别码
	 */
	private String onlykey;
	/**
	 * 证书路径
	 */
	private String licName;
	/**
	 * 公钥库路径
	 */
	private String pubPath;
	private String confPath="licenseVertifyConf.properties";
	
	public LicenseVertify(String onlykey){
		setConf(confPath,onlykey);
	}
	
	public LicenseVertify(String confPath,String onlykey){
		setConf(confPath,onlykey);
	}
	
	public void setConf(String confPath,String onlykey){
		Properties prop = new Properties();
		InputStream in = getClass().getResourceAsStream(confPath);
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.onlykey = onlykey;
		pubAlias = prop.getProperty("public.alias");
		keyStorePwd = prop.getProperty("key.store.pwd");
		licName = prop.getProperty("license.name");
		pubPath = prop.getProperty("public.store.path");
	}
	
	private LicenseParam initLicenseParams(){
		Class<LicenseVertify> clazz = LicenseVertify.class;
		Preferences pre = Preferences.userNodeForPackage(clazz);
		CipherParam cipherParam = new DefaultCipherParam(keyStorePwd);
		KeyStoreParam pubStoreParam = new DefaultKeyStoreParam(
				clazz,pubPath,pubAlias,keyStorePwd,null);
		
		LicenseParam licenseParam = new DefaultLicenseParam(
				onlykey,pre,pubStoreParam,cipherParam);
		return licenseParam;
	}
	
	private LicenseManager getLicenseManager(){
		return LicenseManagerHolder.getLicenseManager(initLicenseParams());
	}
	
	public void install(String licdir){
		try {
			LicenseManager licenseManager = getLicenseManager();
			//path=D:\eclipse_mars_workspace\LicenseTest\license.lic
			log.debug("path="+(licdir+File.separator+licName));
			File file = new File(licdir+File.separator+licName);
			licenseManager.install(file);
			log.debug("license install success！");
		} catch (Exception e) {
			log.debug("license install faile！");
			e.printStackTrace();
			System.exit(0);
		}
	}
	public int vertify(){
		try {
			LicenseManager licenseManager=getLicenseManager();
			licenseManager.verify();
			log.debug("license vertify success！");
			return 0;
		} catch(LicenseContentException ex){
			log.debug("license is expire！");
//			ex.printStackTrace();
			return 1;
		}catch (Exception e) {
			log.debug("license install faile！");
//			e.printStackTrace();
			return 2;
		}
	}
}