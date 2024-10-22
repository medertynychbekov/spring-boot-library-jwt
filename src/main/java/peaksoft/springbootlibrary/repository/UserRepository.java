package peaksoft.springbootlibrary.repository;

import org.springframework.data.jpa.repository.Query;
import peaksoft.springbootlibrary.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByName(String name);

  @Query("from UserEntity  where name like : author")
  UserEntity findByAuthorOrTitle(String author, String title);

}
