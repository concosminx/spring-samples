package com.nimsoc.hazelcast.repository;

import com.nimsoc.hazelcast.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
