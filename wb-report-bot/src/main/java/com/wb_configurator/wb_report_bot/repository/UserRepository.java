package com.wb_configurator.wb_report_bot.repository;

import com.wb_configurator.wb_report_bot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Помечаем как репозиторий
public interface UserRepository extends JpaRepository<User, Long> {
}