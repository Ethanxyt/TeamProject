package uk.ncl.cs.teamproject.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import uk.ncl.cs.teamproject.model.User;

/**
 * @author yantao xu
 */
public interface UserDao extends JpaRepository<User,String> {

    User findByEmail(String email);

}