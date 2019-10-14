package com.karthik.test.csvprocessor.controller;

import com.karthik.test.csvprocessor.model.Record;
import com.karthik.test.csvprocessor.parse.CsvParser;
import com.karthik.test.csvprocessor.parse.Normaliser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class CsvController
{
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String listUploadedFiles()
    {
        return "uploadForm";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException
    {
        if (file.isEmpty())
        {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        byte[] bytes = file.getBytes();
        Path filePath = Paths.get(uploadPath + File.separator + file.getOriginalFilename());
        Files.write(filePath, bytes);
        redirectAttributes.addFlashAttribute("message",
                        "Showing results from '" + file.getOriginalFilename() + "'");

        CsvParser parser = new CsvParser();
        List<Record> allRecords = parser.parse(filePath);

        List<Record> normalisedRecords = new Normaliser().getNormalisedRecords(allRecords);

        redirectAttributes.addFlashAttribute("records", normalisedRecords);

        int varCount = normalisedRecords.isEmpty()? 0 : normalisedRecords.get(0).getValues().size();
        redirectAttributes.addFlashAttribute("varCount", varCount);

        return "redirect:/";
    }
}
