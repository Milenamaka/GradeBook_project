package com.iktpreobuka.grade_book.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iktpreobuka.grade_book.entities.DTO.RoleDTO;
@Service
public interface RoleService {
	public List<RoleDTO> GetRole(Integer role_id);
}
