package ru.dasha.wedding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dasha.wedding.domain.AboutUs;
import ru.dasha.wedding.domain.ProgramDetail;
import ru.dasha.wedding.domain.Story;
import ru.dasha.wedding.domain.WeddingDate;
import ru.dasha.wedding.repos.AboutUsRepo;
import ru.dasha.wedding.repos.ProgramDetailRepo;
import ru.dasha.wedding.repos.StoryRepo;
import ru.dasha.wedding.repos.WeddingDateRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Controller
@RequestMapping("/admin/aboutUs")
@PreAuthorize("hasAuthority('ADMIN')")
public class AboutUsController {

    private static final String DATE_FORMAT = "dd.MM.yyyy hh:mm";

    @Autowired
    AboutUsRepo aboutUsRepo;

    @Autowired
    WeddingDateRepo weddingDateRepo;

    @Autowired
    ProgramDetailRepo programDetailRepo;

    @Autowired
    StoryRepo storyRepo;

    @GetMapping
    public String viewAbout(Map<String, Object> model){
        List<AboutUs> aboutUsList = aboutUsRepo.findAll();
        System.out.println();
        model.put("aboutUs",aboutUsList);
        Date date = weddingDateRepo.findById((long)7).get().getWeddingDate();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String strDate = formatter.format(date);
        System.out.println(strDate);
        model.put("date",strDate);

        ProgramDetail programDetail1 = programDetailRepo.findById((long) 18).get();
        model.put("program",programDetail1.getProgram());

        Story story = storyRepo.findById((long) 19).get();
        model.put("story",story);
        return "aboutUs";
    }

    @PostMapping("/addAboutUs")
    public ResponseEntity<String> addAboutUs(
            @RequestBody AboutUs aboutUs
    ){
        AboutUs aboutUs1 = new AboutUs();
        aboutUs1.setName(aboutUs.getName());
        aboutUs1.setAbout(aboutUs.getAbout());
        aboutUsRepo.save(aboutUs1);
        return ResponseEntity.accepted().body("About was added");
    }

    @PostMapping("/saveDate")
    public String saveDate(
            @RequestParam String date
    ) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
//        TimeZone time_zone
//                = TimeZone.getTimeZone("Europe/Moscow");
//        formatter.setTimeZone(time_zone);
        Date dateToSet = formatter.parse(date);
        System.out.println(dateToSet);
       WeddingDate weddingDate = weddingDateRepo.findById((long) 7).get();

       weddingDate.setWeddingDate(dateToSet);
       weddingDateRepo.save(weddingDate);
        return "redirect:/admin/aboutUs";
    }

    @PostMapping("/updateAboutUs/{id}")
    public String updateAboutUs(
            @RequestParam String name,
            @RequestParam String about,
            @PathVariable Long id
    ){
        AboutUs aboutUs1 = aboutUsRepo.findById(id).get();
        aboutUs1.setName(name);
        aboutUs1.setAbout(about);
        aboutUsRepo.save(aboutUs1);
        return "redirect:/admin/aboutUs";

    }
    @PostMapping("/addProgram")
    public ResponseEntity<String> addProgram(
            @RequestBody ProgramDetail programDetail
    ){
        ProgramDetail programDetail1 = new ProgramDetail();
        programDetail1.setProgram(programDetail.getProgram());
        System.out.println(programDetail1.getProgram());
        programDetailRepo.save(programDetail1);
        return ResponseEntity.accepted().body("About was added");
    }
    @PostMapping("/updateProgram")
    public String updateProgram(
            @RequestParam String program
    ){
        ProgramDetail programDetail1 = programDetailRepo.findById((long) 18).get();
        programDetail1.setProgram(program);
        programDetailRepo.save(programDetail1);
        return "redirect:/admin/aboutUs";
    }
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
    @PostMapping("/updateStory")
    public String updateStory(
            @RequestParam String story
    ){
        Story story1 = storyRepo.findById((long) 19).get();
        story1.setStory(story);
        storyRepo.save(story1);
        return "redirect:/admin/aboutUs";
    }
}
