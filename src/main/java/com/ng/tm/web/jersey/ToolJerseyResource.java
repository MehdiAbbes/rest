package com.ng.tm.web.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ng.tm.domain.Tool;
import com.ng.tm.dto.ToolJerseyList;
import com.ng.tm.exception.BusinessException;
import com.ng.tm.repository.ToolRepository;

@Component
@Path("/tools")
public class ToolJerseyResource {

	@Context
	private UriInfo uriInfo;

	@Autowired
	private ToolRepository toolRepository;

	@PUT
	@Path("/{label}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveTool(@PathParam("label") final String toolLabel,
			final Tool newTool) {
		boolean creation = false;
		if (!toolRepository.exists(toolLabel)) {
			creation = true;
		}
		newTool.setLabel(toolLabel);
		final Tool savedTool = toolRepository.save(newTool);
		Response response = null;
		if (creation) {
			response = Response.created(
					UriBuilder.fromUri(uriInfo.getAbsolutePath()).build())
					.build();
		} else {
			response = Response.ok(savedTool).build();
		}
		return response;

	}

	@GET
	@Path("/{label}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response retrieveTool(@PathParam("label") final String toolLabel)
			throws BusinessException {
		final Tool tool = toolRepository.findOne(toolLabel);
		if (tool == null) {
			throw new BusinessException("no tool with label : " + toolLabel);
		} else {
			return Response.ok(tool).build();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response retrieveAllTools() {
		ToolJerseyList toolJerseyList = new ToolJerseyList();
		toolJerseyList.setToolsList(toolRepository.findAll());
		return Response.ok(toolJerseyList).build();
	}

	@DELETE
	@Path("/{label}")
	public Response deleteTool(@PathParam("label") final String toolLabel) {
		toolRepository.delete(toolLabel);
		return Response.ok().build();
	}
}
