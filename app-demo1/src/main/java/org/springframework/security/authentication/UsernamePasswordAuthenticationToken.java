package org.springframework.security.authentication;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.SpringSecurityCoreVersion;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UsernamePasswordAuthenticationToken implements Serializable {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private Object principal;
    private Object credentials;
}
