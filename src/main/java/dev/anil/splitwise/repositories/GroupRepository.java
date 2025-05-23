package dev.anil.splitwise.repositories;

import dev.anil.splitwise.models.Expense;
import dev.anil.splitwise.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findById(long id);
}
