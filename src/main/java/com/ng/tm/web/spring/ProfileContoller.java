package com.ng.tm.web.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ng.tm.domain.Profile;
import com.ng.tm.repository.ProfileRepository;

@Controller
@RequestMapping("/profiles")
public class ProfileContoller {

	@Autowired
	private ProfileRepository profileRepo;

	@RequestMapping(method = RequestMethod.POST, value = "", consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> addProfile(@RequestBody final Profile newProfile) {
		profileRepo.save(newProfile);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Profile> retrieveProfile(
			@PathVariable("id") final String profileId) {
		Profile profile = profileRepo.findOne(profileId);
		return new ResponseEntity<Profile>(profile, HttpStatus.OK);
	}
}
