package org.springframework.security.web.authentication;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.SpringSecurityCoreVersion;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class WebAuthenticationDetails implements Serializable {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
}
