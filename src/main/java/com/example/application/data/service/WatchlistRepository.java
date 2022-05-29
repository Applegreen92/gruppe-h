package com.example.application.data.service;

import com.example.application.views.watchlist.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.JoinTable;

@JoinTable
public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {

    Watchlist findWachtlistByUserId(int userId);
}
