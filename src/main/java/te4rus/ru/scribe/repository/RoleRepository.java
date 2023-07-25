package te4rus.ru.scribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import te4rus.ru.scribe.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
