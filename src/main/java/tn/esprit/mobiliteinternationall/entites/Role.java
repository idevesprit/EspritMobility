package tn.esprit.mobiliteinternationall.entites;

import static tn.esprit.mobiliteinternationall.constant.Authority.ADMIN_AUTHORITIES;
import static tn.esprit.mobiliteinternationall.constant.Authority.CANDIDAT_AUTHORITIES;
import static tn.esprit.mobiliteinternationall.constant.Authority.HR_AUTHORITIES;
import static tn.esprit.mobiliteinternationall.constant.Authority.SUPER_ADMIN_AUTHORITIES;
import static tn.esprit.mobiliteinternationall.constant.Authority.USER_AUTHORITIES;

public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_HR(HR_AUTHORITIES),
    ROLE_CANDIDAT(CANDIDAT_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES),
    ROLE_SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES);

    private String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
