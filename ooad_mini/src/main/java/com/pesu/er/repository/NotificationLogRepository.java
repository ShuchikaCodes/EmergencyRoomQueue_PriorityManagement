package com.pesu.er.repository;

import com.pesu.er.model.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {

    List<NotificationLog> findTop10ByOrderByCreatedAtDesc();
}
