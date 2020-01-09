package org.springframework.security.core.context;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.SpringSecurityCoreVersion;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SecurityContextImpl implements Serializable {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private UsernamePasswordAuthenticationToken authentication;
}
