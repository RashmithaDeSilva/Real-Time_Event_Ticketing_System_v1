package com.realtimeeventticketingsystem.service;


public interface SystemConfigureService {
    int findConfigValue(String configKey);
    boolean updateConfigValue(String configKey, int configValue);
}
