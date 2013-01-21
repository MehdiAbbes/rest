package com.ng.tm.web.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import com.ng.tm.domain.Profile;
import com.ng.tm.domain.Tool;
import com.ng.tm.dto.ProfileJerseyList;
import com.ng.tm.exception.BusinessException;
import com.ng.tm.repository.ProfileRepository;
import com.ng.tm.repository.ToolRepository;

@Path("/profiles")
@Component
public class ProfileJerseyResource {

	@Context
	private UriInfo uriInfo;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ToolRepository toolRepository;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createProfile(final Profile newProfile) {
		Profile savedProfile = profileRepository.save(newProfile);
		return Response.created(
				UriBuilder.fromUri(uriInfo.getAbsolutePath())
						.path("/" + savedProfile.getId()).build()).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateProfile(@PathParam("id") final String profileId,
			final Profile newProfile) {
		newProfile.setId(profileId);
		final Profile savedProfile = profileRepository.save(newProfile);
		return Response.ok(savedProfile).build();
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.TEXT_PLAIN })
	public Response retieveProfile(@PathParam("id") final String profileId)
			throws BusinessException {
		Profile profile = profileRepository.findOne(profileId);

		if (profile != null) {
			return Response.ok(profile).build();
		}
		throw new BusinessException("pas de profile ayant comme id : "
				+ profileId);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response retrieveAllProfile() {
		ProfileJerseyList profiles = new ProfileJerseyList();
		profiles.setPrfilesList(profileRepository.findAll());
		return Response.ok(profiles).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteProfile(@PathParam("id") final String profileId) {
		profileRepository.delete(profileId);
		return Response.ok().build();

	}

	@POST
	@Path("/{profileId}/tools/{toolId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addToolToProfile(
			@PathParam("profileId") final String profileId,
			@PathParam("toolId") final String toolId) {
		Tool tool = toolRepository.findOne(toolId);
		Profile profile = profileRepository.findOne(profileId);
		profile.getTools().add(tool);
		Profile savedProfile = profileRepository.save(profile);
		return Response.ok(savedProfile).build();
	}

	@DELETE
	@Path("/{profileId}/tools/{toolId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response removeToolToProfile(
			@PathParam("profileId") final String profileId,
			@PathParam("toolId") final String toolId) {
		Tool tool = toolRepository.findOne(toolId);
		Profile profile = profileRepository.findOne(profileId);
		profile.getTools().remove(tool);
		Profile savedProfile = profileRepository.save(profile);
		return Response.ok(savedProfile).build();
	}
}
