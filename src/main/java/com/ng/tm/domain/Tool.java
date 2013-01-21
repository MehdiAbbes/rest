package com.ng.tm.domain;

import java.net.URI;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;
import org.springframework.hateoas.Identifiable;

import com.ng.tm.web.jersey.ToolJerseyResource;
import com.sun.jersey.server.linking.Ref;
import com.sun.jersey.server.linking.Ref.Style;

@XmlRootElement
@Persistent
public class Tool implements Identifiable<String> {

	@Ref(resource = ToolJerseyResource.class, style = Style.ABSOLUTE)
	private URI toolsUri;

	@NotNull
	@Id
	private String label;

	@NotNull
	private String description;

	private Integer score;

	@Ref(value = "tools/{label}", style = Style.ABSOLUTE)
	private URI toolUri;

	public URI getToolUri() {
		return toolUri;
	}

	public void setToolUri(final URI toolUri) {
		this.toolUri = toolUri;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(final Integer score) {
		this.score = score;
	}

	public URI getToolsUri() {
		return toolsUri;
	}

	public void setToolsUri(final URI toolsUri) {
		this.toolsUri = toolsUri;
	}

	@Override
	public String getId() {
		return label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Tool other = (Tool) obj;
		if (label == null) {
			if (other.label != null) {
				return false;
			}
		} else if (!label.equals(other.label)) {
			return false;
		}
		return true;
	}

}
