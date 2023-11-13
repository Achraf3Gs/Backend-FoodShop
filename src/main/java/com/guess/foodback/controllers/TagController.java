package com.guess.foodback.controllers;

import com.guess.foodback.entities.Tag;
import com.guess.foodback.entities.TagRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({""})

public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/tags")
    public List<Tag> getAllTeachers() {
        return (List<Tag>) tagRepository.findAll();
    }

    @PostMapping("/tags/add")
    public Tag createTag(@Valid @RequestBody Tag tag) {
        return tagRepository.save(tag);
    }

    @PutMapping("/tags/{tagId}")
    public Tag updateTag(@PathVariable Long tagId, @Valid @RequestBody Tag tagRequest) {
        return tagRepository.findById(tagId).map(tag -> {
            tag.setName(tagRequest.getName());
            tag.setCount(tagRequest.getCount());

            return tagRepository.save(tag);
        }).orElseThrow(() -> new IllegalArgumentException("TagId " + tagId + " not found"));
    }


    @DeleteMapping("/tags/{tagId}")
    public ResponseEntity<?> deleteTag(@PathVariable Long tagId) {
        return tagRepository.findById(tagId).map(tag -> {
            tagRepository.delete(tag);
            System.out.println(tagId);
            return ResponseEntity.ok().build();

        }).orElseThrow(() -> new IllegalArgumentException("TagId " + tagId + " not found"));

    }

    @GetMapping("/tags/{tagId}")
    public Tag getTeacher(@PathVariable Long teacherId) {

        Optional<Tag> p = tagRepository.findById(teacherId);

        return p.get();

    }
}