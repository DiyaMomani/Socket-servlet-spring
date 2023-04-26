package com.example.spring.HomePage;

import com.example.spring.DataBase.DBService;
import com.example.spring.Student.AuthStudent;
import com.example.spring.mark.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "School")
public class MarkController {
    DBService studentService;

    @Autowired
    public MarkController(DBService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}/marks")
    @ResponseBody
    public ArrayList<Mark> getStudentMarks(@PathVariable("id") Integer id) {
        if (!AuthStudent.isAuthenticated()
            || AuthStudent.getAuthenticatedId() != id) {
            return new ArrayList<>();
        }

        return studentService.getAllStudentMarks(id);
    }

    @GetMapping("{id}/mark")
    public String getSingleMarkForm(@PathVariable("id") Integer id, Model model) {
        if (!AuthStudent.isAuthenticated()
                || AuthStudent.getAuthenticatedId() != id) {
            return "notLoggedIn";
        }

        model.addAttribute("id", id);
        model.addAttribute("route", "mark");
        model.addAttribute("mark", new Mark());
        return "chooseCourse";
    }

    @PostMapping("{id}/mark")
    @ResponseBody
    public Mark getSingleMarkResult(@ModelAttribute Mark mark, @PathVariable("id") Integer id) {
        return studentService.getStudentMark(id, mark.getName());
    }

    @GetMapping("{id}/average")
    public String getAverageForm(@PathVariable("id") Integer id, Model model) {
        if (!AuthStudent.isAuthenticated()
                || AuthStudent.getAuthenticatedId() != id) {
            return "notLoggedIn";
        }

        model.addAttribute("id", id);
        model.addAttribute("route", "average");
        model.addAttribute("mark", new Mark());
        return "chooseCourse";
    }

    @PostMapping("{id}/average")
    @ResponseBody
    public Mark getAverageResult(@ModelAttribute Mark mark, @PathVariable int id) {
        return studentService.getAverage(mark.getName());
    }

    @GetMapping("{id}/max")
    public String getMaxForm(@PathVariable("id") Integer id, Model model) {
        if (!AuthStudent.isAuthenticated()
                || AuthStudent.getAuthenticatedId() != id) {
            return "notLoggedIn";
        }

        model.addAttribute("id", id);
        model.addAttribute("route", "max");
        model.addAttribute("mark", new Mark());
        return "chooseCourse";
    }

    @PostMapping("{id}/max")
    @ResponseBody
    public Mark getMaxResult(@ModelAttribute Mark mark, @PathVariable int id) {
        return studentService.getMax(mark.getName());
    }

    @GetMapping("{id}/min")
    public String getMinForm(@PathVariable("id") Integer id, Model model) {
        if (!AuthStudent.isAuthenticated()
                || AuthStudent.getAuthenticatedId() != id) {
            return "notLoggedIn";
        }

        model.addAttribute("id", id);
        model.addAttribute("route", "min");
        model.addAttribute("mark", new Mark());
        return "chooseCourse";
    }

    @PostMapping("{id}/min")
    @ResponseBody
    public Mark getMinResult(@ModelAttribute Mark mark, @PathVariable int id) {
        return studentService.getMin(mark.getName());
    }
    @GetMapping("{id}/median")
    public String getMedianForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("route", "median");
        model.addAttribute("mark", new Mark());
        return "chooseCourse";
    }

    @PostMapping("{id}/median")
    @ResponseBody
    public Mark getMedianResult(@ModelAttribute Mark mark, @PathVariable int id,  Model model) {
        return studentService.getMedian(mark.getName());
    }

}
