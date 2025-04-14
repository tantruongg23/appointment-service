package vn.tayjava.config;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // You can get username from Spring Security here
        // For example:
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // return Optional.ofNullable(auth != null ? auth.getName() : "system");

        return Optional.of("system"); // fallback for now
    }
}
