package com.ng.tm.web.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ng.tm.domain.Profile;
import com.ng.tm.dto.ProfileList;
import com.ng.tm.exception.BusinessException;
import com.ng.tm.repository.ProfileRepository;

@Controller
@RequestMapping("/profiles")
public class ProfileContoller {

	@Autowired
	private ProfileRepository profileRepo;

	@Autowired
	private ProfileResourceAssembler profileResourceAssembler;

	@RequestMapping(method = RequestMethod.POST, value = "", consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> addProfile(@RequestBody final Profile newProfile) {
		profileRepo.save(newProfile);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ProfileResource> retrieveProfile(
			@PathVariable("id") final String profileId)
			throws BusinessException {
		Profile profile = profileRepo.findOne(profileId);
		if (profile != null) {
			return new ResponseEntity<ProfileResource>(
					profileResourceAssembler.toResource(profile), HttpStatus.OK);
		}
		throw new BusinessException("Aucun profile ne correspond à l'id : "
				+ profileId);

	}

	@RequestMapping(method = RequestMethod.GET, value = "", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ProfileList> retrieveProfilesList() {
		ProfileList profilesList = new ProfileList();
		List<Profile> profiles = profileRepo.findAll();
		profilesList.setProfilesList(profileResourceAssembler
				.toResources(profiles));
		return new ResponseEntity<ProfileList>(profilesList, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ProfileResource> updateProfile(
			@PathVariable("id") final String profileId,
			@RequestBody final Profile newProfile) throws BusinessException {
		final Profile existingProfile = profileRepo.findOne(profileId);
		if (existingProfile != null) {
			newProfile.setId(profileId);
			Profile updatedProfile = profileRepo.save(newProfile);
			return new ResponseEntity<ProfileResource>(
					profileResourceAssembler.toResource(updatedProfile),
					HttpStatus.OK);
		}
		throw new BusinessException("Aucun profile ne correspond à l'id : "
				+ profileId);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> deleteProfile(
			@PathVariable("id") final String profileId) {
		profileRepo.delete(profileId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<String> handleBusinessException(
			final BusinessException e) {
		return new ResponseEntity<String>(e.getMessage(),
				HttpStatus.BAD_REQUEST);
	}
}
