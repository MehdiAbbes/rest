package com.ng.tm.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ng.tm.domain.Tool;

@XmlRootElement
public class ToolJerseyList {

	private List<Tool> toolsList = new ArrayList<Tool>();

	@XmlElement(name = "tool")
	public List<Tool> getToolsList() {
		return toolsList;
	}

	public void setToolsList(final List<Tool> toolsList) {
		this.toolsList = toolsList;
	}

}
