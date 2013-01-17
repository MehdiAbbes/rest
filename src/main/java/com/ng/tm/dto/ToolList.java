package com.ng.tm.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ng.tm.web.spring.ToolResource;

@XmlRootElement
public class ToolList {

	private List<ToolResource> tools = new ArrayList<ToolResource>();

	@XmlElement(name = "tool")
	public List<ToolResource> getTools() {
		return tools;
	}

	public void setTools(final List<ToolResource> tools) {
		this.tools = tools;
	}

}
