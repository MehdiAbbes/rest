package com.ng.tm.web.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ng.tm.domain.Tool;
import com.ng.tm.dto.ToolList;
import com.ng.tm.repository.ToolRepository;

@Controller
@RequestMapping("/tools")
public class ToolController {

	@Autowired
	private ToolRepository toolRepository;

	@Autowired
	private ToolResourceAssembler toolResourceAssembler;

	@RequestMapping(method = RequestMethod.PUT, value = "/{label}", consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ToolResource> createTool(
			@PathVariable("label") final String toolLabel,
			@RequestBody final Tool tool) {
		tool.setLabel(toolLabel);
		Tool savedTool = toolRepository.save(tool);
		final ToolResource toolResource = toolResourceAssembler
				.toResource(savedTool);
		return new ResponseEntity<ToolResource>(toolResource,
				HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ToolList> retrieveToolsList() {
		ToolList toolsList = new ToolList();
		toolsList.setTools(toolResourceAssembler.toResources(toolRepository
				.findAll()));
		return new ResponseEntity<ToolList>(toolsList, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{label}", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ToolResource> retrieveTool(
			@PathVariable("label") final String toolLabel) {

		ToolResource toolResource = toolResourceAssembler
				.toResource(toolRepository.findOne(toolLabel));
		return new ResponseEntity<ToolResource>(toolResource, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{label}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteTool(
			@PathVariable("label") final String toolLabel) {
		toolRepository.delete(toolLabel);
		return new ResponseEntity<String>(ControllerLinkBuilder.linkTo(
				getClass()).toString(), HttpStatus.OK);
	}

}
