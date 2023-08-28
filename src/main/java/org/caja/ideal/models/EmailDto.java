package org.caja.ideal.models;

public record EmailDto(String[] toUser,
                       String subject,
                       String message) {
}
