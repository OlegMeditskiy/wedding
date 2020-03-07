package ru.dasha.wedding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.dasha.wedding.domain.Story;
import ru.dasha.wedding.domain.user.User;
import ru.dasha.wedding.repos.StoryRepo;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasAuthority('ADMIN')")
public class StoryController {
    @Autowired
    StoryRepo storyRepo;

    @PostMapping("/addStory")
    public ResponseEntity<String> addStory(
            @RequestBody Story story
    ){
     Story story1 = new Story();
     story1.setStoryTitle(story.getStoryTitle());
     story1.setStory(story.getStory());
     storyRepo.save(story1);
     return ResponseEntity.accepted().body("Story was added");

    }

    @PostMapping("/updateStory/{id}")
    public ResponseEntity<String> updateStory(
            @RequestBody Story story,
            @PathVariable Long id
            ){
        Story story1 = storyRepo.findById(id).get();
        story1.setStoryTitle(story.getStoryTitle());
        story1.setStory(story.getStory());
        storyRepo.save(story1);
        return ResponseEntity.accepted().body("Story was updated");

    }
}
