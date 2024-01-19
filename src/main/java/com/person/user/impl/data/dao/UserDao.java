package com.person.user.impl.data.dao;

import com.person.user.impl.data.model.UserDbModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<UserDbModel, Long>/*, JpaSpecificationExecutor<UserDbModel>*/ {

    @Query(value = "select nextval('user_id_seq')", nativeQuery = true)
    Long nextId();
}
