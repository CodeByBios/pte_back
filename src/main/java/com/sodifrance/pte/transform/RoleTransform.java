package com.sodifrance.pte.transform;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.sodifrance.pte.model.dto.RoleDto;
import com.sodifrance.pte.model.entity.Role;

/**
 * Classe de conversion des beans Role et RoleDto.
 */
@Component
public class RoleTransform {

	 @PostConstruct
	    public void init() {
	    	
	    	ModelMapper modelMapper = new ModelMapper();
	    	
	        // Pour la transformation Role -> RoleDto
	        modelMapper.addMappings(new PropertyMap<Role, RoleDto>() {
	            @Override
	            protected void configure() {
	            }
	        });
	    }
	    
	    
		/**
		 * Converti un dto en entité
		 *
		 * @param pRoleDto
		 *            objet à convertir en entité
		 * @return un Role
		 */
		public Role convertToEntity(RoleDto pRoleDto) {
			ModelMapper modelMapper = new ModelMapper();
			Role lRole = modelMapper.map(pRoleDto, Role.class);
			return lRole;
		}
		
		/**
		 * Converti une entité en dto
		 *
		 * @param pRole
		 *            l'entité à convertir
		 * @return le dto correspondant
		 */
		public RoleDto convertToDto(Role pRole) {
			ModelMapper modelMapper = new ModelMapper();
			RoleDto lRoleDto = modelMapper.map(pRole, RoleDto.class);
			return lRoleDto;
		}
}
