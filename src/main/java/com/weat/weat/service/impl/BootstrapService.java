package com.weat.weat.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.weat.weat.common.utils.CoreUtil;
import com.weat.weat.data.model.Role;
import com.weat.weat.data.model.SystemParameter;
import com.weat.weat.repository.RoleRepository;
import com.weat.weat.repository.SystemParameterRepository;
import com.weat.weat.util.RoleEnum;
import com.weat.weat.util.SysParameterEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@DependsOn("encoder")
@Slf4j
@RequiredArgsConstructor
public class BootstrapService {

	private final SystemParameterRepository systemParameterRepository;
	private final RoleRepository roleRepository;

	public void execute() {
		createSysParameter();
		createRole();
	}



	private void createSysParameter() {
		log.info("Entering BootStrap Service to Create System Parameter");
		List<SystemParameter> paramList = new ArrayList<>();
		Arrays.asList(SysParameterEnum.values()).forEach(item -> {
			Optional<SystemParameter> sysParamOp = systemParameterRepository.findByParamName(item.getParamName());
			if (!sysParamOp.isPresent()) {
				paramList.add(SystemParameter.builder().paramName(item.getParamName()).paramValue(item.getParamValue())
						.build());
			}
		});
		if (CoreUtil.isNonEmpty(paramList)) {
			systemParameterRepository.saveAll(paramList);
		}
	}

	private void createRole() {

		RoleEnum[] roles = RoleEnum.values();
		Arrays.asList(roles).forEach(roleName -> {
			String roleNameStr = roleName.getDescription();

			Optional<Role> roleOp = roleRepository.findByRoleName(roleNameStr);
			if (roleOp.isPresent()) {
				// role is already present don't do anything
			} else {
				Role role;
				role = Role.builder().roleName(roleNameStr).roleRestricted(false).build();
				roleRepository.save(role);
			}
		});

	}
	
}