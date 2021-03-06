package com.ng.tm.web.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ng.tm.domain.Profile;

@Component
public class ProfileResourceAssembler extends
		ResourceAssemblerSupport<Profile, ProfileResource> {

	@Autowired
	private ToolResourceAssembler toolResourceAssembler;

	public ProfileResourceAssembler() {
		super(ProfileContoller.class, ProfileResource.class);
	}

	@Override
	public ProfileResource toResource(final Profile entity) {
		ProfileResource resource = createResource(entity);
		resource.setFirstname(entity.getFirstname());
		resource.setLastname(entity.getLastname());
		resource.setToolResources(toolResourceAssembler.toResources(entity
				.getTools()));
		resource.add(ControllerLinkBuilder.linkTo(ProfileContoller.class)
				.withRel("tools"));
		return resource;
	}

}
