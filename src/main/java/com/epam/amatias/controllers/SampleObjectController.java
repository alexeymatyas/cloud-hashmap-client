package com.epam.amatias.controllers;

import com.epam.amatias.model.SampleObject;
import com.epam.amatias.rest.CloudHashMapClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

/**
 * Created by Alexey on 27.11.2016.
 */
@Controller
public class SampleObjectController {
    @Autowired
    CloudHashMapClient cloudHashMapClient;

    @GetMapping("/put")
    public String showPutForm(@ModelAttribute("sampleObject") SampleObject sampleObject) {
        return "put";
    }

    @PostMapping(value = "/put", params = {"key"})
    public String putSampleObject(@ModelAttribute("sampleObject") SampleObject sampleObject,
                                  @RequestParam String key, RedirectAttributes redirectAttributes) throws JsonProcessingException {
        cloudHashMapClient.put(key, sampleObject);
        redirectAttributes.addFlashAttribute("message", "Successfully put to Cloud HashMap");
        return "redirect:/put";
    }

    @GetMapping("/get")
    public String showGetForm() {
        return "get";
    }

    @PostMapping(value = "/get", params = {"key"})
    public String getSampleObject(Model model, @RequestParam String key,
                                  RedirectAttributes redirectAttributes) throws IOException {
        model.addAttribute("sampleObject", cloudHashMapClient.get(key, SampleObject.class));
        return "get";
    }
}
