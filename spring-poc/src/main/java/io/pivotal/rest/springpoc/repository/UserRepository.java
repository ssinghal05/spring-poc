package io.pivotal.rest.springpoc.repository;

import io.pivotal.rest.springpoc.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
