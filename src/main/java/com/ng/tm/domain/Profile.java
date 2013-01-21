package com.ng.tm.domain;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;
import org.springframework.hateoas.Identifiable;

import com.ng.tm.web.jersey.ProfileJerseyResource;
import com.sun.jersey.server.linking.Ref;
import com.sun.jersey.server.linking.Ref.Style;

@Persistent
@XmlRootElement
public class Profile implements Identifiable<String> {

	@Ref(resource = ProfileJerseyResource.class, style = Style.ABSOLUTE)
	private URI profilesUri;

	@Ref(value = "profiles/{id}", style = Style.ABSOLUTE)
	private URI profileUri;

	@Ref(resource = ProfileJerseyResource.class, style = Style.ABSOLUTE)
	private URI toolsUri;

	@NotNull
	@Size(min = 2)
	private String firstname;

	@NotNull
	@Size(min = 2)
	private String lastname;

	private Set<Tool> tools = new HashSet<Tool>();

	@Id
	private String id;

	@Override
	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	@XmlElementWrapper(name = "toolsList")
	public Set<Tool> getTools() {
		return tools;
	}

	public void setTools(final Set<Tool> tools) {
		this.tools = tools;
	}

	public URI getProfilesUri() {
		return profilesUri;
	}

	public void setProfilesUri(final URI profilesUri) {
		this.profilesUri = profilesUri;
	}

	public URI getProfileUri() {
		return profileUri;
	}

	public void setProfileUri(final URI profileUri) {
		this.profileUri = profileUri;
	}

	public URI getToolsUri() {
		return toolsUri;
	}

	public void setToolsUri(final URI toolsUri) {
		this.toolsUri = toolsUri;
	}

}
