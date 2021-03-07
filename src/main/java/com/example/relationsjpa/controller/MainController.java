package com.example.relationsjpa.controller;

import com.example.relationsjpa.command.MentorDto;
import com.example.relationsjpa.command.StudentDto;
import com.example.relationsjpa.command.TeachesDto;
import com.example.relationsjpa.persistence.entity.Mentor;
import com.example.relationsjpa.persistence.entity.Student;
import com.example.relationsjpa.persistence.entity.Team;
import com.example.relationsjpa.service.MainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping("/team")
    public ResponseEntity<Team> createTeam(@RequestBody Team teamToCreate) {
        Team createdTeam = mainService.createTeam(teamToCreate);

        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @PostMapping("team/{teamId}/students")
    public ResponseEntity<StudentDto> createStudent(@PathVariable Long teamId,
                                                    @RequestBody Student studentToCreate) {
        StudentDto createdStudent = mainService.createStudent(studentToCreate, teamId);

        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PostMapping("/mentor/{studentId}/students")
    public ResponseEntity<MentorDto> createMentorWithStudent(@PathVariable Long studentId,
                                                         @RequestBody Mentor mentorToCreate) {
        MentorDto createdMentor = mainService.createMentor(mentorToCreate, studentId);
        return new ResponseEntity<MentorDto>(createdMentor, HttpStatus.CREATED);
    }

    @PostMapping("/team/{teamId}/mentor/{mentorId}")
    public ResponseEntity<TeachesDto> createTeachesRecord(@PathVariable long teamId, @PathVariable Long mentorId){
        TeachesDto createdTeachesRecord=mainService.createTeachesRecord(teamId,mentorId);
        return new ResponseEntity<>(createdTeachesRecord,HttpStatus.CREATED);
    }
}
