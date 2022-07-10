package com.zenan.steepedbackend.controllers;

import com.zenan.steepedbackend.entities.Session;
import com.zenan.steepedbackend.repositories.SessionRepository;
import com.zenan.steepedbackend.services.shared.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(APIService.currentRoute)
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;

    // get sessions
    @GetMapping("sessions")
    public List<Session> getAllSessions() {
        return this.sessionRepository.findAll();
    }

    // get session by id
    @GetMapping("sessions/{sessionId}")
    public ResponseEntity<Session> getSessionById(@PathVariable int sessionId)
            throws InvalidConfigurationPropertyValueException {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("Session id", sessionId, "Session id not found."));
        return ResponseEntity.ok().body(session);
    }

    // get sessions by user id
    @GetMapping("sessions/userid/{userId}")
    public List<Session> getSessionByUserId(@PathVariable int userId) {
        return this.sessionRepository.findByUserId(userId);
    }

    // save session
    @PostMapping("sessions")
    public Session createSession(@RequestBody Session session) {
        return this.sessionRepository.save(session);
    }

    // update session
    @PutMapping("sessions/{sessionId}")
    public ResponseEntity<Session> updateSession(@PathVariable int sessionId,
                                                 @RequestBody Session sessionDetails) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("Session id", sessionId, "Session id not found"));
        session.replaceFields(sessionDetails);
        return ResponseEntity.ok(this.sessionRepository.save(session));
    }

    // delete session
    @DeleteMapping("sessions/{sessionId}")
    public Map<String, Boolean> deleteSession(@PathVariable int sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("Session id", sessionId, "Session id not found"));
        this.sessionRepository.delete(session);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    // get session by user id
}
