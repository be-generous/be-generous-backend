package com.begenerous.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private String email;
    private String password;
    private String fullName;
    private String avatarURL;

    public static class Builder {
        private String email;
        private String password;
        private String fullName;
        private String avatarURL;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder avatarURL(String avatarURL) {
            this.email = avatarURL;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(email, password, fullName, avatarURL);
        }
    }
}
