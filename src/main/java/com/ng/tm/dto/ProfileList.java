package com.ng.tm.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ng.tm.web.spring.ProfileResource;

@XmlRootElement
public class ProfileList {

	private List<ProfileResource> profilesList = new ArrayList<ProfileResource>();

	@XmlElement(name = "profile")
	public List<ProfileResource> getProfilesList() {
		return profilesList;
	}

	public void setProfilesList(final List<ProfileResource> profilesList) {
		this.profilesList = profilesList;
	}

}
