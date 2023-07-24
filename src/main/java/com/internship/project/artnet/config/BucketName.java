package com.internship.project.artnet.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {
    _IMAGE("image-artnet");
    private final String bucketName;
}
