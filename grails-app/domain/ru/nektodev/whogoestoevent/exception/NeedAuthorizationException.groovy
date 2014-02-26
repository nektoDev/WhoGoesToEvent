package ru.nektodev.whogoestoevent.exception

class NeedAuthorizationException extends Exception {

    NeedAuthorizationException(String message) {
        super(message)
    }

    NeedAuthorizationException(String message, Throwable cause) {
        super(message, cause)
    }

}
