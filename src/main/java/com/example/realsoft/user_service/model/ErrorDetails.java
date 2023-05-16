package com.example.realsoft.user_service.model;

import java.util.Date;

public record ErrorDetails(Date timestamp, String message, String details) {
}
