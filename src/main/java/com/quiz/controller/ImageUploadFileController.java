package com.quiz.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageUploadFileController {
	@RequestMapping(value = "getimage/{image}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImage(@PathVariable("image") String image) {
		if (image.equals("") || image != null) {
			try {
				Path filename = Paths.get("upload", image);
				byte[] buffer = Files.readAllBytes(filename);
				ByteArrayResource arrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok().contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png")).body(arrayResource);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.badRequest().build();
	}
}
