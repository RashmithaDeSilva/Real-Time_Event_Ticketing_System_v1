package com.realtimeeventticketingsystem.service.impl;

import com.realtimeeventticketingsystem.repo.SystemConfigureRepo;
import com.realtimeeventticketingsystem.service.SystemConfigureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SystemConfigureServiceImpl implements SystemConfigureService {

    private final SystemConfigureRepo systemConfigureRepo;

    @Override
    public int findConfigValue(String configKey) {
        return systemConfigureRepo.findByConfigKey(configKey);
    }

    @Override
    @Transactional
    public boolean updateConfigValue(String configKey, int configValue) {
        return systemConfigureRepo.updateConfigValueUsingKey(configKey, configValue) == 1;
    }
}
