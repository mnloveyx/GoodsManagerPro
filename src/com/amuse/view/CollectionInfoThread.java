package com.amuse.view;

import java.util.concurrent.Callable;

import com.amuse.enums.license.SystemTool;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CollectionInfoThread implements Callable<Boolean>{

//	@Override
//	public void run() {
//		
//		log.debug("collect client info hostName:{},ipaddress:{},machinecode:{},os:{}",SystemTool.getHostName(),SystemTool.getIpAddress(),SystemTool.getMachineCode(),SystemTool.getOS());
//	}

	@Override
	public Boolean call() throws Exception {
		log.debug("collect client info hostName:{},ipaddress:{},machinecode:{},os:{}",SystemTool.getHostName(),SystemTool.getIpAddress(),SystemTool.getMachineCode(),SystemTool.getOS());
		return true;
	}

}
