package com.ng.tm.web.spring;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

@XmlRootElement
public class ProfileResource extends ResourceSupport {

	private String firstname;
	private String lastname;

	private List<ToolResource> toolResources = new ArrayList<ToolResource>();

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

	public List<ToolResource> getToolResources() {
		return toolResources;
	}

	public void setToolResources(final List<ToolResource> toolResources) {
		this.toolResources = toolResources;
	}

}
