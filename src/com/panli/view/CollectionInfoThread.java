package com.panli.view;

import com.panli.enums.license.SystemTool;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CollectionInfoThread implements Runnable{

	@Override
	public void run() {
		log.debug("collect client info hostName:{},ipaddress:{},machinecode:{},os:{}",SystemTool.getHostName(),SystemTool.getIpAddress(),SystemTool.getMachineCode(),SystemTool.getOS());
	}

}
