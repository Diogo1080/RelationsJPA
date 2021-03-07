package com.example.relationsjpa.service;

import com.example.relationsjpa.command.MentorDto;
import com.example.relationsjpa.command.StudentDto;
import com.example.relationsjpa.command.TeachesDto;
import com.example.relationsjpa.persistence.entity.Mentor;
import com.example.relationsjpa.persistence.entity.Student;
import com.example.relationsjpa.persistence.entity.Team;
import com.example.relationsjpa.persistence.repository.MentorRepository;
import com.example.relationsjpa.persistence.repository.StudentRepository;
import com.example.relationsjpa.persistence.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MentorRepository mentorRepository;

    public Team createTeam(Team teamToCreate) {
        Team createdTeam = teamRepository.save(teamToCreate);

        return createdTeam;
    }

    public StudentDto createStudent(Student studentToCreate, Long teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);

        if (team != null) {
            studentToCreate.setTeam(team);

            Student createdStudent = studentRepository.save(studentToCreate);

            return StudentDto.builder()
                    .id(createdStudent.getId())
                    .name(createdStudent.getName())
                    .age(createdStudent.getAge())
                    .teamId(createdStudent.getTeam().getId())
                    .build();
        }

        return null;
    }

    public MentorDto createMentor(Mentor mentorToCreate, long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);

        if (student != null) {
            mentorToCreate.setStudent(student);

            Mentor createdMentor = mentorRepository.save(mentorToCreate);

            return MentorDto.builder()
                    .id(createdMentor.getId())
                    .name(createdMentor.getName())
                    .age(createdMentor.getAge())
                    .studentId(createdMentor.getStudent().getId())
                    .build();
        }
        return null;
    }

    public TeachesDto createTeachesRecord(long teamId, Long mentorId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        Mentor mentor = mentorRepository.findById(mentorId).orElse(null);

        if(team != null && mentor !=null){
            mentor.getTeams().add(team);

            mentorRepository.save(mentor);

            return TeachesDto.builder()
                    .teamId(team.getId())
                    .mentorId(mentor.getId())
                    .build();
        }
        return null;
    }
}
