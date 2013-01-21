package com.ng.tm.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ng.tm.domain.Profile;

@XmlRootElement
public class ProfileJerseyList {

	private List<Profile> prfilesList = new ArrayList<Profile>();

	@XmlElement(name = "profile")
	public List<Profile> getPrfilesList() {
		return prfilesList;
	}

	public void setPrfilesList(final List<Profile> prfilesList) {
		this.prfilesList = prfilesList;
	}

}
