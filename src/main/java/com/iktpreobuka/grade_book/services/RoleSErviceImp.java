package com.iktpreobuka.grade_book.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.grade_book.entities.DTO.ParentDTO;
import com.iktpreobuka.grade_book.entities.DTO.RoleDTO;
import com.iktpreobuka.grade_book.repositories.UserRoleRepository;
@Service
public class RoleSErviceImp implements RoleService {

	
	@Autowired
	private UserRoleRepository roleRepository;
	@Override
	public List<RoleDTO> GetRole(Integer role_id) {
		List<Tuple>listPr = roleRepository.findRoleById(role_id);
		List<RoleDTO> listParents = listPr.stream()
				.map(t->new RoleDTO(
						t.get(0,Integer.class),
						t.get(1,String.class)
					
						))
				.collect(Collectors.toList());
			
		return listParents;
	}

}
