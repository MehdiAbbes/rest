package com.ng.tm.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ng.tm.domain.Profile;

@XmlRootElement(name = "profilesList")
public class ProfileList {

	@XmlElement(name = "Profile")
	private List<Profile> profilesList = new ArrayList<Profile>();

	public List<Profile> getProfilesList() {
		return profilesList;
	}

	public void setProfilesList(final List<Profile> profilesList) {
		this.profilesList = profilesList;
	}

}
