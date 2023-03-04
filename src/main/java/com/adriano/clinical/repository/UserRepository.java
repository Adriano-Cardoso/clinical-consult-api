package com.adriano.clinical.repository;

import com.adriano.clinical.domain.Patient;
import com.adriano.clinical.domain.User;
import com.adriano.clinical.domain.dto.response.SearchPatientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String username);
}
