package org.springframework.security.core.authority;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.SpringSecurityCoreVersion;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SimpleGrantedAuthority implements Serializable {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private String role;

    public String getAuthority() {
        return this.role;
    }

}
