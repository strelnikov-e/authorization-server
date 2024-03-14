package com.sltrelnikov.resourceserver.repository;

import com.sltrelnikov.resourceserver.entity.Authority;
import com.sltrelnikov.resourceserver.entity.AuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId> {
}
