package com.ng.tm.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;
import org.springframework.hateoas.Identifiable;

@Persistent
@XmlRootElement
public class Profile implements Identifiable<String> {

	@NotNull
	@Size(min = 2)
	private String firstname;

	@NotNull
	@Size(min = 2)
	private String lastname;

	@ManyToMany(cascade = CascadeType.ALL)
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

	public Set<Tool> getTools() {
		return tools;
	}

	public void setTools(final Set<Tool> tools) {
		this.tools = tools;
	}
}
