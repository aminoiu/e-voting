package com.electronicvoting.service.profile;

import com.electronicvoting.domain.dto.ProfileDTO;
import com.electronicvoting.entity.Profile;
import com.electronicvoting.entity.Voter;

public interface ProfileService {
    Profile findByEmail(String email);

    Profile saveProfile(Profile profile);

    Profile updateProfile(String email,ProfileDTO profileDTO);

    Profile findById(String profileId);
}
