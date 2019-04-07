package com.tirmizee.backend.service;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tirmizee.backend.api.role.data.ReqUpdateRoleDTO;
import com.tirmizee.backend.api.role.data.RoleDTO;
import com.tirmizee.backend.api.role.data.SearchTermDTO;
import com.tirmizee.backend.dao.RoleDao;
import com.tirmizee.backend.dao.RoleMapPermissionDao;
import com.tirmizee.core.component.PageMapper;
import com.tirmizee.core.constant.MessageCode;
import com.tirmizee.core.domain.Role;
import com.tirmizee.core.domain.RoleMapPermission;
import com.tirmizee.core.exception.BusinessException;
import com.tirmizee.core.utilities.CollectionUtils;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private PageMapper mapper;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleMapPermissionDao roleMapPermissionDao;
	
	@Override
	public Page<RoleDTO> buildPageByTerm(SearchTermDTO searchTerm) {
		Integer page = ObjectUtils.defaultIfNull(searchTerm.getPage(), 0),
			    size = ObjectUtils.defaultIfNull(searchTerm.getSize(), 20);
		Pageable pageable = new PageRequest(page, size);
		return roleDao.findPage(searchTerm, pageable);
	}

	@Override
	@Transactional
	public void updateRole(ReqUpdateRoleDTO updateRoleDTO) {
		
		Role role = roleDao.findOne(updateRoleDTO.getRoleId());
		
		if (role == null) {
			throw new BusinessException(MessageCode.MSG006, "Role data");
		}
		
		List<RoleMapPermission> oldPermissons = roleMapPermissionDao.findByRoleId(role.getId());
		List<Integer> updatePermissions = CollectionUtils.emptyIfNull(updateRoleDTO.getPerIds());
		
		for (Iterator<Integer> updateIterator = updatePermissions.iterator() ; updateIterator.hasNext();) {
			Integer updatePerId = updateIterator.next();
			for (Iterator<RoleMapPermission> oldIterator = oldPermissons.iterator(); oldIterator.hasNext();) {
				RoleMapPermission oldPermission = oldIterator.next();
				if (updatePerId.equals(oldPermission.getPerId())) {
					updateIterator.remove();
					oldIterator.remove();
				}
			}
		}
		
		// update role
		mapper.map(updateRoleDTO, role);
		roleDao.save(role);
		
		// delete permission of role
		if (!oldPermissons.isEmpty()) {
			roleMapPermissionDao.delete(oldPermissons);
		}
		
		// insert permission of role
		if (!updatePermissions.isEmpty()) {
			for (Integer perId : updatePermissions) {
				RoleMapPermission newPermission = new RoleMapPermission();
				newPermission.setRoleId(role.getId());
				newPermission.setPerId(perId);
				roleMapPermissionDao.save(newPermission);
			}
		}
		
	}

}
