package com.ng.tm.web.spring;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ng.tm.domain.Tool;

@Component
public class ToolResourceAssembler extends
		ResourceAssemblerSupport<Tool, ToolResource> {

	public ToolResourceAssembler() {
		super(ToolController.class, ToolResource.class);
	}

	@Override
	public ToolResource toResource(final Tool entity) {
		ToolResource toolResource = createResource(entity);
		toolResource.setDescription(entity.getDescription());
		toolResource.setScore(entity.getScore());
		toolResource.add(ControllerLinkBuilder.linkTo(ToolController.class)
				.withRel("tools"));
		return toolResource;
	}

}
