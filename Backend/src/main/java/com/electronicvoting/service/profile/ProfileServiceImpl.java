package com.electronicvoting.service.profile;

import com.electronicvoting.domain.dto.ProfileDTO;
import com.electronicvoting.entity.Profile;
import com.electronicvoting.repository.ProfileRepository;
import com.electronicvoting.repository.VoterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    public Profile findByEmail(String email) {
        log.info("Find profile by e-mail[{}]", email);
        return profileRepository.findByEmail(email);
    }

    @Override
    public Profile saveProfile(Profile profile) {
        profile.setProfileId(UUID.randomUUID().toString());
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(String email, ProfileDTO profileDTO) {
        Profile profileToUpdate = profileRepository.findByEmail(email);
        profileToUpdate.setEmail(profileDTO.getEmail());
        profileToUpdate.setName(profileDTO.getName());
        profileToUpdate.setPosition(profileDTO.getPosition());
        profileToUpdate.setEducation(profileDTO.getEducation());
        profileToUpdate.setSelfDescription(profileDTO.getSelfDescription());
        return profileRepository.save(profileToUpdate);
    }

    @Override
    public Profile findById(String profileId) {
        return profileRepository.findByProfileId(profileId);
    }
}
