package com.opennice.os;



public interface IMonitorService {
	public MonitorInfoBean getMonitorInfoBean() throws Exception;
	public MonitorInfoBean getMonitorInfoBean(String processName) throws Exception;
}
